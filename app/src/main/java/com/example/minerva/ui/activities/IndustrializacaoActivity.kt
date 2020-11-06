package com.example.minerva.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.minerva.R

class IndustrializacaoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (supportActionBar != null) {
            supportActionBar!!.title = "Industrializacao"
            supportActionBar!!.setDisplayHomeAsUpEnabled(true); //Mostrar o botão
            supportActionBar!!.setHomeButtonEnabled(true);      //Ativar o botão
        }
        setContentView(R.layout.activity_industrializacao)


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean { //Botão adicional na ToolBar
        when (item.getItemId()) {
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