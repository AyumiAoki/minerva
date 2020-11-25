package com.example.minerva.ui.fragments

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.opengl.Visibility
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.ImageButton
import android.widget.SearchView
import android.widget.Toolbar
import androidx.annotation.RequiresApi
import androidx.core.view.children
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.minerva.R
import com.example.minerva.service.model.NotaListModel
import com.example.minerva.ui.activities.CadastroNotaActivity
import com.example.minerva.ui.activities.NotaActivity
import com.example.minerva.ui.adapter.NotaAdapter
import com.example.minerva.ui.listener.NotaListListener
import com.example.minerva.ui.viewmodel.AnotacaoViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_anotacao.*
import java.util.ArrayList


class AnotacaoFragment : Fragment() {

    private lateinit var listaNotas: MutableList<NotaListModel>
    private lateinit var anotacaoViewModel: AnotacaoViewModel
    private val mAdapter: NotaAdapter = NotaAdapter()
    private lateinit var  mListListener: NotaListListener
    private lateinit var mListener: CreateAnotacaoListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        anotacaoViewModel =
            ViewModelProviders.of(this).get(AnotacaoViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_anotacao, container, false)
        listaNotas = ArrayList()


        class SearchFilto: androidx.appcompat.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean { return false }
            override fun onQueryTextChange(palavra: String): Boolean {
                mAdapter.updateNota(listaNotas.filter { it.titulo.toUpperCase().contains(palavra.toUpperCase()) })
                return false
            }
        }

        val pesquisa = root.findViewById<ImageButton>(R.id.button_pesquisa)
        val pesquisar = root.findViewById<androidx.appcompat.widget.SearchView>(R.id.button_pesquisar)
        val test:androidx.appcompat.widget.SearchView.OnQueryTextListener = SearchFilto()


        pesquisar.setOnQueryTextListener(test)
        pesquisar.setBackgroundColor(resources.getColor(R.color.colorWhite))
        pesquisar.setOnCloseListener {
            pesquisa.visibility = View.VISIBLE
            pesquisar.visibility = View.INVISIBLE
            true }
        pesquisa.setOnClickListener {
            pesquisa.visibility = View.INVISIBLE
            pesquisar.visibility = View.VISIBLE
            pesquisar.isIconified = false
        }


        val adicionarNota = root.findViewById<FloatingActionButton>(R.id.button_adicionar_anotacao)
        adicionarNota.setOnClickListener{
            startActivity(Intent(mListener as Context, CadastroNotaActivity::class.java))
        }

        val recycler = root.findViewById<RecyclerView>(R.id.list_nota)
        recycler.setHasFixedSize(true)
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = mAdapter

        mListListener = object : NotaListListener {
            override fun onClick(idNota: String, titulo: String, conteudo: String, cor: String) {
                val intent = Intent(context, NotaActivity::class.java)

                val bundle = Bundle()
                bundle.putString("idNota", idNota)
                bundle.putString("titulo", titulo)
                bundle.putString("conteudo", conteudo)
                bundle.putString("cor", cor)

                intent.putExtras(bundle)
                startActivity(intent)
            }

            override fun onDelete(id: String) {}
        }

        val data  = FirebaseDatabase.getInstance().reference
        val notas = data.child("usuarios").child(FirebaseAuth.getInstance().currentUser!!.uid).child("notas")

        val query = notas.orderByChild("titulo")
   //     val query = notas.orderByChild("idUsuario").equalTo(FirebaseAuth.getInstance().currentUser!!.uid)
        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val list: MutableList<NotaListModel> = ArrayList()
                for (postSnapshot in dataSnapshot.children) {
                    val idNota = postSnapshot.toString().substringAfter("key = ").substringBefore(",")
                    val titulo =  postSnapshot.toString().substringAfter("titulo=").substringBefore("}")
                    val conteudo =  postSnapshot.toString().substringAfter("conteudo=").substringBefore(",")
                    val cor =  postSnapshot.toString().substringAfter("cor=").substringBefore(",")
                    list.add(NotaListModel(idNota,titulo,conteudo,cor))
                }

                listaNotas = list
                mAdapter.updateNota(list)
            }
            override fun onCancelled(databaseError: DatabaseError) {}
        })


        return root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mListener = try {
            activity as CreateAnotacaoListener

        } catch (e: ClassCastException) {
            throw ClassCastException(activity.toString() + " deve implementar CreateEmailListener")
        }
        mListener.onCreateAnotacao(1)

    }

    override fun onPause() {
        super.onPause()
        mListener.onCreateAnotacao(2)
    }


    interface CreateAnotacaoListener{
        fun onCreateAnotacao(codigo: Int)
    }

    override fun onResume() {
        super.onResume()
        mAdapter.attachListener(mListListener)
    }

}