package com.example.minerva.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.minerva.R
import kotlinx.android.synthetic.main.activity_tela_inicial.*

class TelaInicialActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }
        setContentView(R.layout.activity_tela_inicial)

        button_começar.setOnClickListener {
            comecar()
        }
    }

    private fun comecar() {
        val intent = Intent(applicationContext, ApresentacaoActivity::class.java)
        startActivity(intent)
    }

}
