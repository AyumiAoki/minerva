package com.example.minerva.ui.activities

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.minerva.R
import com.example.minerva.ui.fragments.AgendaFragment
import com.example.minerva.ui.fragments.NotaFragment
import com.example.minerva.ui.fragments.ConteudoFragment
import com.example.minerva.ui.fragments.UsuarioFragment
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL


class MainActivity : AppCompatActivity(), View.OnClickListener,
    ConteudoFragment.CreateConteudoLiestener, NotaFragment.CreateAnotacaoListener,
    UsuarioFragment.CreateUsuarioListener, AgendaFragment.AgendaListener {
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mUser: FirebaseUser
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private lateinit var mAuthStateListener: FirebaseAuth.AuthStateListener
    private lateinit var mNomeUsuario: String
    private var bitmap: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        setContentView(R.layout.activity_main)

        loadImage(Handler())

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_conteudo,
                R.id.navigation_anotacao,
                R.id.navigation_agenda,
                R.id.navigation_usuario
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        mAuth = FirebaseAuth.getInstance()

        //  button_sair_usuario.setOnClickListener(this)
        mNomeUsuario = ("Olá, " + mAuth.currentUser!!.displayName + "!")
        //  text_teste.text = mNomeUsuario

        // button_sair_usuario.setOnClickListener(this)

        estadoAutenticacao()
        servicosGoogle()
    }


    override fun onClick(view: View) {

    }

    //--------------------------------METODOS DOS CLIKCS----------------------------------//
    private fun deslogar() {
        FirebaseAuth.getInstance().signOut()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
        mGoogleSignInClient.signOut()

        finish()

        startActivity(Intent(baseContext, IntroducaoActivity::class.java))
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
            if (it.currentUser != null) {
                mUser = it.currentUser!!

            } else {

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

        mAuth.removeAuthStateListener { mAuthStateListener }
    }

    override fun onCreateConteudo(codigo: Int) {
        when (codigo) {
            1 -> {
                startActivity(Intent(baseContext, CienciasHumanasActivity::class.java))
            }
            2 -> {
                startActivity(Intent(baseContext, CienciasNaturezaActivity::class.java))
            }
            3 -> {
                startActivity(Intent(baseContext, LinguagemCodigoActivity::class.java))
            }
            4 -> {
                startActivity(Intent(baseContext, MatematicaActivity::class.java))
            }
        }
    }

    override fun onListenerUsuario(codigo: Int) {
        when (codigo) {
            1 -> {
                openKeyBoard()
            }
            2 -> {
                closeKeyBoard()
            }
            4 -> {
                deslogar()
            }
        }
    }

    override fun passarImagem(): Bitmap? {
        return bitmap
    }

    override fun atualizarImagem(bitmap: Bitmap) {
        this.bitmap = bitmap
    }


    private fun openKeyBoard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
        }
    }

    private fun closeKeyBoard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    override fun onCreateAnotacao(codigo: Int) {}

    private fun loadImage(handler: Handler) {
        var img: Bitmap? = null

        Thread {
            kotlin.run {
                try {
                    val url = URL(FirebaseAuth.getInstance().currentUser!!.photoUrl.toString())
                    val conexao: HttpURLConnection = url.openConnection() as HttpURLConnection
                    val input = conexao.inputStream
                    img = BitmapFactory.decodeStream(input)

                } catch (e: IOException) {
                    e.printStackTrace()
                }
                handler.post {
                    bitmap = img
                }
            }
        }.start()

    }

    override fun onAnotacao(codigo: Int) {
        TODO("Not yet implemented")

    }
}