package com.example.minerva

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.activity_introduction.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.button_cadastrar

class IntroductionActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_introduction)
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        button_cadastrar.setOnClickListener(this)
        button_entrar.setOnClickListener(this)
        button_login_google_2.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        val id = view.id
        when (id) {
            R.id.button_cadastrar -> {
                startActivity(Intent(applicationContext, CadastroActivity::class.java))
                finish()
            }

            R.id.button_login_google_2 -> {
                Toast.makeText(applicationContext, "Teste recuperar senha", Toast.LENGTH_SHORT)
                    .show()

            }

            R.id.button_entrar -> {
                startActivity(Intent(applicationContext, LoginActivity::class.java))
                finish()
            }
        }
    }
}

