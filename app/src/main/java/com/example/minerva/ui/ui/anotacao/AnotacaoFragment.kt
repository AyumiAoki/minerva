package com.example.minerva.ui.ui.anotacao

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.minerva.R

class AnotacaoFragment : Fragment() {

    private lateinit var anotacaoViewModel: AnotacaoViewModel

    override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
    ): View? {
        anotacaoViewModel =
            ViewModelProviders.of(this).get(AnotacaoViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_anotacao, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        anotacaoViewModel.text.observe(viewLifecycleOwner, Observer {
          textView.text = it
        })
        return root
    }
}