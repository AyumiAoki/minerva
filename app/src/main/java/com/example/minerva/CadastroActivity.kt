package com.example.minerva

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_cadastro.*

class CadastroActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        button_login.setOnClickListener(this)
        button_termo_uso.setOnClickListener(this)
        button_cadastrar.setOnClickListener(this)
        button_cadastro_google.setOnClickListener(this)
        checkbox_termo.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        val id = view.id

        when (id) {
            R.id.button_login -> {
                startActivity(Intent(applicationContext, LoginActivity::class.java))
                finish()
            }

            R.id.button_termo_uso -> {
                Toast.makeText(applicationContext, "Teste termo de uso", Toast.LENGTH_SHORT).show()
            }

            R.id.button_cadastrar -> {
                cadastrar()
            }

            R.id.button_cadastro_google -> {
                Toast.makeText(applicationContext, "Teste cadastrar google", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun cadastrar() {
        if(validarDados()){
            Toast.makeText(applicationContext, "Teste cadastrar", Toast.LENGTH_SHORT).show()
        }
    }

    private fun validarDados(): Boolean {
        var check = true
        if(edit_name.text.toString().isEmpty()){
            check = false
            edit_name.error = "Campo obrigaóorio"
        }else if(!validarNome(edit_name.text.toString())){
            check = false
            edit_name.error = "Formato de nome invalído"
        }

        if(edit_sobrenome.text.toString().isEmpty()){
            check = false
            edit_sobrenome.error = "Campo obrigaóorio"
        }else if(!validarNome(edit_sobrenome.text.toString())){
            check = false
            edit_name.error = "Formato de nome invalído"
        }

        if(edit_email.text.toString().isEmpty()){
            check = false
            edit_email.error = "Campo obrigaóorio"
        }else if(!validateEmail(edit_email.text.toString())){
            check = false
            edit_email.error = "Insira um email válido"
        }

        if(edit_senha.text.toString().isEmpty()){
            check = false
            edit_senha.error = "Campo obrigaóorio"
        }else if(edit_senha.text.toString().length < 6){
            check = false
            edit_senha.error = "Senha possui 6 ou mais caracteres"
        }

        if(!checkbox_termo.isChecked){
            check = false
            checkbox_termo.error = "Você deve aceitar os termos para prosseguir"
        }else{
            checkbox_termo.setError(null)
        }
        return check
    }

    fun validateEmail(email: String) : Boolean{
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        return email.matches(emailPattern.toRegex())
    }

    fun validarNome(nome : String):Boolean{
        for(a in nome){
            if(!a.isLetter() && !a.isWhitespace())
                return false
        }
        return true
    }

}