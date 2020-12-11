package com.example.minerva.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.minerva.R
import com.example.minerva.ui.fragments.perguntas.humanas.Pergunta1Fragment

class ExerciciosChActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercicios_ch)

        //coloca o fragment
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.viewPage, Pergunta1Fragment())
        transaction.commit()

        if (supportActionBar != null) {

            supportActionBar!!.elevation = 0f

            supportActionBar!!.setDisplayHomeAsUpEnabled(true) //Mostrar o botão
            supportActionBar!!.setHomeButtonEnabled(true)      //Ativar o botão
            supportActionBar!!.setBackgroundDrawable(getDrawable(R.drawable.toolbar_ciencias_humanas))
            supportActionBar!!.title = "Exercicíos"
        }
    }
}