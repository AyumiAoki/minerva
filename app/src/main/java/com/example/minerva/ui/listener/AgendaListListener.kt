package com.example.minerva.ui.listener

import android.view.View
import android.widget.Button
import com.example.minerva.service.model.LembreteModel

interface AgendaListListener {
    fun onClick(lembrete: LembreteModel )
    fun onDelete(id: Int)
    fun onClickButton(view : View)
}