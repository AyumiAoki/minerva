package com.example.minerva.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.minerva.util.UsuarioFirebase
import com.google.firebase.auth.FirebaseAuth
import de.hdodenhof.circleimageview.CircleImageView

class UsuarioViewModel : ViewModel() {
    private var mAuth: FirebaseAuth

    private val _text = MutableLiveData<String>().apply {
        mAuth = FirebaseAuth.getInstance()
        /* var nomes = mAuth.currentUser!!.displayName?.split(" ")
         value = "Olá, " + nomes!!.get(0) + "!"*/

        val nome = UsuarioFirebase.nomeUsuario()
        value = nome
    }
   /* val text: LiveData<String> = _text

    private val _image = MutableLiveData<CircleImageView>().apply {
        mAuth = FirebaseAuth.getInstance()
        /* var nomes = mAuth.currentUser!!.displayName?.split(" ")
         value = "Olá, " + nomes!!.get(0) + "!"*/

        value = mAuth.currentUser!!.displayName
    }
    val text: LiveData<String> = _text*/
}