package com.example.minerva.ui.ui.conteudo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ConteudoViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is conteudo Fragment"
    }
    val text: LiveData<String> = _text
}