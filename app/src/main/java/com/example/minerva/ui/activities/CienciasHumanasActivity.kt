package com.example.minerva.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.minerva.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_ciencias_humanas.*


class CienciasHumanasActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_ciencias_humanas)

        val reference = FirebaseDatabase.getInstance().reference
        val m : DatabaseReference = reference.child("usuarios").child(FirebaseAuth.getInstance().currentUser!!.uid).child("materias")

        m.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val qntdRevisado1 = snapshot.child("Geografia").child("qntdRevisados").getValue().toString()
                text_view_tarefas_completas_geografia.setText(qntdRevisado1)

                val qntdRevisado2 = snapshot.child("Filosofia").child("qntdRevisados").getValue().toString()
                text_view_terafas_completas_filosofia.setText(qntdRevisado2)

                val qntdRevisado3 = snapshot.child("Historia").child("qntdRevisados").getValue().toString()
                text_view_tarefas_completas_historia.setText(qntdRevisado3)

                val qntdRevisado4 = snapshot.child("Sociologia").child("qntdRevisados").getValue().toString()
                text_view_tarefas_completas_sociologia.setText(qntdRevisado4)

            }
        })

        if (supportActionBar != null) {

            supportActionBar!!.elevation = 0f

            supportActionBar!!.setDisplayHomeAsUpEnabled(true) //Mostrar o botão
            supportActionBar!!.setHomeButtonEnabled(true)      //Ativar o botão
            supportActionBar!!.setBackgroundDrawable(getDrawable(R.drawable.toolbar_ciencias_humanas))
            supportActionBar!!.title = "Ciências Humanas"
        }

        button_geografia.setOnClickListener(this)
        button_filosofia.setOnClickListener(this)
        button_historia.setOnClickListener(this)
        button_sociologia.setOnClickListener(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean { //Botão adicional na ToolBar
        when (item.itemId) {
            android.R.id.home -> {
            }
            else -> {
            }
        }
        return true
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.button_geografia -> {
                startActivity(Intent(baseContext, ConteudoGeografiaActivity::class.java))
            }
            R.id.button_filosofia -> {
                startActivity(Intent(baseContext, ConteudoFilosofiaActivity::class.java))
            }
            R.id.button_historia -> {
                startActivity(Intent(baseContext, ConteudoSociologiaActivity::class.java))
            }
            R.id.button_sociologia -> {
                startActivity(Intent(baseContext, ConteudoSociologiaActivity::class.java))
            }
            else -> {
                startActivity(Intent(baseContext, ConteudoActivity::class.java))
            }
        }
    }
}