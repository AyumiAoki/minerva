package com.example.minerva.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.minerva.service.model.NotaModel
import com.example.minerva.service.repository.NotaRepository

class AnotacaoViewModel(application: Application) : AndroidViewModel(application) {

    private val mNotaRepository = NotaRepository.getInstance(application.applicationContext)

    private val mNotaList = MutableLiveData<List<NotaModel>>()
    val notaList: LiveData<List<NotaModel>> = mNotaList

    fun load() {
        println("AAAAAAAAAAAQUIIIIIIIIIIIIIII")
        mNotaList.value = mNotaRepository.getAll()
    }

    fun delete(id: Int) {
        mNotaRepository.delete(id)
    }

}