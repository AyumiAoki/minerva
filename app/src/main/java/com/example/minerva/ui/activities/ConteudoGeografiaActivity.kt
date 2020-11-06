package com.example.minerva.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.minerva.R
import kotlinx.android.synthetic.main.activity_consteudo_geografia.*

class ConteudoGeografiaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (supportActionBar != null) {
            supportActionBar!!.title = "Conteúdo"
            supportActionBar!!.setDisplayHomeAsUpEnabled(true) //Mostrar o botão
            supportActionBar!!.setHomeButtonEnabled(true)      //Ativar o botão
        }


        setContentView(R.layout.activity_consteudo_geografia)
        button_industrializacao.setOnClickListener{
            startActivity(Intent(baseContext, IndustrializacaoActivity::class.java))
        }
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
}