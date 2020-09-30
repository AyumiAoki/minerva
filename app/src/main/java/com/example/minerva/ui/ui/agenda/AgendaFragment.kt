package com.example.minerva.ui.ui.agenda

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.minerva.R

class AgendaFragment : Fragment() {

  private lateinit var agendaViewModel: AgendaViewModel

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    agendaViewModel =
    ViewModelProviders.of(this).get(AgendaViewModel::class.java)
    val root = inflater.inflate(R.layout.fragment_agenda, container, false)
    val textView: TextView = root.findViewById(R.id.text_notifications)
    agendaViewModel.text.observe(viewLifecycleOwner, Observer {
      textView.text = it
    })
    return root
  }
}