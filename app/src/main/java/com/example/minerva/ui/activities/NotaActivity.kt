package com.example.minerva.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.minerva.R
import com.example.minerva.ui.viewmodel.NotaViewModel
import kotlinx.android.synthetic.main.activity_nota.*

class NotaActivity : AppCompatActivity() {

    private lateinit var mViewModel: NotaViewModel
    private var mGuestId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true) //Mostrar o botão
            supportActionBar!!.setHomeButtonEnabled(true)      //Ativar o botão
        }

        setContentView(R.layout.activity_nota)
        mViewModel = ViewModelProvider(this).get(NotaViewModel::class.java)

        loadData()

        // Cria observadores
        observe()
    }

    private fun loadData() {
        val bundle = intent.extras
        if (bundle != null) {
            mGuestId = bundle.getInt("notaID")
            mViewModel.load(mGuestId)
        }
    }

    private fun observe() {
        mViewModel.saveGuest.observe(this, {
            if (it) {
                Toast.makeText(applicationContext, "Sucesso", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(applicationContext, "Falha", Toast.LENGTH_SHORT).show()
            }
            finish()
        })

        mViewModel.guest.observe(this, {
            if (supportActionBar != null) {
                supportActionBar!!.title = it.titulo
            }
            text_conteudo_nota.text = it.texto

        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean { //Botão adicional na ToolBar
        when (item.itemId) {
            android.R.id.home -> {
                /*startActivity(
                    Intent(
                        this,
                        SuaActivity::class.java
                    )
                ) //O efeito ao ser pressionado do botão (no caso abre a activity)*/
                finish()
                /* finishAffinity() //Método para matar a activity e não deixa-lá indexada na pilhagem*/
            }
            else -> {
            }
        }
        return true
    }
}