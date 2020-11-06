package com.example.minerva.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.minerva.R
import com.example.minerva.service.model.NotaModel
import com.example.minerva.ui.listener.NotaListener
import com.example.minerva.ui.viewholder.NotaViewHolder

class NotaAdapter : RecyclerView.Adapter<NotaViewHolder>() {

    // Lista de convidados
    private var mNotaList: List<NotaModel> = arrayListOf()
    private lateinit var mListener: NotaListener

    /**
     * Faz a criação do layout da linha
     * Faz a criação de várias linhas que vão mostrar cada um dos convidados
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotaViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.row_nota, parent, false)
        return NotaViewHolder(item, mListener)
    }

    /**
     * Qual o tamanho da RecyclerView
     */
    override fun getItemCount(): Int {
        return mNotaList.count()
    }

    /**
     * Para cada linha, este método é chamado
     * É responsável por atribuir os valores de cada item para uma linha específica
     */
    override fun onBindViewHolder(holder: NotaViewHolder, position: Int) {
        holder.bind(mNotaList[position])
    }

    /**
     * Atualização da lista de convidados
     */
    fun updateNota(list: List<NotaModel>) {
        mNotaList = list
        notifyDataSetChanged()
    }

    /**
     * Eventos na listagem
     */
    fun attachListener(listener: NotaListener) {
        mListener = listener
    }


}