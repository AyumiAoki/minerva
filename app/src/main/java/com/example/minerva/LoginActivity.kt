package com.example.minerva

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        button_recuperar_senha.setOnClickListener(this)
        button_cadastrar.setOnClickListener(this)
        button_login.setOnClickListener(this)
        button_login_google.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        val id = view.id
        when (id) {
            R.id.button_cadastrar -> {
                startActivity(Intent(applicationContext, CadastroActivity::class.java))
                finish()
            }

            R.id.button_login -> {
                login()
            }

            R.id.button_login_google -> {
                Toast.makeText(applicationContext, "Teste Login google", Toast.LENGTH_SHORT).show()
            }

            R.id.button_recuperar_senha -> {
                Toast.makeText(applicationContext, "Teste recuperar senha", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    fun login() {
        if (validarDados()) {
            Toast.makeText(applicationContext, "Teste login", Toast.LENGTH_SHORT).show()
        }
    }

    private fun validarDados(): Boolean {
        var check = true

        //Validação campo email
        if(edit_email.text.toString().isEmpty()){
            check = false
            edit_email.error = "Campo obrigatório"
        }else if(!validateEmail(edit_email.text.toString())){
            check = false
            edit_email.error = "Insira um formato de email válido"
        }

        //Validação campo senha
        if(edit_senha.text.toString().isEmpty()){
            check = false
            edit_senha.error = "Campo obrigatório"
        }else if(edit_senha.text.toString().length < 6){
            check = false
            edit_senha.error = "Senha possui 6 ou mais caracteres"
        }

        return check
    }

    fun validateEmail(email: String) : Boolean{
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        return email.matches(emailPattern.toRegex())
    }

}