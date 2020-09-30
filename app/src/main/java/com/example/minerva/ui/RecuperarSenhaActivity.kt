package com.example.minerva.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.minerva.R
import com.example.minerva.service.Util
import com.example.minerva.service.constants.ErrorsFirebase
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_recuperar_senha.*

class RecuperarSenhaActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recuperar_senha)

        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        auth = FirebaseAuth.getInstance()

        button_recuperar_senha.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when(view.id){
            R.id.button_recuperar_senha -> {
                recuperarSenha()
            }
        }
    }

    private fun recuperarSenha() {
        val email = edit_email_recuperar_senha.text.toString()
        if(validarDados()) {
            auth.sendPasswordResetEmail(email).addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    Toast.makeText(
                        applicationContext,
                        "Foi enviada uma mensagem de recuperação de senha ao seu email",
                        Toast.LENGTH_LONG
                    )
                        .show()
                } else {
                    validacaoFirebase(it.exception.toString())
                }
            }
        }
    }

    private fun validacaoFirebase(error: String) {
        when (Util.errorFirebase(error)){
            ErrorsFirebase.EMAIL_INVALIDO -> {
                edit_email_recuperar_senha.error = "Insira um email válido"
            }
            ErrorsFirebase.EMAIL_NAO_CADASTRADO -> {
                edit_email_recuperar_senha.error = "Este e-mail não está cadastrado"
            }
            ErrorsFirebase.SEM_CONEXAO -> {
                Toast.makeText(applicationContext, "Sem conexão com o Firebase", Toast.LENGTH_LONG)
                    .show()
            }
            else ->{
                Toast.makeText(applicationContext, error, Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun validarDados(): Boolean {
        var check = true

        val email = edit_email_recuperar_senha.text.toString()

        //Validação campo email
        if (email.isEmpty()) {
            check = false
            edit_email_recuperar_senha.error = "Campo obrigatório"
        } else if (!Util.validarEmail(email)) {
            check = false
            edit_email_recuperar_senha.error = "Insira um formato de email válido"
        }

        //Verifica conexão com a internet
        if (!Util.statusInternet_MoWi(applicationContext)) {
            check = false
            Toast.makeText(applicationContext, "Sem conexão com a internet", Toast.LENGTH_SHORT)
                .show()
        }
        return check
    }
}