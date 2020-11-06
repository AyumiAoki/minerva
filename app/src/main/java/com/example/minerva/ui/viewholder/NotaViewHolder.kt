package com.example.minerva.ui.viewholder

import android.app.AlertDialog
import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.minerva.R
import com.example.minerva.service.model.NotaModel
import com.example.minerva.ui.listener.NotaListener
import kotlinx.android.synthetic.main.row_nota.view.*

class NotaViewHolder(itemView: View, private val listener: NotaListener) :
    RecyclerView.ViewHolder(itemView) {

    fun bind(nota: NotaModel) {

        // Obtém os elementos de interface
        val textTitulo = itemView.findViewById<TextView>(R.id.text_titulo_nota)
        val textConteudo = itemView.findViewById<TextView>(R.id.text_conteudo_nota)
        val buttonNota = itemView.findViewById<CardView>(R.id.button_nota)

        // Atribui valores
        textTitulo.text = nota.titulo
        textConteudo.text = nota.texto

        // Atribui eventos
        buttonNota.setOnClickListener {
            listener.onClick(nota.id)
        }

        // Atribui eventos
        buttonNota.setOnLongClickListener {
            AlertDialog.Builder(itemView.context)
                .setTitle("Remoção de nota")
                .setMessage("Deseja mesmo remover a nota?")
                .setPositiveButton("Remover") { dialog, which ->
                    listener.onDelete(nota.id)
                }
                .setNeutralButton("Cancelar", null)
                .show()

            true
        }

    }
}