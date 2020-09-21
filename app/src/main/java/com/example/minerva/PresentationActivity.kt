package com.example.minerva

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.view.size
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_tela_apresentacao.*
import kotlinx.android.synthetic.main.dica.view.*
import java.lang.Math.abs

class PresentationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }
        setContentView(R.layout.activity_tela_apresentacao)

        val dicas = arrayListOf(
            Dicas(
                "Minerva",
                "Revise os principais assuntos das Áreas de Conhecimento: Ciências Humanas " +
                        "e suas Tecnologias Ciências da Natureza e suas Tecnologias Linguagens, " +
                        "Códigos e suas Tecnologias Matemática e suas Tecnologias",
                R.drawable.img_apresentacao2,
                R.color.colorWhite
            ),
            Dicas(
                "Exercícios",
                "Exercite o que aprendeu após a revisão de cada conteúdo respondendo questões.",
                R.drawable.img_apresentacao3,
                R.color.colorWhite
            ),
            Dicas(
                "Agenda",
                "Anote o que for preciso e consulte quando quiser.",
                R.drawable.img_apresentacao4,
                R.color.colorWhite
            ),
            Dicas(
                "Anotações",
                "Defina em sua agenda horários de estudos e mantenha o foco.",
                R.drawable.img_apresentacao5,
                R.color.colorWhite
            )
        )

        addPontos(dicas.size)
        tela_apresentacao.adapter = onboardingAdapter(dicas)

        tela_apresentacao.setPageTransformer(true){
                page, position ->
            page.alpha = 1 - abs(position)
            page.translationX = -position * page.width
        }

        tela_apresentacao.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                addPontos(dicas.size, position)
            }
        })

        proximo.setOnClickListener{
            if(tela_apresentacao.currentItem == tela_apresentacao.size) {
                intent = Intent(applicationContext, LoginActivity::class.java)
                startActivity(intent)
            } else {
                tela_apresentacao.setCurrentItem(tela_apresentacao.currentItem + 1, true)
            }
        }

        pular.setOnClickListener{
            intent = Intent(applicationContext, IntroductionActivity::class.java)
            startActivity(intent)
        }
    }

    private fun addPontos(size: Int, position: Int = 0) {
        ponto.removeAllViews()
        Array(size){
            val textView = TextView(baseContext).apply {
                text = getText(R.string.ponto)
                textSize = 40f
                setTextColor(
                    if(position == it) ContextCompat.getColor(baseContext, android.R.color.holo_blue_dark)
                    else ContextCompat.getColor(baseContext, android.R.color.darker_gray)
                )
            }
            ponto.addView(textView)
        }
    }

    private inner class onboardingAdapter(val dicas: ArrayList<Dicas>) : PagerAdapter() {

        @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val view = layoutInflater.inflate(R.layout.dica, container, false)

            with(dicas[position]) {

                view.titulo_dicas.text = titulo
                view.subtitulo_dicas.text = subtitulo
                view.logo_dicas.setImageResource(logo)
                view.background = ContextCompat.getDrawable(this@PresentationActivity, background)
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
}

