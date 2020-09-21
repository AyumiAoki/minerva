package com.example.minerva

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_principal.*

class PrincipalActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mUser: FirebaseUser
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private lateinit var mAuthStateListener: FirebaseAuth.AuthStateListener
    private lateinit var mNomeUsuario: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)
        mAuth = FirebaseAuth.getInstance()

        button_deslogar.setOnClickListener(this)
        mNomeUsuario = mAuth.currentUser!!.displayName ?: "null"
        text_teste.text = mNomeUsuario

        estadoAutenticacao()
        servicosGoogle()
    }

    override fun onClick(view: View) {
        when(view.id){
            R.id.button_deslogar -> {
                deslogar()
            }
        }
    }

    //--------------------------------METODOS DOS CLIKCS----------------------------------//
    private fun deslogar(){
        FirebaseAuth.getInstance().signOut()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
        mGoogleSignInClient.signOut()

        finish()

        startActivity(Intent(baseContext, LoginActivity::class.java))
    }

    //---------------------------------- SERVIÇOS ------------------------------------------//

    private fun servicosGoogle() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
    }

    private fun estadoAutenticacao() {
        mAuthStateListener = FirebaseAuth.AuthStateListener {
            if(it.currentUser != null){
                mUser = it.currentUser!!

            }else{

            }

        }
    }

    //-------------------------------METODOS DA ACTIVITY--------------------------------------//

    override fun onStart() {
        super.onStart()

        mAuth.addAuthStateListener { mAuthStateListener }
    }

    override fun onStop() {
        super.onStop()

        if( mAuthStateListener != null ){
            mAuth.removeAuthStateListener { mAuthStateListener }
        }
    }

}