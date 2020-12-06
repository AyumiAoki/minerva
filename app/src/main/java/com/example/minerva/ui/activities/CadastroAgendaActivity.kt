package com.example.minerva.ui.activities

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.minerva.R
import com.example.minerva.service.constants.CoresNotasConstants
import com.example.minerva.service.model.LembreteModel
import com.example.minerva.ui.fragments.SelecaoCoresFragment
import com.example.minerva.util.ConfiguracaoFirebase
import com.example.minerva.util.SalvarNotificacao
import com.google.firebase.auth.FirebaseAuth
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog
import kotlinx.android.synthetic.main.activity_cadastro_agenda.*
import kotlinx.android.synthetic.main.activity_cadastro_nota.*
import java.util.*


class CadastroAgendaActivity : AppCompatActivity(), View.OnClickListener,
    SelecaoCoresFragment.SelecaoCores, DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener {



    var id = 0
    var anoUsuario: Int = 0
    var mesUsario: Int = 0
    var diaUsuario: Int = 0
    var horaUsuario: Int = 0
    var minutoUsuario: Int = 0
    var cor = "#33AEC4"

    private var anoAtual = 0
    private var mesAtual = 0
    private var diaAtual = 0
    private var horaAtual = 0
    private var minutoAtual = 0

    lateinit var datePickerDialog: DatePickerDialog
    lateinit var timePickerDialog: TimePickerDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (supportActionBar != null) {

            supportActionBar!!.title = ""
            supportActionBar!!.setDisplayHomeAsUpEnabled(true) //Mostrar o botão
            supportActionBar!!.setHomeButtonEnabled(true)      //Ativar o botão
        }

        setContentView(R.layout.activity_cadastro_agenda)

        criarPickers()

        button_escolher_data.setOnClickListener(this)
        button_escolher_hora.setOnClickListener(this)
        button_salvar_lembrete.setOnClickListener(this)

        val bundle = intent.extras
        if (bundle != null) {
            println("hihihihi")
            button_salvar_lembrete.text = "Atualizar lembrete"
            cor = bundle.getString("cor") ?: ""
            diaUsuario = bundle.getInt("dia")
            mesUsario = bundle.getInt("mes")
            anoUsuario = bundle.getInt("ano")
            horaUsuario = bundle.getInt("hora")
            minutoUsuario = bundle.getInt("minuto")
            val conteudo = bundle.getString("conteudo") ?: ""
            cor = bundle.getString("cor") ?: ""
            id = bundle.getInt("idAlarme")

            edit_text_titulo_agenda.setText(conteudo)

            var dia = if (diaUsuario < 10) "0$diaUsuario" else "$diaUsuario"
            var mes = if (mesUsario < 10) "0$mesUsario" else "$mesUsario"
            edit_text_escolher_data.setText("${dia}/${mes.toInt() + 1}/$anoUsuario")

            var minuto = if (minutoUsuario < 10) "0$minutoUsuario" else "$minutoUsuario"
            var hora = if (horaUsuario < 10) "0$horaUsuario" else "$horaUsuario"
            edit_text_escolher_hora.setText("$hora:$minuto")

            try {
                when (cor) {
                    CoresNotasConstants.AMARELO -> {
                        supportActionBar!!.setBackgroundDrawable(
                            ColorDrawable(
                                Color.parseColor(
                                    CoresNotasConstants.AMARELO_ESCURO
                                )
                            )
                        )
                    }
                    CoresNotasConstants.AZUL -> {
                        supportActionBar!!.setBackgroundDrawable(
                            ColorDrawable(
                                Color.parseColor(
                                    CoresNotasConstants.AZUL_ESCURO
                                )
                            )
                        )
                    }
                    CoresNotasConstants.VERMELHO -> {
                        supportActionBar!!.setBackgroundDrawable(
                            ColorDrawable(
                                Color.parseColor(
                                    CoresNotasConstants.VERMELHO_ESCURO
                                )
                            )
                        )
                    }
                    else -> {
                        supportActionBar!!.setBackgroundDrawable(
                            ColorDrawable(
                                Color.parseColor(
                                    CoresNotasConstants.VERDE_ESCURO
                                )
                            )
                        )
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.button_escolher_data -> {
                datePickerDialog.show(supportFragmentManager, "Oiiii")
            }
            R.id.button_escolher_hora -> {
                timePickerDialog.show(supportFragmentManager, "Oiiiiii")
            }
            R.id.button_salvar_lembrete -> {
                if(validarCampos()){
                    if(id == 0){
                        id = System.currentTimeMillis().toInt()
                    }
                    SalvarNotificacao.scheduleNotification(
                        this,
                        anoUsuario,
                        mesUsario,
                        diaUsuario,
                        horaUsuario,
                        minutoUsuario,
                        id,
                        edit_text_titulo_agenda.text.toString()
                    )
                    salvarAgenda()
                    finish()
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)

        val m1 = menu.add(0, 1, 1, "paleta")
        m1.setIcon(R.drawable.ic_paleta)
        m1.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean { //Botão adicional na ToolBar
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
            1 -> {
                chamarMyDialog()
            }
        }
        return true
    }

    override fun onCor(cor: String) {
        this.cor = cor
        when (cor) {
            CoresNotasConstants.AMARELO -> {
                supportActionBar!!.setBackgroundDrawable(
                    ColorDrawable(
                        Color.parseColor(
                            CoresNotasConstants.AMARELO_ESCURO
                        )
                    )
                )
            }
            CoresNotasConstants.AZUL -> {
                supportActionBar!!.setBackgroundDrawable(
                    ColorDrawable(
                        Color.parseColor(
                            CoresNotasConstants.AZUL_ESCURO
                        )
                    )
                )
            }
            CoresNotasConstants.VERMELHO -> {
                supportActionBar!!.setBackgroundDrawable(
                    ColorDrawable(
                        Color.parseColor(
                            CoresNotasConstants.VERMELHO_ESCURO
                        )
                    )
                )
            }
            else -> {
                supportActionBar!!.setBackgroundDrawable(
                    ColorDrawable(
                        Color.parseColor(
                            CoresNotasConstants.VERDE_ESCURO
                        )
                    )
                )
            }
        }
    }

    private fun chamarMyDialog() {
        val myFragment = SelecaoCoresFragment()
        myFragment.show(supportFragmentManager, "my_fragment")
    }

    private fun initDateTimeData() {
        val c = Calendar.getInstance()
        anoAtual = c.get(Calendar.YEAR)
        mesAtual = c.get(Calendar.MONTH)
        diaAtual = c.get(Calendar.DAY_OF_MONTH)
        horaAtual = c.get(Calendar.HOUR_OF_DAY)
        minutoAtual = c.get(Calendar.MINUTE)
    }

    override fun onDateSet(view: DatePickerDialog?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        anoUsuario = year
        mesUsario = monthOfYear
        diaUsuario = dayOfMonth

        var dia = if (dayOfMonth < 10) "0$dayOfMonth" else "$dayOfMonth"
        var mes = if (monthOfYear < 10) "0$monthOfYear" else "$monthOfYear"

        edit_text_escolher_data.setText("${dia}/${mes.toInt() + 1}/$anoUsuario")
        println("${dia}/${mes + 1}/$anoUsuario")
    }

    override fun onTimeSet(view: TimePickerDialog?, hourOfDay: Int, minute: Int, second: Int) {
        horaUsuario = hourOfDay
        minutoUsuario = minute

        var hora = if (hourOfDay < 10) "0$hourOfDay" else "$hourOfDay"
        var minuto = if (minute < 10) "0$minute" else "$minute"
        edit_text_escolher_hora.setText("$hora:$minuto")
    }

    fun criarPickers() {
        initDateTimeData()
        val cDefault = Calendar.getInstance()
        cDefault.set(anoAtual, mesAtual, diaAtual, horaAtual, minutoAtual)

        datePickerDialog = DatePickerDialog.newInstance(
            this,
            cDefault.get(Calendar.YEAR),
            cDefault.get(Calendar.MONTH),
            cDefault.get(Calendar.DAY_OF_MONTH)
        )

        val cMin = Calendar.getInstance()
        datePickerDialog.accentColor = resources.getColor(R.color.colorPrimary)
        datePickerDialog.minDate = cMin

        timePickerDialog = TimePickerDialog.newInstance(
            this,
            cDefault.get(Calendar.HOUR_OF_DAY),
            cDefault.get(Calendar.MINUTE),
            true
        )

        timePickerDialog.accentColor = resources.getColor(R.color.colorPrimary)
    }

    private fun salvarAgenda() {
        val titulo = edit_text_titulo_agenda.text.toString()
        var idAlarme = id
        val bundle = intent.extras
        val reference = ConfiguracaoFirebase.firebaseDatabase
        val dataUsuario = edit_text_escolher_data.text!!.split("/")
        diaUsuario = dataUsuario[0].toInt()
        mesUsario = dataUsuario[1].toInt() - 1
        anoUsuario = dataUsuario[2].toInt()
        val horarioUsuario = edit_text_escolher_hora.text!!.split(":")
        minutoUsuario = horarioUsuario[1].toInt()
        horaUsuario = horarioUsuario[0].toInt()


        if (bundle != null) {
            idAlarme = bundle.getInt("idAlarme")
        }
        val agenda =
            LembreteModel(
                null,
                idAlarme,
                titulo,
                cor,
                anoUsuario,
                mesUsario,
                diaUsuario,
                horaUsuario,
                minutoUsuario
            )

        if (bundle != null) {
            reference!!.child("usuarios").child(FirebaseAuth.getInstance().currentUser!!.uid)
                .child("agenda").child(bundle.getString("idFirebase") ?: "").setValue(agenda)
        } else {
            reference!!.child("usuarios").child(FirebaseAuth.getInstance().currentUser!!.uid)
                .child("agenda").push().setValue(agenda)
        }

    }

    private fun validarCampos(): Boolean{
        val titulo = edit_text_titulo_agenda.text.toString()
        val data = edit_text_escolher_data.text.toString()
        val hora = edit_text_escolher_hora.text.toString()

        if(titulo.isEmpty()){
            Toast.makeText(this, "Título não pode ser vazio", Toast.LENGTH_SHORT).show()
            return false
        }else if(data.isEmpty()){
            Toast.makeText(this, "Data não pode ser vazia", Toast.LENGTH_SHORT).show()
            return false
        }else if(data.length != 10){
            Toast.makeText(this, "Data tem que estar no formato dd/MM/yyyy", Toast.LENGTH_SHORT).show()
            return false
        }else if(hora.isEmpty()){
            Toast.makeText(this, "Hora não pode ser vazia", Toast.LENGTH_SHORT).show()
            return false
        }else if(hora.length != 5){
            Toast.makeText(this, "Hora tem que estar no formato HH:mm", Toast.LENGTH_SHORT).show()
            return false
        }else{
            return true
        }
    }
}


