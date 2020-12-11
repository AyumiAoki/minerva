package com.example.minerva.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.minerva.R
import com.example.minerva.ui.fragments.perguntas.linguagem.Pergunta1Fragment

class ExerciciosLinguagemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercicios_linguagem)

        //coloca o fragment
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.viewPageLinguagem, Pergunta1Fragment())
        transaction.commit()
    }
}