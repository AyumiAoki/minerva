package com.example.minerva.util

import android.net.Uri
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest

object UsuarioFirebase {
    val identificadorUsuario: String
        get() {
            val usuario: FirebaseAuth = ConfiguracaoFirebase.firebaseAutenticacao!!
          //  val email = usuario.currentUser!!.email
            return usuario.uid.toString()
        }

    val usuarioAtual: FirebaseUser?
        get() {
            val usuario: FirebaseAuth = ConfiguracaoFirebase.firebaseAutenticacao!!
            return usuario.currentUser
        }

    fun atualizarNomeUsuario(nome: String?): Boolean {
        return try {
            val user = usuarioAtual
            val profile = UserProfileChangeRequest.Builder()
                .setDisplayName(nome)
                .build()
            user!!.updateProfile(profile).addOnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.d("Perfil", "Erro ao atualizar nome de perfil.")
                }
            }
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    fun atualizarFotoUsuario(url: Uri?): Boolean {
        return try {
            val user = usuarioAtual
            val profile = UserProfileChangeRequest.Builder()
                .setPhotoUri(url)
                .build()
            user!!.updateProfile(profile).addOnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.d("Perfil", "Erro ao atualizar foto de perfil.")
                }
            }
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    fun nomeUsuario():String{
        if(usuarioAtual!!.isAnonymous){
            return "Visitante"
        }else{
            return usuarioAtual?.displayName ?: "Visitante"
        }
    }
}
