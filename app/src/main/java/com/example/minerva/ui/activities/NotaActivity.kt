package com.example.minerva.ui.activities

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.example.minerva.R
import com.example.minerva.service.constants.CoresNotasConstants
import com.example.minerva.ui.viewmodel.NotaViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_nota.*

class NotaActivity : AppCompatActivity() {

    private lateinit var mViewModel: NotaViewModel
    private lateinit var mIdNota: String
    private lateinit var mTitulo: String
    private lateinit var mCor: String
    private lateinit var mConteudo: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (supportActionBar != null) {
            supportActionBar!!.title = ""
            supportActionBar!!.setDisplayHomeAsUpEnabled(true) //Mostrar o botão
            supportActionBar!!.setHomeButtonEnabled(true)      //Ativar o botão
        }
        setContentView(R.layout.activity_nota)
        mViewModel = ViewModelProvider(this).get(NotaViewModel::class.java)

        loadData()

        try {
            layout_nota.setBackgroundColor(Color.parseColor(mCor))
            when (mCor) {
                CoresNotasConstants.AMARELO -> {
                    supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor(CoresNotasConstants.AMARELO_ESCURO)))
                }
                CoresNotasConstants.AZUL -> {
                    supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor(CoresNotasConstants.AZUL_ESCURO)))
                }
                CoresNotasConstants.VERMELHO -> {
                    supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor(CoresNotasConstants.VERMELHO_ESCURO)))
                }
                else -> {
                    supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor(CoresNotasConstants.VERDE_ESCURO)))
                }
            }
        }catch (e: Exception){
            e.printStackTrace()
        }
        // Cria observadores
        observe()
    }

    private fun loadData() {
        val bundle = intent.extras
        if (bundle != null) {
            // mGuestId = bundle.getInt("notaID")
            // mViewModel.load(mGuestId)

            mIdNota = bundle.getString("idNota") ?: ""
            mTitulo = bundle.getString("titulo") ?: ""
            mConteudo = bundle.getString("conteudo") ?: ""
            mCor = bundle.getString("cor") ?: ""

            text_titulo_nota.text = mTitulo
            text_conteudo_nota.text = mConteudo

        }
    }

    private fun observe() {
        mViewModel.saveGuest.observe(this, {
            if (it) {
                Toast.makeText(applicationContext, "Sucesso", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(applicationContext, "Falha", Toast.LENGTH_SHORT).show()
            }
            finish()
        })

        mViewModel.guest.observe(this, {
            text_titulo_nota.text = it.titulo
            text_conteudo_nota.text = it.texto
        })
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
                val intent = Intent(this, CadastroNotaActivity::class.java)

                val bundle = Bundle()
                bundle.putString("idNota", mIdNota)
                bundle.putString("titulo", mTitulo)
                bundle.putString("conteudo", mConteudo)
                bundle.putString("cor", mCor)

                intent.putExtras(bundle)
                startActivity(intent)

                finish()
            }
            2 -> {
                AlertDialog.Builder(this)
                    .setTitle("Remoção de nota")
                    .setMessage("Deseja mesmo remover a nota?")
                    .setPositiveButton("Remover") { _, _ ->
                        excluirNota()
                    }
                    .setNeutralButton("Cancelar", null)
                    .show()
            }
        }
        return true
    }

    private fun excluirNota(){
        val  reference = FirebaseDatabase.getInstance().reference
        reference.child("usuarios").child(FirebaseAuth.getInstance().currentUser!!.uid).child("notas").child(mIdNota).removeValue()
        finish()
    }

}