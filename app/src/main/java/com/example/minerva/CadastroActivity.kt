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
                Toast.makeText(applicationContext, "Teste cadastrar", Toast.LENGTH_SHORT).show()
            }

            R.id.button_cadastro_google -> {
                Toast.makeText(applicationContext, "Teste cadastrar google", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}