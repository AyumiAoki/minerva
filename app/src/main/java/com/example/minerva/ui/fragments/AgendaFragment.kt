package com.example.minerva.ui.fragments

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.minerva.R
import com.example.minerva.service.model.LembreteModel
import com.example.minerva.ui.activities.CadastroLembreteActivity
import com.example.minerva.ui.activities.LembreteActivity
import com.example.minerva.ui.adapter.AgendaAdapter
import com.example.minerva.ui.listener.AgendaListListener
import com.example.minerva.util.CurrentDayDecorator
import com.example.minerva.util.UsuarioFirebase
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import kotlinx.android.synthetic.main.fragment_agenda.*
import java.util.*
import kotlin.collections.ArrayList

class AgendaFragment : Fragment() {

    private lateinit var mListener: AgendaListener

    private val mAdapter: AgendaAdapter = AgendaAdapter()
    private lateinit var mAgendaListener: AgendaListListener
    private lateinit var listaAgenda: MutableList<LembreteModel>

    private lateinit var buttonAdicionarAgendar: FloatingActionButton
    private lateinit var calendarView: MaterialCalendarView
    private lateinit var textData: TextView
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_agenda, container, false)

        mAgendaListener = object : AgendaListListener {
            override fun onClick(lembreteModel: LembreteModel) {
                val intent = Intent(context, LembreteActivity::class.java)

                val bundle = Bundle()
                bundle.putString("idFirebase", lembreteModel.idFirebase)
                bundle.putInt("idAlarme", lembreteModel.id)
                bundle.putString("cor", lembreteModel.cor)
                bundle.putString("conteudo", lembreteModel.titulo)
                bundle.putInt("dia", lembreteModel.dia)
                bundle.putInt("mes", lembreteModel.mes)
                bundle.putInt("ano", lembreteModel.ano)
                bundle.putInt("hora", lembreteModel.hora)
                bundle.putInt("minuto", lembreteModel.minuto)

                println(lembreteModel)
                intent.putExtras(bundle)
                startActivity(intent)
            }

            override fun onDelete(id: Int) {
                TODO("Not yet implemented")
            }

            override fun onClickButton(view: View) {
                /*   view.setOnClickListener{
                   println("triste")
                   val popup = android.widget.PopupMenu(context!!, it)
                   val inflater: MenuInflater = popup.menuInflater
                   inflater.inflate(R.menu.button_opcoes_agenda, popup.menu)
                   popup.show()
               }*/
            }
        }
        if (!UsuarioFirebase.usuarioAtual!!.isAnonymous) {
            textData = root.findViewById(R.id.text_view_data_lembrete)
            calendarView = root.findViewById(R.id.calendarView)
            progressBar = root.findViewById(R.id.progressBar_lembretes)
            progressBar.visibility = View.VISIBLE

            val meses = arrayOf<CharSequence>(
                "Janeiro",
                "Fevereiro",
                "Março",
                "Abril",
                "Maio",
                "Junho",
                "Julho",
                "Agosto",
                "Setembro",
                "Outubro",
                "Novembro",
                "Dezembro"
            )
            calendarView.setTitleMonths(meses)

            val semanas = arrayOf<CharSequence>(
                "Seg",
                "Ter",
                "Qua",
                "Qui",
                "Sex",
                "Sab",
                "Dom"
            )
            calendarView.setWeekDayLabels(semanas)

            val hoje = CalendarDay.today()
            calendarView.setDateSelected(Calendar.getInstance(), true)
            textData.text = "${hoje.day}/${hoje.month + 1}/${hoje.year}"

            calendarView.setOnDateChangedListener { widget, date, selected ->
                textData.text = "${date.day}/${date.month + 1}/${date.year}"
                mAdapter.updateNota(listaAgenda.filter { it.ano == date.year && it.mes == date.month && it.dia == date.day })
            }

            listaAgenda = ArrayList()

            buttonAdicionarAgendar = root.findViewById(R.id.button_adicionar_evento_agenda)
            buttonAdicionarAgendar.setOnClickListener {
                startActivity(Intent(mListener as Context, CadastroLembreteActivity::class.java))
            }

            val recycler = root.findViewById<RecyclerView>(R.id.lista_agenda)
            recycler.setHasFixedSize(true)
            recycler.layoutManager = LinearLayoutManager(context)
            recycler.adapter = mAdapter



            val data = FirebaseDatabase.getInstance().reference
            val notas =
                data.child("usuarios").child(FirebaseAuth.getInstance().currentUser!!.uid).child(
                    "agenda"
                )

            val query = notas.orderByChild("minuto")
            query.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val list: MutableList<LembreteModel> = java.util.ArrayList()
                    calendarView.removeDecorators()
                    for (postSnapshot in dataSnapshot.children) {

                        val mes = postSnapshot.child("mes").value.toString()
                        val minuto = postSnapshot.child("minuto").value.toString()
                        val ano = postSnapshot.child("ano").value.toString()
                        val hora = postSnapshot.child("hora").value.toString()
                        val id = postSnapshot.child("id").value.toString()
                        val dia = postSnapshot.child("dia").value.toString()
                        val titulo = postSnapshot.child("titulo").value.toString()
                        val cor = postSnapshot.child("cor").value.toString()
                        val idFirebase = postSnapshot.key.toString()

                        list.add(
                            LembreteModel(
                                idFirebase,
                                id.toInt(),
                                titulo,
                                cor,
                                ano.toInt(),
                                mes.toInt(),
                                dia.toInt(),
                                hora.toInt(),
                                minuto.toInt()
                            )
                        )

                        list.sortBy { it.hora }
                        val date = CalendarDay.from(ano.toInt(), mes.toInt(), dia.toInt())
                        calendarView.addDecorators(CurrentDayDecorator(activity, date, cor))
                        listaAgenda = list
                    }

                    atualizarLista(list)
                    if (progressBar != null) {
                        progressBar.visibility = View.INVISIBLE
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {}
            })
        } else {
            AlertDialog.Builder(requireContext())
                .setTitle("Ops!")
                .setMessage("Para ter acesso a essa funcionalidade você precisa ser um Minerva!")
                .setPositiveButton("Ok", null)
                .setNegativeButton("Login"){ _, _ ->
                    mListener.onAnotacao(1)

                }
                .show()
        }
        return root
    }

    fun atualizarLista(list: MutableList<LembreteModel>) {
        println(calendarView.currentDate)
        val listaAuxiliar =
            list.filter { it.dia == calendarView.selectedDate.day && it.mes == calendarView.selectedDate.month && it.ano == calendarView.selectedDate.year }
        mAdapter.updateNota(listaAuxiliar)
        if (listaAuxiliar.isEmpty() && text_view_lista_lembretes_vazia != null) {
            text_view_lista_lembretes_vazia.visibility = View.VISIBLE
        } else if (text_view_lista_lembretes_vazia != null) {
            text_view_lista_lembretes_vazia.visibility = View.INVISIBLE
        }
    }

    interface AgendaListener {
        fun onAnotacao(codigo: Int)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mListener = try {
            activity as AgendaListener

        } catch (e: ClassCastException) {
            throw ClassCastException(activity.toString() + " deve implementar CreateEmailListener")
        }

    }

    override fun onResume() {
        super.onResume()
        mAdapter.attachListener(mAgendaListener)
    }
}