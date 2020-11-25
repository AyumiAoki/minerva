package com.example.minerva.ui.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.minerva.R
import com.example.minerva.service.constants.CoresNotasConstants

class SelecaoCoresFragment : DialogFragment() {

    var azul: View? = null
    var verde: View? = null
    var amarelo: View? = null
    var vermelho: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    interface SelecaoCores{
        fun onCor(cor : String) // metodo que sera chamado para retornar o resultado da operação
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        var _view: View = requireActivity().getLayoutInflater().inflate(R.layout.fragment_selecao_cores, null)
        this.azul = _view.findViewById(R.id.azul)
        this.verde = _view.findViewById(R.id.verde)
        this.amarelo = _view.findViewById(R.id.amarelo)
        this.vermelho = _view.findViewById(R.id.vermelho)


        var alert = AlertDialog.Builder(activity)
        alert.setView(_view)

        this.azul!!.setOnClickListener{
            (activity as (SelecaoCores)).onCor(CoresNotasConstants.AZUL)
            dismiss()
        }

        this.vermelho!!.setOnClickListener{
            (activity as (SelecaoCores)).onCor(CoresNotasConstants.VERMELHO)
            dismiss()
        }

        this.verde!!.setOnClickListener{
            (activity as (SelecaoCores)).onCor(CoresNotasConstants.VERDE)
            dismiss()
        }

        this.amarelo!!.setOnClickListener{
            (activity as (SelecaoCores)).onCor(CoresNotasConstants.AMARELO)
            dismiss()
        }

        return alert.create()
    }
}