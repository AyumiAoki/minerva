package com.example.minerva.ui

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.minerva.R
import com.example.minerva.service.Util
import com.example.minerva.service.constants.ErrorsFirebase
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.android.synthetic.main.activity_cadastro.*
import kotlinx.android.synthetic.main.activity_cadastro.button_cadastrar
import kotlinx.android.synthetic.main.activity_cadastro.button_login
import kotlinx.android.synthetic.main.activity_cadastro.edit_email
import kotlinx.android.synthetic.main.activity_cadastro.edit_senha
import kotlinx.android.synthetic.main.activity_cadastro.layout_senha
import kotlinx.android.synthetic.main.activity_login.*


class CadastroActivity : AppCompatActivity(), View.OnClickListener{

    private lateinit var mAuth: FirebaseAuth
    private lateinit var mGoogleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        mAuth = FirebaseAuth.getInstance()

        button_login.setOnClickListener(this)
        button_termo_uso.setOnClickListener(this)
        button_cadastrar.setOnClickListener(this)
        button_cadastro_google.setOnClickListener(this)
        checkbox_termo.setOnClickListener(this)

        edit_senha.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                edit_senha.error = null
                layout_senha.isPasswordVisibilityToggleEnabled = true
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })

        servicosGoogle()
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.button_login -> {
                login()
            }

            R.id.button_termo_uso -> {
                Toast.makeText(applicationContext, "Teste termo de uso", Toast.LENGTH_SHORT).show()
            }

            R.id.button_cadastrar -> {
                cadastrar()
            }

            R.id.button_cadastro_google -> {
                cadastroGoogle()
            }

            R.id.checkbox_termo -> {
                button_termo_uso.error = null
            }
        }

    }

    //--------------------------------METODOS DOS CLIKCS----------------------------------//
    fun login(){
        startActivity(Intent(applicationContext, LoginActivity::class.java))
        finish()
    }

    private fun cadastrar() {
        val email = edit_email.text.toString()
        val senha = edit_senha.text.toString()
        val nome = edit_name.text.toString() + " " + edit_sobrenome.text.toString()

        if (validarDados()) {

            mAuth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    salvarNome(nome)
                } else {
                    validacaoFirebase(it.exception.toString())
                }
            }


        }
    }

    fun cadastroGoogle(){
        val account = GoogleSignIn.getLastSignedInAccount(this)

        if(account == null){
            val intent = mGoogleSignInClient.signInIntent
            startActivityForResult(intent, 555)
        }else{
            //Já existe algue conectado pelo google
            // startActivity(Intent(applicationContext, TelaInicialActivity::class.java))
            Toast.makeText(applicationContext, "Já logado com o Google", Toast.LENGTH_LONG)
                .show()

            mGoogleSignInClient.signOut()
        }
    }

    // --------------------------------VALIDAÇÕES-------------------------------------- //

    private fun validarDados(): Boolean {
        var check = true

        val nome = edit_name.text.toString()
        val sobrenome = edit_sobrenome.text.toString()
        val email = edit_email.text.toString()
        val senha = edit_senha.text.toString()

        if (nome.isEmpty()) {
            check = false
            edit_name.error = "Campo obrigatório"
        } else if (!Util.validarNome(nome)){
            check = false
            edit_name.error = "Formato de nome invalído"
        }

        if (sobrenome.isEmpty()) {
            check = false
            edit_sobrenome.error = "Campo obrigatório"
        } else if (!Util.validarNome(sobrenome)) {
            check = false
            edit_sobrenome.error = "Formato de nome invalído"
        }

        if (email.isEmpty()) {
            check = false
            edit_email.error = "Campo obrigatório"
        } else if (!Util.validarEmail(email)) {
            check = false
            edit_email.error = "Insira um email válido"
        }

        if (senha.isEmpty()) {
            check = false
            layout_senha.isPasswordVisibilityToggleEnabled = true
            layout_senha.isPasswordVisibilityToggleEnabled = false
            edit_senha.error = "Campo obrigatório"
        } else if (Util.validarSenha(senha)) {
            check = false
            layout_senha.isPasswordVisibilityToggleEnabled = true
            layout_senha.isPasswordVisibilityToggleEnabled = false
            edit_senha.error = "Senha deve possuir 6 ou mais caracteres"
        }

        if (!checkbox_termo.isChecked) {
            check = false
            button_termo_uso.error = "Você deve aceitar os termos para prosseguir"
        }

        if (!Util.statusInternet_MoWi(applicationContext)) {
            check = false
            Toast.makeText(applicationContext, "Sem conexão com a internet", Toast.LENGTH_SHORT)
                .show()
        }
        return check
    }

    private fun validacaoFirebase(error: String) {
        when (Util.errorFirebase(error)){
            ErrorsFirebase.SENHA_INVALIDA -> {
                edit_senha.error = "Senha deve possuir 6 ou mais caracteres"
                layout_senha.isPasswordVisibilityToggleEnabled = true
                layout_senha.isPasswordVisibilityToggleEnabled = false
            }
            ErrorsFirebase.EMAIL_INVALIDO -> {
                edit_email.error = "Insira um email válido"
            }
            ErrorsFirebase.EMAIL_JA_CADASTRADO -> {
                edit_email.error = "E-mail já existe cadastrado"
            }
            ErrorsFirebase.SEM_CONEXAO -> {
                Toast.makeText(applicationContext, "Sem conexão com o Firebase", Toast.LENGTH_SHORT)
                    .show()
            }
            else -> {
                Toast.makeText(applicationContext, error, Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    //---------------------------------- SERVIÇOS ------------------------------------------//
    private fun salvarNome(nome: String){
        val user = mAuth.currentUser

        val profileUpdates = UserProfileChangeRequest.Builder()
            .setDisplayName(nome)
            .build()

        user?.updateProfile(profileUpdates)
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    startActivity(Intent(applicationContext, MainActivity::class.java))
                    finish()
                }else{
                    Toast.makeText(
                        applicationContext,
                        "Não foi possível salvar o nome",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            }
    }

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

        if (requestCode == 555){
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