package com.example.minerva.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.minerva.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_ciencias_natureza.*

class CienciasNaturezaActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (supportActionBar != null) {
            supportActionBar!!.elevation = 0f
            supportActionBar!!.setDisplayHomeAsUpEnabled(true) //Mostrar o botão
            supportActionBar!!.setHomeButtonEnabled(true)      //Ativar o botão
            supportActionBar!!.setBackgroundDrawable(getDrawable(R.drawable.toolbar_ciencias_natureza))
            supportActionBar!!.title = "Ciências da Natureza"
        }

        setContentView(R.layout.activity_ciencias_natureza)

        val reference = FirebaseDatabase.getInstance().reference
        val m : DatabaseReference = reference.child("usuarios").child(FirebaseAuth.getInstance().currentUser!!.uid).child("materias")

        m.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.child("Biologia").exists()){
                    val qntdRevisado1 = snapshot.child("Biologia").child("qntdRevisados").getValue().toString()
                    text_view_terafas_completas_biologia.setText(qntdRevisado1)

                    val qntdRevisado2 = snapshot.child("Quimica").child("qntdRevisados").getValue().toString()
                    text_view_tarefas_completas_quimica.setText(qntdRevisado2)

                    val qntdRevisado3 = snapshot.child("Fisica").child("qntdRevisados").getValue().toString()
                    text_view_tarefas_completas_fisica.setText(qntdRevisado3)

                } else {
                    reference.child("usuarios").child(FirebaseAuth.getInstance().currentUser!!.uid)
                        .child("materias").child("Biologia").child("qntdRevisados").setValue(0)

                    reference.child("usuarios").child(FirebaseAuth.getInstance().currentUser!!.uid)
                        .child("materias").child("Quimica").child("qntdRevisados").setValue(0)

                    reference.child("usuarios").child(FirebaseAuth.getInstance().currentUser!!.uid)
                        .child("materias").child("Fisica").child("qntdRevisados").setValue(0)

                    val qntdRevisado1 = snapshot.child("Biologia").child("qntdRevisados").getValue().toString()
                    text_view_tarefas_biologia.setText(qntdRevisado1)

                    val qntdRevisado2 = snapshot.child("Quimica").child("qntdRevisados").getValue().toString()
                    text_view_tarefas_completas_quimica.setText(qntdRevisado2)

                    val qntdRevisado3 = snapshot.child("Fisica").child("qntdRevisados").getValue().toString()
                    text_view_tarefas_completas_fisica.setText(qntdRevisado3)
                }
            }
        })

        button_biologia.setOnClickListener(this)
        button_quimica.setOnClickListener(this)
        button_fisica.setOnClickListener(this)
        button_exercicios_ciencias_natureza.setOnClickListener(this)
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
            R.id.button_biologia -> {
                startActivity(Intent(baseContext, ConteudoBiologiaActivity::class.java))
            }
            R.id.button_quimica -> {
                startActivity(Intent(baseContext, ConteudoQuimicaActivity::class.java))
            }
            R.id.button_fisica -> {
                startActivity(Intent(baseContext, ConteudoFisicaActivity::class.java))
            }
            R.id.button_exercicios_ciencias_natureza -> {
                startActivity(Intent(baseContext, ExerciciosCnActivity::class.java))
            }
            else ->{
                startActivity(Intent(baseContext, ConteudoActivity::class.java))
            }
        }
    }
}