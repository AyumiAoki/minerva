package com.example.minerva.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.example.minerva.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_ciencias_natureza.*
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

        val reference = FirebaseDatabase.getInstance().reference
        val m : DatabaseReference = reference.child("usuarios").child(FirebaseAuth.getInstance().currentUser!!.uid).child("materias")

        m.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.child("Literatura").exists()){
                    val qntdRevisado1 = snapshot.child("Literatura").child("qntdRevisados").getValue().toString()
                    text_view_tarefas_completas_literatura.setText(qntdRevisado1)

                    val qntdRevisado2 = snapshot.child("Portugues").child("qntdRevisados").getValue().toString()
                    text_view_tarefas_completas_linguagem.setText(qntdRevisado2)

                    val qntdRevisado3 = snapshot.child("Ingles").child("qntdRevisados").getValue().toString()
                    text_view_terafas_completas_ingles.setText(qntdRevisado3)

                } else {
                    reference.child("usuarios").child(FirebaseAuth.getInstance().currentUser!!.uid)
                        .child("materias").child("Literatura").child("qntdRevisados").setValue(0)

                    reference.child("usuarios").child(FirebaseAuth.getInstance().currentUser!!.uid)
                        .child("materias").child("Portugues").child("qntdRevisados").setValue(0)

                    reference.child("usuarios").child(FirebaseAuth.getInstance().currentUser!!.uid)
                        .child("materias").child("Ingles").child("qntdRevisados").setValue(0)

                    val qntdRevisado1 = snapshot.child("Literatura").child("qntdRevisados").getValue().toString()
                    text_view_tarefas_completas_literatura.setText(qntdRevisado1)

                    val qntdRevisado2 = snapshot.child("Portugues").child("qntdRevisados").getValue().toString()
                    text_view_tarefas_completas_linguagem.setText(qntdRevisado2)

                    val qntdRevisado3 = snapshot.child("Ingles").child("qntdRevisados").getValue().toString()
                    text_view_terafas_completas_ingles.setText(qntdRevisado3)
                }
            }
        })

        button_ingles.setOnClickListener(this)
        button_conteudo_linguagem.setOnClickListener(this)
        button_literatura.setOnClickListener(this)
        button_exercicios_linguagens.setOnClickListener(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean { //Botão adicional na ToolBar
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
            else -> {
            }
        }
        return true
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.button_ingles -> {
                startActivity(Intent(baseContext, ConteudoInglesActivity::class.java))
            }
            R.id.button_conteudo_linguagem -> {
                startActivity(Intent(baseContext, ConteudoPortuguesActivity::class.java))
            }
            R.id.button_literatura -> {
                    startActivity(Intent(baseContext, ConteudoLiteraturaActivity::class.java))
            }
            R.id.button_exercicios_linguagens -> {
                startActivity(Intent(baseContext, ExerciciosLinguagemActivity::class.java))
            }
            else ->{
                startActivity(Intent(baseContext, ConteudoActivity::class.java))
            }
        }
    }
}