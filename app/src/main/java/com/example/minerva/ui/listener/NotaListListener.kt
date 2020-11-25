package com.example.minerva.ui.listener

interface NotaListListener {
    fun onClick(idNota: String, titulo: String, conteudo:String, cor:String)
    fun onDelete(id: String)
}