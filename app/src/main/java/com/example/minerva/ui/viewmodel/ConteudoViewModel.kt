package com.example.minerva.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.minerva.util.UsuarioFirebase
import com.google.firebase.auth.FirebaseAuth

class ConteudoViewModel : ViewModel() {

    private lateinit var mAuth: FirebaseAuth

    private val _text = MutableLiveData<String>().apply {
        mAuth = FirebaseAuth.getInstance()
       /* var nomes = mAuth.currentUser!!.displayName?.split(" ")
        value = "Olá, " + nomes!!.get(0) + "!"*/

        val nome = UsuarioFirebase.nomeUsuario()
        value = "Olá, $nome!"
    }
    val text: LiveData<String> = _text
}