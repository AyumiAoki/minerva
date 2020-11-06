package com.example.minerva.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.minerva.R
import com.example.minerva.ui.viewmodel.ConteudoViewModel

class ConteudoFragment : Fragment() {

    private lateinit var conteudoViewModel: ConteudoViewModel
    private lateinit var mListener: CreateConteudoLiestener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        conteudoViewModel = ViewModelProviders.of(this).get(ConteudoViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_conteudo, container, false)
        val textView: TextView = root.findViewById(R.id.text_nome_usuario)
        conteudoViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        val buttonCienciasHumanas = root.findViewById<View>(R.id.button_ciencias_humanas)
        buttonCienciasHumanas.setOnClickListener(View.OnClickListener {
            mListener.onCreateConteudo(1)
        })

        val buttonCienciasNatureza = root.findViewById<View>(R.id.button_ciencias_natureza)
        buttonCienciasNatureza.setOnClickListener(View.OnClickListener {
            mListener.onCreateConteudo(2)
        })

        val buttonLinguagens = root.findViewById<View>(R.id.button_lingugens)
        buttonLinguagens.setOnClickListener(View.OnClickListener {
            mListener.onCreateConteudo(3)
        })

        val buttonMatematica = root.findViewById<View>(R.id.button_matematica)
        buttonMatematica.setOnClickListener(View.OnClickListener {
            mListener.onCreateConteudo(4)
        })

        return root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mListener = try {
            activity as CreateConteudoLiestener
        } catch (e: ClassCastException) {
            throw ClassCastException(activity.toString() + " deve implementar CreateEmailListener")
        }
    }

    interface CreateConteudoLiestener{
         fun onCreateConteudo(codigo: Int)
    }
}