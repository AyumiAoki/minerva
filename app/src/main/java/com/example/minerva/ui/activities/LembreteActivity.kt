package com.example.minerva.ui.activities

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.minerva.R
import com.example.minerva.service.constants.CoresNotasConstants
import com.example.minerva.service.model.LembreteModel
import com.example.minerva.util.ConfiguracaoFirebase
import com.example.minerva.util.MyNotificationSystem
import com.example.minerva.util.SalvarNotificacao
import com.example.minerva.util.UsuarioFirebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_lembrete.*
import kotlinx.android.synthetic.main.activity_nota.*

class LembreteActivity : AppCompatActivity() {
    private lateinit var mLembrete: LembreteModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (supportActionBar != null) {
            supportActionBar!!.title = ""
            supportActionBar!!.setDisplayHomeAsUpEnabled(true) //Mostrar o botão
            supportActionBar!!.setHomeButtonEnabled(true)      //Ativar o botão
        }

        setContentView(R.layout.activity_lembrete)
        loadData()

        try {
            when (mLembrete.cor) {
                CoresNotasConstants.AMARELO -> {
                    supportActionBar!!.setBackgroundDrawable(
                        ColorDrawable(
                            Color.parseColor(
                                CoresNotasConstants.AMARELO_ESCURO))
                    )
                }
                CoresNotasConstants.AZUL -> {
                    supportActionBar!!.setBackgroundDrawable(
                        ColorDrawable(
                            Color.parseColor(
                                CoresNotasConstants.AZUL_ESCURO))
                    )
                }
                CoresNotasConstants.VERMELHO -> {
                    supportActionBar!!.setBackgroundDrawable(
                        ColorDrawable(
                            Color.parseColor(
                                CoresNotasConstants.VERMELHO_ESCURO))
                    )
                }
                else -> {
                    supportActionBar!!.setBackgroundDrawable(
                        ColorDrawable(
                            Color.parseColor(
                                CoresNotasConstants.VERDE_ESCURO))
                    )
                }
            }
        }catch (e: Exception){
            e.printStackTrace()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)

        val m1 = menu.add(0, 1, 1, "editar")
        m1.setIcon(R.drawable.ic_editar)
        m1.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)

        val m2 = menu.add(0, 2, 2, "excluir")
        m2.setIcon(R.drawable.ic_excluir)
        m2.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean { //Botão adicional na ToolBar
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
            1 -> {
                val intent = Intent(this, CadastroAgendaActivity::class.java)

                val bundle = Bundle()
                bundle.putString("idFirebase", mLembrete.idFirebase)
                bundle.putInt("idAlarme", mLembrete.id)
                bundle.putString("cor", mLembrete.cor)
                bundle.putString("conteudo", mLembrete.titulo)
                bundle.putInt("dia", mLembrete.dia)
                bundle.putInt("mes", mLembrete.mes)
                bundle.putInt("ano", mLembrete.ano)
                bundle.putInt("hora", mLembrete.hora)
                bundle.putInt("minuto", mLembrete.minuto)

                intent.putExtras(bundle)
                startActivity(intent)

                finish()
            }
            2 -> {
                AlertDialog.Builder(this)
                    .setTitle("Remoção de lembrete")
                    .setMessage("Deseja mesmo remover o lembrete?")
                    .setPositiveButton("Remover") { _, _ ->
                        excluirLembrete()
                    }
                    .setNeutralButton("Cancelar", null)
                    .show()
                SalvarNotificacao.excluirNotificação(applicationContext, mLembrete.id, mLembrete.titulo)
            }
        }
        return true
    }

    fun loadData() {
        val bundle = intent.extras
        if (bundle != null) {
            val idFirebase = bundle.getString("idFirebase") ?: ""
            val id = bundle.getInt("idAlarme") ?: 0
            val cor = bundle.getString("cor") ?: ""
            val dia = bundle.getInt("dia") ?: 0
            val mes = bundle.getInt("mes") ?: 0
            val ano = bundle.getInt("ano") ?: 0
            val hora = bundle.getInt("hora") ?: 0
            val minuto = bundle.getInt("minuto") ?: 0
            val conteudo = bundle.getString("conteudo") ?: ""

            mLembrete = LembreteModel(idFirebase, id, conteudo, cor, ano, mes, dia, hora, minuto)

            text_titulo_lembrete.text = mLembrete.titulo
            val minutoTexto = if(mLembrete.minuto > 10) mLembrete.minuto else "0${mLembrete.minuto}"
            text_view_hora_lembrete.text = "${mLembrete.hora}:$minutoTexto"
            text_view_data_lembrete.text = "${mLembrete.dia} de ${mesExtenso(mLembrete.mes)} de ${mLembrete.ano}"
        }
    }

    private fun mesExtenso(mes: Int) : String{
        return when (mes){
            0 -> "Janeiro"
            1 -> "Fevereiro"
            2 -> "Março"
            3 -> "Abril"
            4 -> "Maio"
            5 -> "Junho"
            6 -> "Julho"
            7 -> "Agosto"
            8 -> "Setembro"
            9 -> "Outubro"
            10 -> "Novembro"
            11 -> "Dezembro"

            else -> "lascou"
        }
    }

    private fun excluirLembrete(){
        val  reference = ConfiguracaoFirebase.firebaseDatabase
        reference!!.child("usuarios").child(FirebaseAuth.getInstance().currentUser!!.uid).child("agenda").child(mLembrete.idFirebase!!).removeValue()
        finish()
    }
}