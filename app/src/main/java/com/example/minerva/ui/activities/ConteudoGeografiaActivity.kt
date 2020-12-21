package com.example.minerva.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import com.example.minerva.R
import com.example.minerva.service.model.AssuntoModel
import com.example.minerva.service.model.MateriaModel
import com.example.minerva.ui.activities.conteudos.AugusteComteActivity
import com.example.minerva.ui.activities.conteudos.DemografiaActivity
import com.example.minerva.ui.activities.conteudos.WeberActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_auguste_comte.*
import kotlinx.android.synthetic.main.activity_conteudo_filosofia.*
import kotlinx.android.synthetic.main.activity_conteudo_sociologia.*

class ConteudoGeografiaActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val nomeMateria = "Geografia"

        val reference = FirebaseDatabase.getInstance().reference
        val m : DatabaseReference = reference.child("usuarios").child(FirebaseAuth.getInstance().currentUser!!.uid).child("materias").child("Geografia")
        var qntdRevisado = ""
        var subtitulo = ""

        m.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                qntdRevisado = snapshot.child("qntdRevisados").getValue().toString()
                subtitulo = qntdRevisado + " de 10 assuntos revisados"
                if (supportActionBar != null) {
                    supportActionBar!!.title = nomeMateria
                    supportActionBar!!.subtitle = subtitulo
                    supportActionBar!!.setBackgroundDrawable(getDrawable(R.drawable.toolbar_ciencias_humanas))
                    supportActionBar!!.setDisplayHomeAsUpEnabled(true) //Mostrar o botão
                    supportActionBar!!.setHomeButtonEnabled(true)      //Ativar o botão
                }
            }
        })

        setContentView(R.layout.activity_conteudo_geografia)

        button_1.setOnClickListener(this)
        button_2.setOnClickListener(this)
        button_3.setOnClickListener(this)
        button_4.setOnClickListener(this)
        button_5.setOnClickListener(this)
        button_6.setOnClickListener(this)
        button_7.setOnClickListener(this)
        button_8.setOnClickListener(this)
        button_9.setOnClickListener(this)
        button_10.setOnClickListener(this)
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
            R.id.button_1 -> {
                startActivity(Intent(baseContext, DemografiaActivity::class.java))
            }
            R.id.button_2 -> {
                startActivity(Intent(baseContext, WeberActivity::class.java))
            }
            R.id.button_3 -> {
                startActivity(Intent(baseContext, AugusteComteActivity::class.java))
            }
            R.id.button_4 -> {
                startActivity(Intent(baseContext, AugusteComteActivity::class.java))
            }
            R.id.button_5 -> {
                startActivity(Intent(baseContext, AugusteComteActivity::class.java))
            }
            R.id.button_6 -> {
                startActivity(Intent(baseContext, AugusteComteActivity::class.java))
            }
            R.id.button_7 -> {
                startActivity(Intent(baseContext, AugusteComteActivity::class.java))
            }
            R.id.button_8 -> {
                startActivity(Intent(baseContext, AugusteComteActivity::class.java))
            }
            R.id.button_9 -> {
                startActivity(Intent(baseContext, AugusteComteActivity::class.java))
            }
            R.id.button_10 -> {
                startActivity(Intent(baseContext, AugusteComteActivity::class.java))
            }
            else -> {
                startActivity(Intent(baseContext, ConteudoActivity::class.java))
            }
        }
    }
}