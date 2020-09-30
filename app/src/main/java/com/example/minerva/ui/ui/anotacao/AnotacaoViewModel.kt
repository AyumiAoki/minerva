package com.example.minerva.ui.ui.anotacao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AnotacaoViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is anotacao Fragment"
    }
    val text: LiveData<String> = _text
}