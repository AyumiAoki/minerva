package com.example.minerva.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.minerva.service.model.NotaModel
import com.example.minerva.service.repository.NotaRepository
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class CadastroNotaViewModel(application: Application) : AndroidViewModel(application) {

    // Contexto e acesso a dados
    private val mContext = application.applicationContext
    private val mGuestRepository: NotaRepository = NotaRepository.getInstance(mContext)

    private var mSaveGuest = MutableLiveData<Boolean>()
    val saveGuest: LiveData<Boolean> = mSaveGuest

    private var mGuest = MutableLiveData<NotaModel>()
    val guest: LiveData<NotaModel> = mGuest

    /**
     * Salva convidado
     * */
    fun save(id: Int, titulo: String, conteudo: String) {
        val guest = NotaModel(id, titulo, conteudo)

      //  if (id == 0) {
            mSaveGuest.value = mGuestRepository.save(guest)
           val reference = FirebaseDatabase.getInstance().reference

        //} else {
           // mSaveGuest.value = mGuestRepository.update(guest)
      //  }
    }

    /**
     * Carrega convidado
     * */
    fun load(id: Int) {
        mGuest.value = mGuestRepository.get(id)
    }

}