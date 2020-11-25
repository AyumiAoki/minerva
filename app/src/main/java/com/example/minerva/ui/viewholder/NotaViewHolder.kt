package com.example.minerva.ui.viewholder

import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.minerva.R
import com.example.minerva.service.constants.CoresNotasConstants
import com.example.minerva.service.model.NotaListModel
import com.example.minerva.ui.listener.NotaListListener

class NotaViewHolder(itemView: View, private val listListener: NotaListListener) :
    RecyclerView.ViewHolder(itemView) {

    fun bind(nota: NotaListModel) {

        // Obtém os elementos de interface
        val textTitulo = itemView.findViewById<TextView>(R.id.text_titulo_nota)
        val textConteudo = itemView.findViewById<TextView>(R.id.text_conteudo_nota)
        val buttonNota = itemView.findViewById<CardView>(R.id.button_nota)
        val layout = itemView.findViewById<View>(R.id.layout_nota_dentro)

        // Atribui valores
        textTitulo.text = nota.titulo
        textConteudo.text = nota.conteudo

        // Atribui eventos
        buttonNota.setOnClickListener {
            listListener.onClick(nota.idNota, nota.titulo, nota.conteudo, nota.cor)
        }
        try {
            layout.setBackgroundColor(Color.parseColor(nota.cor))
            println(nota.cor)
            when (nota.cor) {
                CoresNotasConstants.AMARELO -> {
                    buttonNota.setCardBackgroundColor(Color.parseColor(CoresNotasConstants.AMARELO_ESCURO))
                }
                CoresNotasConstants.AZUL -> {
                    buttonNota.setCardBackgroundColor(Color.parseColor(CoresNotasConstants.AZUL_ESCURO))
                }
                CoresNotasConstants.VERMELHO -> {
                    buttonNota.setCardBackgroundColor(Color.parseColor(CoresNotasConstants.VERMELHO_ESCURO))
                }
                else -> {
                    buttonNota.setCardBackgroundColor(Color.parseColor(CoresNotasConstants.VERDE_ESCURO))
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}