package com.example.minerva.ui.activities.conteudos

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import com.example.minerva.R
import com.example.minerva.service.constants.CoresNotasConstants
import com.example.minerva.service.model.AssuntoModel
import com.example.minerva.service.model.MateriaModel
import com.example.minerva.service.model.NotaListModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_auguste_comte.*
import java.util.ArrayList

class WeberActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (supportActionBar != null) {
            supportActionBar!!.title = "Max Weber"
            supportActionBar!!.setDisplayHomeAsUpEnabled(true) //Mostrar o botão
            supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor(CoresNotasConstants.AZUL)))
            supportActionBar!!.setHomeButtonEnabled(true)    //Ativar o botão
            supportActionBar!!.setBackgroundDrawable(
                ColorDrawable(
                    Color.parseColor(
                        CoresNotasConstants.AZUL))
            )
        }
        setContentView(R.layout.activity_weber)

        val reference = FirebaseDatabase.getInstance().reference
        val m : DatabaseReference = reference.child("usuarios").child(FirebaseAuth.getInstance().currentUser!!.uid).child("materias").child("Sociologia")
        var assunto = AssuntoModel("Max Weber", "")

        m.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val revisado = snapshot.child("assunto2").child("revisado").getValue().toString()

                if(snapshot.child("assunto2").exists()){
                    if(revisado.equals("sim"))
                        button_revisao.setText("Revisado")
                } else{
                    reference.child("usuarios").child(FirebaseAuth.getInstance().currentUser!!.uid)
                        .child("materias").child("Sociologia").child("assunto2").setValue(assunto)
                }
            }
        })

        button_revisao.setOnClickListener {
            m.addValueEventListener(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
                override fun onDataChange(snapshot: DataSnapshot) {
                    val revisado = snapshot.child("assunto2").child("revisado").getValue().toString()
                    val qntdRevisado = snapshot.child("qntdRevisados").getValue().toString()

                    if (revisado.equals("sim")) {
                        button_revisao.setText("Revisado")
                    } else {
                        val qntd = qntdRevisado.toInt() + 1
                        assunto = AssuntoModel("Max Weber", "sim")

                        reference.child("usuarios")
                            .child(FirebaseAuth.getInstance().currentUser!!.uid)
                            .child("materias").child("Sociologia").child("assunto2").setValue(assunto)

                        reference.child("usuarios").child(FirebaseAuth.getInstance().currentUser!!.uid)
                            .child("materias").child("Sociologia").child("qntdRevisados").setValue(qntd)
                    }
                }
            })
        }
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
}
