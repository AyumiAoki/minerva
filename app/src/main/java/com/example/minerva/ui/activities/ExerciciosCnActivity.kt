package com.example.minerva.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.minerva.R
import com.example.minerva.ui.fragments.perguntas.natureza.Pergunta1Fragment

class ExerciciosCnActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercicios_cn)

        if (supportActionBar != null) {

            supportActionBar!!.elevation = 0f

            supportActionBar!!.setDisplayHomeAsUpEnabled(true) //Mostrar o botão
            supportActionBar!!.setHomeButtonEnabled(true)      //Ativar o botão
            supportActionBar!!.setBackgroundDrawable(getDrawable(R.drawable.toolbar_ciencias_natureza))
            supportActionBar!!.title = "Exercicíos"
        }

        //coloca o fragment
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.viewPageCn, Pergunta1Fragment())
        transaction.commit()
    }
}