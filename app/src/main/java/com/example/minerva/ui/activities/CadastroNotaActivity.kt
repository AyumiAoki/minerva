package com.example.minerva.ui.activities

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.minerva.R
import com.example.minerva.service.constants.CoresNotasConstants
import com.example.minerva.service.model.NotaFirebaseModel
import com.example.minerva.ui.fragments.SelecaoCoresFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_cadastro_nota.*

class CadastroNotaActivity : AppCompatActivity(), View.OnClickListener,
    SelecaoCoresFragment.SelecaoCores {

    private var mCor: String = "#33AEC4"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (supportActionBar != null) {
            supportActionBar!!.title = ""
            supportActionBar!!.setDisplayHomeAsUpEnabled(true) //Mostrar o botão
            supportActionBar!!.setHomeButtonEnabled(true)      //Ativar o botão
            supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor(CoresNotasConstants.AZUL_ESCURO)))
        }
        setContentView(R.layout.activity_cadastro_nota)

        button_salvar_nota.setOnClickListener(this)

        val bundle = intent.extras
        if (bundle != null) {
            button_salvar_nota.text = "Salvar Alterações"
            edit_titulo_nota.setText(bundle.getString("titulo") ?: "")
            edit_conteudo_nota.setText(bundle.getString("conteudo") ?: "")
            mCor = bundle.getString("cor") ?: "#33AEC4"
        }
        try {
            layout_cadastro_produto.setBackgroundColor(Color.parseColor(mCor))
            edit_titulo_nota.setBackgroundColor(Color.parseColor(mCor))
            edit_conteudo_nota.setBackgroundColor(Color.parseColor(mCor))
            when (mCor) {
                CoresNotasConstants.AMARELO -> {
                    supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor(CoresNotasConstants.AMARELO_ESCURO)))
                }
                CoresNotasConstants.AZUL -> {
                    supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor(CoresNotasConstants.AZUL_ESCURO)))
                }
                CoresNotasConstants.VERMELHO -> {
                    supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor(CoresNotasConstants.VERMELHO_ESCURO)))
                }
                else -> {
                    supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor(CoresNotasConstants.VERDE_ESCURO)))
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    override fun onClick(v: View) {
        val id = v.id
        if (id == R.id.button_salvar_nota) {
            salvarNota()
            finish()
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
        layout_cadastro_produto.setBackgroundColor(Color.parseColor(cor))
        edit_titulo_nota.setBackgroundColor(Color.parseColor(cor))
        edit_conteudo_nota.setBackgroundColor(Color.parseColor(cor))
        when (cor) {
            CoresNotasConstants.AMARELO -> {
                supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor(CoresNotasConstants.AMARELO_ESCURO)))
            }
            CoresNotasConstants.AZUL -> {
                supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor(CoresNotasConstants.AZUL_ESCURO)))
            }
            CoresNotasConstants.VERMELHO -> {
                supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor(CoresNotasConstants.VERMELHO_ESCURO)))
            }
            else -> {
                supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor(CoresNotasConstants.VERDE_ESCURO)))
            }
        }
    }

    private fun chamarMyDialog() {
        val myFragment = SelecaoCoresFragment()
        myFragment.show(supportFragmentManager, "my_fragment")
    }
}