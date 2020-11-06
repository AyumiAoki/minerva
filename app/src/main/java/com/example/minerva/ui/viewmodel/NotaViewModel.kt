package com.example.minerva.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.minerva.service.model.NotaModel
import com.example.minerva.service.repository.NotaRepository

class NotaViewModel(application: Application) : AndroidViewModel(application) {

    // Contexto e acesso a dados
    private val mContext = application.applicationContext
    private val mGuestRepository: NotaRepository = NotaRepository.getInstance(mContext)

    private var mSaveGuest = MutableLiveData<Boolean>()
    val saveGuest: LiveData<Boolean> = mSaveGuest

    private var mGuest = MutableLiveData<NotaModel>()
    val guest: LiveData<NotaModel> = mGuest


    /**
     * Carrega convidado
     * */
    fun load(id: Int) {
        mGuest.value = mGuestRepository.get(id)
    }

}