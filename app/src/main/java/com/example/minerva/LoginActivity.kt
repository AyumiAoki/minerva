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
                Toast.makeText(applicationContext, "Teste login", Toast.LENGTH_SHORT).show()
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

}