package com.example.minerva.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.minerva.R
import com.example.minerva.util.Util
import com.example.minerva.service.constants.ErrorsFirebase
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.button_cadastrar
import kotlinx.android.synthetic.main.activity_login.button_login
import kotlinx.android.synthetic.main.activity_login.edit_email
import kotlinx.android.synthetic.main.activity_login.edit_senha
import kotlinx.android.synthetic.main.activity_login.layout_senha

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var mGoogleSignInClient: GoogleSignInClient


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        mAuth = FirebaseAuth.getInstance()

        button_recuperar_senha.setOnClickListener(this)
        button_cadastrar.setOnClickListener(this)
        button_login.setOnClickListener(this)
        button_login_google.setOnClickListener(this)
        layout_senha.setOnClickListener(this)

        edit_senha.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                edit_senha.error = null
                layout_senha.isPasswordVisibilityToggleEnabled = true
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })
        servicosGoogle()
        closeKeyBoard()
        progressBar_login.visibility = View.INVISIBLE
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.button_cadastrar -> {
                cadastrar()
            }

            R.id.button_login -> {
                loginEmail()
            }

            R.id.button_login_google -> {
                loginGoogle()
            }

            R.id.button_recuperar_senha -> {
                recuperarSenha()
            }

        }
    }

    //--------------------------------METODOS DOS CLIKCS----------------------------------//

    private fun recuperarSenha(){
        startActivity(Intent(applicationContext, RecuperarSenhaActivity::class.java))
    }

    private fun cadastrar(){
        startActivity(Intent(applicationContext, CadastroActivity::class.java))
        finish()
    }

    private fun loginEmail() {
        val email = edit_email.text.toString()
        val senha = edit_senha.text.toString()


        if (validarDados()) {
            progressBar_login.visibility = View.VISIBLE
            mAuth.signInWithEmailAndPassword(email, senha).addOnCompleteListener(this) {
                progressBar_login.visibility = View.INVISIBLE
                if (it.isSuccessful) {
                    val intent = Intent(this, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                    startActivity(intent)
                    finish()
                } else {
                    validacaoFirebase(it.exception.toString())
                }
            }
        }
    }

    private fun loginGoogle(){
        val account = GoogleSignIn.getLastSignedInAccount(this)
        progressBar_login.visibility = View.VISIBLE

        if(account == null){
            val intent = mGoogleSignInClient.signInIntent
            startActivityForResult(intent, 555)
        }else{
            //Já existe algue conectado pelo google
            // startActivity(Intent(applicationContext, TelaInicialActivity::class.java))
            progressBar_login.visibility = View.INVISIBLE
            Toast.makeText(applicationContext, "Já logado com o Google", Toast.LENGTH_LONG).show()

            mGoogleSignInClient.signOut()
        }

    }

    // --------------------------------VALIDAÇÕES-------------------------------------- //

    private fun validacaoFirebase(error: String) {
        when (Util.errorFirebase(error)){
            ErrorsFirebase.EMAIL_INVALIDO -> {
                edit_email.error = "Insira um email válido"
            }
            ErrorsFirebase.EMAIL_JA_CADASTRADO -> {
                edit_email.error = "Este e-mail não está cadastrado"
            }
            ErrorsFirebase.SENHA_INVALIDA -> {
                edit_senha.error = "Senha deve possuir 6 ou mais caracteres"
                layout_senha.isPasswordVisibilityToggleEnabled = true
                layout_senha.isPasswordVisibilityToggleEnabled = false
            }
            ErrorsFirebase.SENHA_INCORRETA -> {
                edit_senha.error = "Senha inválida"
                layout_senha.isPasswordVisibilityToggleEnabled = true
                layout_senha.isPasswordVisibilityToggleEnabled = false
            }
            ErrorsFirebase.SEM_CONEXAO -> {
                Toast.makeText(applicationContext, "Sem conexão com o Firebase", Toast.LENGTH_LONG).show()
            }
            else ->{
                Toast.makeText(applicationContext, error, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validarDados(): Boolean {
        var check = true

        val email = edit_email.text.toString()
        val senha = edit_senha.text.toString()

        //Validação campo email
        if (email.isEmpty()) {
            check = false
            edit_email.error = "Campo obrigatório"
        } else if (!Util.validarEmail(email)) {
            check = false
            edit_email.error = "Insira um formato de email válido"
        }

        //Validação campo senha
        if (senha.isEmpty()) {
            check = false
            layout_senha.isPasswordVisibilityToggleEnabled = true
            layout_senha.isPasswordVisibilityToggleEnabled = false
            edit_senha.error = "Campo obrigatório"

        } else if (Util.validarSenha(senha)) {
            check = false
             layout_senha.isPasswordVisibilityToggleEnabled = true
            layout_senha.isPasswordVisibilityToggleEnabled = false

            edit_senha.error = "Senha possui 6 ou mais caracteres"

        }

        //Verifica conexão com a internet
        if (!Util.statusInternetMoWi(applicationContext)) {
            check = false
            Toast.makeText(applicationContext, "Sem conexão com a internet", Toast.LENGTH_SHORT).show()
        }
        return check
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
                    val intent = Intent(this, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(applicationContext, "Error ao logar com o Google", Toast.LENGTH_LONG).show()
                }
            }
    }

    //-------------------------------METODOS DA ACTIVITY--------------------------------------//

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 555){
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                adicionarContaGoogleFirebase(account!!.idToken ?: "nice")
            } catch (e: ApiException) {
                progressBar_login.visibility = View.INVISIBLE
                Toast.makeText(applicationContext, e.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun closeKeyBoard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}