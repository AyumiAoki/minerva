package com.example.minerva.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.minerva.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_introducao.*

class IntroducaoActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var mGoogleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_introducao)
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        mAuth = FirebaseAuth.getInstance()

        button_cadastrar.setOnClickListener(this)
        button_login.setOnClickListener(this)
        button_login_google.setOnClickListener(this)

        servicosGoogle()
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.button_cadastrar -> {
                startActivity(Intent(applicationContext, CadastroActivity::class.java))
            }

            R.id.button_login -> {
                startActivity(Intent(applicationContext, LoginActivity::class.java))
            }

            R.id.button_login_google -> {
                loginGoogle()
            }
        }
    }

    fun loginGoogle() {

        val account = GoogleSignIn.getLastSignedInAccount(this)

        if (account == null) {
            val intent = mGoogleSignInClient.signInIntent
            startActivityForResult(intent, 555)
        } else {
            //Já existe algue conectado pelo google
            // startActivity(Intent(applicationContext, TelaInicialActivity::class.java))
            Toast.makeText(applicationContext, "Já logado com o Google", Toast.LENGTH_LONG)
                .show()

            mGoogleSignInClient.signOut()
        }

    }

    //---------------------------------- SERVIÇOS ------------------------------------------//

    private fun servicosGoogle() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
    }

    private fun adicionarContaGoogleFirebase(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    startActivity(Intent(applicationContext, MainActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(
                        applicationContext,
                        "Error ao logar com o Google",
                        Toast.LENGTH_LONG
                    )
                        .show()
                }
            }
    }

    //-------------------------------METODOS DA ACTIVITY--------------------------------------//

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 555) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)

            try {
                val account = task.getResult(ApiException::class.java)

                adicionarContaGoogleFirebase(account!!.idToken ?: "nice")

            } catch (e: ApiException) {

                Toast.makeText(applicationContext, e.message, Toast.LENGTH_LONG)
                    .show()
            }

        }
    }
}

