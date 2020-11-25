package com.example.minerva.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.minerva.service.model.NotaModel

class AnotacaoViewModel(application: Application) : AndroidViewModel(application) {

    private val mNotaList = MutableLiveData<List<NotaModel>>()
    val notaList: LiveData<List<NotaModel>> = mNotaList

}