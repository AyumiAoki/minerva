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
import com.example.minerva.ui.activities.conteudos.WeberActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_auguste_comte.*
import kotlinx.android.synthetic.main.activity_conteudo_sociologia.*

class ConteudoSociologiaActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val nomeMateria = "Sociologia"

        val reference = FirebaseDatabase.getInstance().reference
        val m : DatabaseReference = reference.child("usuarios").child(FirebaseAuth.getInstance().currentUser!!.uid).child("materias").child("Sociologia")
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

        setContentView(R.layout.activity_conteudo_sociologia)

        button_auguste.setOnClickListener(this)
        button_weber.setOnClickListener(this)
        button_marx.setOnClickListener(this)
        button_emile.setOnClickListener(this)
        button_socio.setOnClickListener(this)
        button_cultura.setOnClickListener(this)
        button_mobilidade.setOnClickListener(this)
        button_genero.setOnClickListener(this)
        button_imigracao.setOnClickListener(this)
        button_industria.setOnClickListener(this)
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
            R.id.button_auguste -> {
                startActivity(Intent(baseContext, AugusteComteActivity::class.java))
            }
            R.id.button_weber -> {
                startActivity(Intent(baseContext, WeberActivity::class.java))
            }
            R.id.button_marx -> {
                startActivity(Intent(baseContext, AugusteComteActivity::class.java))
            }
            R.id.button_emile -> {
                startActivity(Intent(baseContext, AugusteComteActivity::class.java))
            }
            R.id.button_socio -> {
                startActivity(Intent(baseContext, AugusteComteActivity::class.java))
            }
            R.id.button_cultura -> {
                startActivity(Intent(baseContext, AugusteComteActivity::class.java))
            }
            R.id.button_mobilidade -> {
                startActivity(Intent(baseContext, AugusteComteActivity::class.java))
            }
            R.id.button_genero -> {
                startActivity(Intent(baseContext, AugusteComteActivity::class.java))
            }
            R.id.button_imigracao -> {
                startActivity(Intent(baseContext, AugusteComteActivity::class.java))
            }
            R.id.button_industria -> {
                startActivity(Intent(baseContext, AugusteComteActivity::class.java))
            }
            else -> {
                startActivity(Intent(baseContext, ConteudoActivity::class.java))
            }
        }
    }
}