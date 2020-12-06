package com.example.minerva.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.minerva.R
import kotlinx.android.synthetic.main.activity_ciencias_humanas.*


class CienciasHumanasActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (supportActionBar != null) {

            supportActionBar!!.elevation = 0f

            supportActionBar!!.setDisplayHomeAsUpEnabled(true) //Mostrar o botão
            supportActionBar!!.setHomeButtonEnabled(true)      //Ativar o botão
            supportActionBar!!.setBackgroundDrawable(getDrawable(R.drawable.toolbar_ciencias_humanas))
            supportActionBar!!.title = "Ciências Humanas"
        }



        setContentView(R.layout.activity_ciencias_humanas)
        button_geografia.setOnClickListener(this)
        button_filosofia.setOnClickListener(this)
        button_historia.setOnClickListener(this)
        button_sociologia.setOnClickListener(this)
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
        if(view.id == R.id.button_geografia)
            startActivity(Intent(baseContext, ConteudoGeografiaActivity::class.java))
        else
            startActivity(Intent(baseContext, ConteudoActivity::class.java))
    }
}