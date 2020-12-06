package com.example.minerva.ui.adapter

import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.minerva.R
import com.example.minerva.service.model.LembreteModel
import com.example.minerva.ui.listener.AgendaListListener
import com.example.minerva.ui.viewholder.AgendaViewHolder

class AgendaAdapter : RecyclerView.Adapter<AgendaViewHolder>(), PopupMenu.OnMenuItemClickListener {

    private var mLembreteList: List<LembreteModel> = arrayListOf()
    private lateinit var mListListener: AgendaListListener

    /**
     * Faz a criação do layout da linha
     * Faz a criação de várias linhas que vão mostrar cada um dos convidados
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgendaViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.row_agenda, parent, false)
        return AgendaViewHolder(item, mListListener)
    }

    /**
     * Qual o tamanho da RecyclerView
     */
    override fun getItemCount(): Int {
        return mLembreteList.count()
    }

    /**
     * Para cada linha, este método é chamado
     * É responsável por atribuir os valores de cada item para uma linha específica
     */
    override fun onBindViewHolder(holder: AgendaViewHolder, position: Int) {
        holder.bind(mLembreteList[position])
        holder.buttonOptions.setOnClickListener {

            val popup = PopupMenu(it.context, it)

            popup.setOnMenuItemClickListener(this)
            val inflater: MenuInflater = popup.menuInflater
            inflater.inflate(R.menu.button_opcoes_agenda, popup.menu)
            popup.show()
        }
    }

    /**
     * Atualização da lista de convidados
     */
    fun updateNota(list: List<LembreteModel>) {
        mLembreteList = list
        notifyDataSetChanged()
    }

    /**
     * Eventos na listagem
     */
    fun attachListener(listListener: AgendaListListener) {
        mListListener = listListener
    }

    override fun onMenuItemClick(opcao: MenuItem): Boolean {
        return when(opcao.itemId){
            R.id.opcao_editar -> {
                println("Excluir")
             //   println(opcao.)
                true
            }
            R.id.opcao_excluir ->{
                println("Editar")
                true
            }
            else ->
                 false
        }
    }

}