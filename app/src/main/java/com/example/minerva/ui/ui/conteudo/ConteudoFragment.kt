package com.example.minerva.ui.ui.conteudo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.minerva.R

class ConteudoFragment : Fragment() {

    private lateinit var conteudoViewModel: ConteudoViewModel

    override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
    ): View? {
        conteudoViewModel =
            ViewModelProviders.of(this).get(ConteudoViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_conteudo, container, false)
      /*  val textView: TextView = root.findViewById(R.id.text_home)
        conteudoViewModel.text.observe(viewLifecycleOwner, Observer {
          textView.text = it
        })*/
        return root
    }
}