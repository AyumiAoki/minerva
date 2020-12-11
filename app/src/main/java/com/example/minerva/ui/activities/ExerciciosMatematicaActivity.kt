package com.example.minerva.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.minerva.R
import com.example.minerva.ui.fragments.perguntas.matematica.Pergunta1Fragment

class ExerciciosMatematicaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercicios_matematica)

        //coloca o fragment
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.viewPageMatematica, Pergunta1Fragment())
        transaction.commit()
    }
}