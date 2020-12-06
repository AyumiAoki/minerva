package com.example.minerva.ui.viewholder

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.minerva.R
import com.example.minerva.service.constants.CoresNotasConstants
import com.example.minerva.service.model.LembreteModel
import com.example.minerva.ui.listener.AgendaListListener

class AgendaViewHolder(itemView: View, private val listListener: AgendaListListener) :
    RecyclerView.ViewHolder(itemView) {

    lateinit var buttonOptions: ImageView
    fun bind(lembrete: LembreteModel) {

        // Obtém os elementos de interface
        val horario = itemView.findViewById<TextView>(R.id.text_view_horas)
        val conteudo = itemView.findViewById<TextView>(R.id.text_view_conteudo_agenda)
        val imagemCor = itemView.findViewById<ImageView>(R.id.circle_color)
        buttonOptions = itemView.findViewById(R.id.button_opcoes_lembrete)
        val buttonLembrete = itemView.findViewById<View>(R.id.button_agenda)

        // Atribui valores
        val minuto = if(lembrete.minuto < 10) "0${lembrete.minuto}" else "${lembrete.minuto}"
        horario.text = "${lembrete.hora}:${minuto}"
        conteudo.text = "${lembrete.titulo}"

        buttonLembrete.setOnClickListener{
            listListener.onClick(lembrete)
        }
      /*  buttonOptions.setOnClickListener {
            println("hihihihi")
            listListener.onClickButton(it)
        }*/

         try {
          //  layout.setBackgroundColor(Color.parseColor(nota.cor))
            //println(nota.cor)
            when (lembrete.cor) {
                CoresNotasConstants.AMARELO -> {
                    imagemCor.imageTintList = ColorStateList.valueOf(Color.parseColor(CoresNotasConstants.AMARELO_ESCURO))
                }
                CoresNotasConstants.AZUL -> {
                    imagemCor.imageTintList = ColorStateList.valueOf(Color.parseColor(CoresNotasConstants.AZUL_ESCURO))
                }
                CoresNotasConstants.VERMELHO -> {
                    imagemCor.imageTintList = ColorStateList.valueOf(Color.parseColor(CoresNotasConstants.VERMELHO_ESCURO))
                }
                else -> {
                    imagemCor.imageTintList = ColorStateList.valueOf(Color.parseColor(CoresNotasConstants.VERDE_ESCURO))
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}