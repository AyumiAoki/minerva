package com.example.minerva.ui.activities;

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem
import android.view.View
import com.example.minerva.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_linguagem_codigo.*
import kotlinx.android.synthetic.main.activity_matematica.*

class MatematicaActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (supportActionBar != null) {
            supportActionBar!!.elevation = 0f
            supportActionBar!!.setDisplayHomeAsUpEnabled(true) //Mostrar o botão
            supportActionBar!!.setHomeButtonEnabled(true)      //Ativar o botão
            supportActionBar!!.setBackgroundDrawable(getDrawable(R.drawable.toolbar_humanas))
            supportActionBar!!.title = "Matemática"
        }

        setContentView(R.layout.activity_matematica)

        val reference = FirebaseDatabase.getInstance().reference
        val m : DatabaseReference = reference.child("usuarios").child(FirebaseAuth.getInstance().currentUser!!.uid).child("materias")

        m.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.child("Matematica").exists()){
                    val qntdRevisado1 = snapshot.child("Matematica").child("qntdRevisados").getValue().toString()
                    text_view_terafas_completas_matematica.setText(qntdRevisado1)

                } else {
                    reference.child("usuarios").child(FirebaseAuth.getInstance().currentUser!!.uid)
                        .child("materias").child("Matematica").child("qntdRevisados").setValue(0)

                    val qntdRevisado1 = snapshot.child("Matematica").child("qntdRevisados").getValue().toString()
                    text_view_terafas_completas_matematica.setText(qntdRevisado1)
                }
            }
        })

        button_conteudo_matematica.setOnClickListener(this)
        button_exercicios_matematica.setOnClickListener(this)
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
        when (view.id) {
            R.id.button_conteudo_matematica -> {
                startActivity(Intent(baseContext, ConteudoMatematicaActivity::class.java))
            }
            R.id.button_exercicios_matematica -> {
                startActivity(Intent(baseContext, ExerciciosMatematicaActivity::class.java))
            }
            else ->{
                startActivity(Intent(baseContext, ConteudoActivity::class.java))
            }
        }
    }
}