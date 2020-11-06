package com.example.minerva.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.minerva.R
import com.example.minerva.service.model.NotaModel
import com.example.minerva.ui.viewmodel.CadastroNotaViewModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_cadastro_nota.*

class CadastroNotaActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mViewModel: CadastroNotaViewModel
    private var mGuestId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (supportActionBar != null) {
            supportActionBar!!.title = "Cadastrar Nota"

        }
        setContentView(R.layout.activity_cadastro_nota)

        mViewModel = ViewModelProvider(this).get(CadastroNotaViewModel::class.java)

        // Eventos
        setListeners()

        // Cria observadores
        observe()

        // Carrega dados do usuário, caso haja
        loadData()

    }

    override fun onClick(v: View) {
        val id = v.id
        if (id == R.id.button_salvar_nota) {

            val titulo = edit_titulo_nota.text.toString()
            val conteudo = edit_conteudo_nota.text.toString()

            mViewModel.save(mGuestId, titulo, conteudo)

            finish()
        }
    }

    private fun loadData() {
        val bundle = intent.extras
        if (bundle != null) {
            mGuestId = bundle.getInt("notaID")
            mViewModel.load(mGuestId)
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
            edit_titulo_nota.setText(it.titulo)
            edit_conteudo_nota.setText(it.texto)
        })
    }


    private fun setListeners() {
        button_salvar_nota.setOnClickListener(this)
    }
    /*
    private fun salvarNota(){
        var nota = NotaModel( edit_conteudo_nota.text.toString(),ed)
        nota.titulo = edit_conteudo_nota.text.toString()

        val  reference = FirebaseDatabase.getInstance().reference
        reference.child("notas").push().setValue(guest)
    }*/
}