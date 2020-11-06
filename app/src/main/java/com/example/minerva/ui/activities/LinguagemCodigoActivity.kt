package com.example.minerva.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.example.minerva.R
import kotlinx.android.synthetic.main.activity_linguagem_codigo.*

class LinguagemCodigoActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (supportActionBar != null) {
            supportActionBar!!.title = "Linguagens e Códigos"
            supportActionBar!!.setDisplayHomeAsUpEnabled(true) //Mostrar o botão
            supportActionBar!!.setHomeButtonEnabled(true)      //Ativar o botão
        }
        setContentView(R.layout.activity_linguagem_codigo)
        button_portugues.setOnClickListener(this)
        button_artes.setOnClickListener(this)
        button_comunicacao.setOnClickListener(this)
        button_educacao_fisica.setOnClickListener(this)
        button_espanhol.setOnClickListener(this)
        button_ingles.setOnClickListener(this)
        button_literatura.setOnClickListener(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean { //Botão adicional na ToolBar
        when (item.itemId) {
            android.R.id.home -> {
                /*startActivity(
                    Intent(
                        this,
                        SuaActivity::class.java
                    )
                ) //O efeito ao ser pressionado do botão (no caso abre a activity)*/
                finish()
                /* finishAffinity() //Método para matar a activity e não deixa-lá indexada na pilhagem*/
            }
            else -> {
            }
        }
        return true
    }

    override fun onClick(view: View) {
        startActivity(Intent(baseContext, ConteudoActivity::class.java))
    }
}