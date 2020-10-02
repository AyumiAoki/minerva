package com.example.minerva.ui.ui.conteudo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class ConteudoViewModel : ViewModel() {

    private lateinit var mAuth: FirebaseAuth

    private val _text = MutableLiveData<String>().apply {
        mAuth = FirebaseAuth.getInstance()
        value = "Olá, " + mAuth.currentUser!!.displayName + "!"
    }
    val text: LiveData<String> = _text
}