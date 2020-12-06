package com.example.minerva.ui.activities;

import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem
import androidx.appcompat.app.ActionBar

import com.example.minerva.R;
import kotlinx.android.synthetic.main.activity_termo_uso.*

class TermoUsoActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (supportActionBar != null) {
            supportActionBar!!.elevation = 0f
            supportActionBar!!.title = ""
            supportActionBar!!.setDisplayHomeAsUpEnabled(true) //Mostrar o botão
            supportActionBar!!.setHomeButtonEnabled(true)      //Ativar o botão
        }

        setContentView(R.layout.activity_termo_uso)

        button_voltar_termo_uso.setOnClickListener{
            finish()
        }
    }

     override fun onOptionsItemSelected(item: MenuItem): Boolean { //Botão adicional na ToolBar
         when (item.itemId) {
             android.R.id.home -> {
                 finish()
             }
             else -> {
             }
         }
         return true
     }
}