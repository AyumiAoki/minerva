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
            supportActionBar!!.elevation = 0f
            supportActionBar!!.setDisplayHomeAsUpEnabled(true) //Mostrar o botão
            supportActionBar!!.setHomeButtonEnabled(true)      //Ativar o botão

            supportActionBar!!.setBackgroundDrawable(getDrawable(R.drawable.toolbar_linguagem))
            supportActionBar!!.title = "Linguagens e Códigos"
        }
        setContentView(R.layout.activity_linguagem_codigo)

        button_ingles.setOnClickListener(this)
        button_conteudo_linguagem.setOnClickListener(this)
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