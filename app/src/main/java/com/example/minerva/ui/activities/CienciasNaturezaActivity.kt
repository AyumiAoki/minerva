package com.example.minerva.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.minerva.R
import kotlinx.android.synthetic.main.activity_ciencias_natureza.*

class CienciasNaturezaActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (supportActionBar != null) {
            supportActionBar!!.title = "Ciências da Natureza"
            supportActionBar!!.setDisplayHomeAsUpEnabled(true) //Mostrar o botão
            supportActionBar!!.setHomeButtonEnabled(true)      //Ativar o botão
        }

        setContentView(R.layout.activity_ciencias_natureza)

        button_quimica.setOnClickListener(this)
        button_biologia.setOnClickListener(this)
        button_fisica.setOnClickListener(this)
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