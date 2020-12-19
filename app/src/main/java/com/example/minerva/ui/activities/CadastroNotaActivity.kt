package com.example.minerva.ui.activities

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.minerva.R
import com.example.minerva.service.constants.CoresNotasConstants
import com.example.minerva.service.model.NotaFirebaseModel
import com.example.minerva.ui.fragments.SelecaoCoresFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_cadastro_nota.*

class CadastroNotaActivity : AppCompatActivity(), View.OnClickListener,
    SelecaoCoresFragment.SelecaoCores {

    private lateinit var mCor: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (supportActionBar != null) {
            supportActionBar!!.title = ""
            supportActionBar!!.setDisplayHomeAsUpEnabled(true) //Mostrar o botão
            supportActionBar!!.setHomeButtonEnabled(true)      //Ativar o botão
            supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor(CoresNotasConstants.AZUL_ESCURO)))
        }
        setContentView(R.layout.activity_cadastro_nota)
        mCor = "#33AEC4"

        button_salvar_nota.setOnClickListener(this)

        val bundle = intent.extras
        if (bundle != null) {
            text_view_salvar_nota.text = "Salvar alterações"
            edit_titulo_nota.setText(bundle.getString("titulo") ?: "")
            edit_conteudo_nota.setText(bundle.getString("conteudo") ?: "")
            mCor = bundle.getString("cor") ?: "#33AEC4"
        }
        onCor(mCor)
        edit_titulo_nota.requestFocus()
    }

    override fun onClick(v: View) {
        val id = v.id
        if (id == R.id.button_salvar_nota) {
            if(validarCampos()){
                salvarNota()
                finish()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)

        val m1 = menu.add(0, 1, 1, "paleta")
        m1.setIcon(R.drawable.ic_paleta)
        m1.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean { //Botão adicional na ToolBar
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
            1 -> {
                chamarMyDialog()
            }
        }
        return true
    }

    private fun salvarNota() {
        val titulo = edit_titulo_nota.text.toString()
        val conteudo = edit_conteudo_nota.text.toString()

        val nota = NotaFirebaseModel(titulo, conteudo, mCor)

        val reference = FirebaseDatabase.getInstance().reference

        val bundle = intent.extras
        if (bundle != null) {
            reference.child("usuarios").child(FirebaseAuth.getInstance().currentUser!!.uid)
                .child("notas").child(bundle.getString("idNota") ?: "a").setValue(nota)
        } else {
            reference.child("usuarios").child(FirebaseAuth.getInstance().currentUser!!.uid)
                .child("notas").push().setValue(nota)
        }
    }

    override fun onCor(cor: String) {
        mCor = cor
      /*  layout_cadastro_produto.setBackgroundColor(Color.parseColor(cor))
        edit_titulo_nota.setBackgroundColor(Color.parseColor(cor))
        edit_conteudo_nota.setBackgroundColor(Color.parseColor(cor))*/
        val corFraca: Int
        val corForte : Int
        when (cor) {
            CoresNotasConstants.AMARELO -> {
                corFraca = ContextCompat.getColor(this, R.color.colorAmareloFraco)
                corForte = Color.parseColor(CoresNotasConstants.AMARELO_ESCURO)
            }
            CoresNotasConstants.AZUL -> {
                corFraca = ContextCompat.getColor(this, R.color.colorAzulFraco)
                corForte = Color.parseColor(CoresNotasConstants.AZUL_ESCURO)
            }
            CoresNotasConstants.VERMELHO -> {
                corFraca = ContextCompat.getColor(this, R.color.colorVermelhoFraco)
                corForte = Color.parseColor(CoresNotasConstants.VERMELHO_ESCURO)
            }
            else -> {
                corFraca = ContextCompat.getColor(this, R.color.colorVerdeFraco)
                corForte = Color.parseColor(CoresNotasConstants.VERDE_ESCURO)

            }
        }
        supportActionBar!!.setBackgroundDrawable(ColorDrawable(corForte))
        layout_cadastro_produto.setBackgroundColor(corFraca)
        edit_titulo_nota.setBackgroundColor(corFraca)
        edit_conteudo_nota.setBackgroundColor(corFraca)
        button_salvar_nota.setCardBackgroundColor(corForte)
    }

    private fun chamarMyDialog() {
        val myFragment = SelecaoCoresFragment()
        myFragment.show(supportFragmentManager, "my_fragment")
    }

    private fun validarCampos(): Boolean{
        val titulo = edit_titulo_nota.text.toString()
        val conteudo = edit_conteudo_nota.text.toString()

        if(titulo.isEmpty()){
            Toast.makeText(this, "Título não pode ser vazio", Toast.LENGTH_SHORT).show()
            return false
        }else if(conteudo.isEmpty()){
            Toast.makeText(this, "Conteúdo não pode ser vazio", Toast.LENGTH_SHORT).show()
            return false
        }else{
            return true
        }
    }
}