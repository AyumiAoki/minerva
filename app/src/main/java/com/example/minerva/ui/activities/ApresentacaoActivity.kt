package com.example.minerva.ui.activities

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.size
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.minerva.R
import com.example.minerva.util.Dicas
import kotlinx.android.synthetic.main.activity_apresentacao.*
import kotlinx.android.synthetic.main.dica.view.*

class ApresentacaoActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        setContentView(R.layout.activity_apresentacao)


        val dicas = arrayListOf(
            Dicas(
                "Minerva",
                """Revise os principais assuntos das
4 Áreas de Conhecimento:

Ciências Humanas
Ciências da Natureza
Linguagens e Códigos
Matemática""",
                R.drawable.img_apresentacao2,
                R.color.colorPrimary
            ),
            Dicas(
                "Exercite",
                """Exercite o que aprendeu após 
a revisão de cada conteúdo 
respondendo exercícios.""",
                R.drawable.img_apresentacao3,
                R.color.colorPrimary
            ),
            Dicas(
                "Agenda",
                """Defina em sua agenda horários 
de estudos e mantenha o foco.""",
                R.drawable.img_apresentacao4,
                R.color.colorPrimary
            ),
            Dicas(
                "Anotações",
                """Anote o que for preciso 
e consulte quando quiser.""",
                R.drawable.img_apresentacao5,
                R.color.colorPrimary
            )
        )

        //    addPontos(dicas.size)
        tela_apresentacao.adapter = onboardingAdapter(dicas)

        tela_apresentacao.setPageTransformer(true) { page, position ->
            page.alpha = 1 - kotlin.math.abs(position)
            page.translationX = -position * page.width
        }

        tela_apresentacao.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                estadoBotoes(position)
                //addPontos(dicas.size, position)
            }
        })

        button_proximo.setOnClickListener(this)
        button_pular.setOnClickListener(this)
    }

    /*
    private fun addPontos(size: Int, position: Int = 0) {
        ponto.removeAllViews()
        Array(size) {
            val textView = TextView(baseContext).apply {
                text = getText(R.string.ponto)
                textSize = 40f
                setTextColor(
                    if (position == it) ContextCompat.getColor(
                        baseContext,
                        android.R.color.holo_blue_dark
                    )
                    else ContextCompat.getColor(baseContext, android.R.color.darker_gray)
                )
            }
            ponto.addView(textView)
        }
    }*/

    private inner class onboardingAdapter(val dicas: ArrayList<Dicas>) : PagerAdapter() {

        @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val view = layoutInflater.inflate(R.layout.dica, container, false)

            with(dicas[position]) {

                view.text_titulo_dicas.text = titulo
                view.text_subtitulo_dicas.text = subtitulo
                view.logo_dicas.setImageResource(logo)
                view.background = ContextCompat.getDrawable(this@ApresentacaoActivity, background)
            }

            container.addView(view)

            return view
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as View)

        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view == `object`
        }

        override fun getCount(): Int = dicas.size

    }

    fun estadoBotoes(position: Int) {
        when (position) {
            0 -> {
                text_button_pular.text = "Pular"
                text_button_proximo.text = "—ᐳ"
            }
            /*3 -> {
                text_button_proximo.text = "Começar"
                button_pular.text = "ᐸ—"
            }*/
            else -> {
                text_button_pular.text = "ᐸ—"
                text_button_proximo.text = "—ᐳ"
            }
        }
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.button_proximo -> {
                if (tela_apresentacao.currentItem - 1 == tela_apresentacao.size) {
                    intent = Intent(applicationContext, IntroducaoActivity::class.java)
                    finish()
                    startActivity(intent)
                } else {
                    estadoBotoes(tela_apresentacao.currentItem - 1)
                    tela_apresentacao.setCurrentItem(tela_apresentacao.currentItem + 1, true)
                }
            }
            R.id.button_pular -> {
                if (tela_apresentacao.currentItem == 0) {
                    intent = Intent(applicationContext, IntroducaoActivity::class.java)
                    finish()
                    startActivity(intent)
                } else {
                    estadoBotoes(tela_apresentacao.currentItem - 1)
                    tela_apresentacao.setCurrentItem(tela_apresentacao.currentItem - 1, true)
                }

            }
        }
    }
}

