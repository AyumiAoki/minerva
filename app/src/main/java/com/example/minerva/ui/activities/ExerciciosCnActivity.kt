package com.example.minerva.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.minerva.R
import com.example.minerva.ui.fragments.perguntas.natureza.Pergunta1Fragment

class ExerciciosCnActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercicios_cn)

        //coloca o fragment
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.viewPageCn, Pergunta1Fragment())
        transaction.commit()
    }
}