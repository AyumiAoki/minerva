package com.example.minerva.ui.ui.usuario

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.minerva.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth

class UsuarioFragment : Fragment() {

    private lateinit var usuarioViewModel: UsuarioViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        usuarioViewModel = ViewModelProviders.of(this).get(UsuarioViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_usuario, container, false)
       /* val textView: TextView = root.findViewById(R.id.text_notifications)
        usuarioViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })*/
        return root
    }


}