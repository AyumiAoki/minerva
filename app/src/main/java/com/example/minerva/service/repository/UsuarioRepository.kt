package com.example.minerva.service.repository
import android.content.Context
import android.content.Intent
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class UsuarioRepository {
    companion object {
        fun deslogar(auth: FirebaseAuth) {
            auth.signOut()
        }/*
        fun login(auth: FirebaseAuth,context: Context, email: String, senha: String): Int{

            auth.run {
                signInWithEmailAndPassword(
                        email,
                        senha
                    ).addOnCompleteListener(context.applicationContext, OnCompleteListener {

                    })
            }
            /*addOnCompleteListener(context) {

            }*/
        }*/
    }
}