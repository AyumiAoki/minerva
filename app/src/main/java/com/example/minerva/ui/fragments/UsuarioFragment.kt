package com.example.minerva.ui.fragments

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import com.example.minerva.R
import com.example.minerva.ui.viewmodel.UsuarioViewModel

class UsuarioFragment : Fragment() {

    private lateinit var usuarioViewModel: UsuarioViewModel
    private lateinit var mListener: CreateUsuarioListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        usuarioViewModel = ViewModelProviders.of(this).get(UsuarioViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_usuario, container, false)

        val textView: TextView = root.findViewById(R.id.text_nome_usuario_usuario)
        usuarioViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        val buttonSair = root.findViewById<View>(R.id.button_sair)
        buttonSair.setOnClickListener(View.OnClickListener {
            mListener.onCreateUsuario(4)
        })

        return root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mListener = try {
            activity as CreateUsuarioListener
        } catch (e: ClassCastException) {
            throw ClassCastException(activity.toString() + " deve implementar CreateEmailListener")
        }

    }

    interface CreateUsuarioListener{
        fun onCreateUsuario(codigo:Int)
    }

}