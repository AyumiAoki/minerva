package com.example.minerva.ui.fragments.perguntas.linguagem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.minerva.R
import com.example.minerva.ui.fragments.perguntas.linguagem.Pergunta2Fragment
import kotlinx.android.synthetic.main.fragment_exercicio.view.*

class Pergunta1Fragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_exercicio, container, false)

        inicializar(view)
        conferir(view)

        //muda o atributo marginLeft do botao para 0
        val lp = view.btn_proximo.layoutParams
        if (lp is MarginLayoutParams) {
            lp.leftMargin = 0

            // Nao esqueca de requisitar o reajuste no layout
            view.btn_proximo.requestLayout()
        } //fim do bloco para mudar o atributo

        view.btn_proximo.setOnClickListener {
            trocarFragment(Pergunta2Fragment())
        }

        view.btn_voltar.visibility = View.GONE

        return view
    }

    private fun inicializar(view: View) {

        val text = view.findViewById<TextView>(R.id.text_questao)
        val textA = view.findViewById<TextView>(R.id.txt_opcao_a)
        val textB = view.findViewById<TextView>(R.id.txt_opcao_b)
        val textC = view.findViewById<TextView>(R.id.txt_opcao_c)
        val textD = view.findViewById<TextView>(R.id.txt_opcao_d)
        val textE = view.findViewById<TextView>(R.id.txt_opcao_e)

        text.text =
            "(ENEM/2018) Na sociologia e na literatura, o brasileiro foi por vezes tratado como cordial e hospitaleiro, mas não é isso o que acontece nas redes sociais: a democracia racial apregoada por Gilberto Freyre passa ao largo do que acontece diariamente nas comunidades virtuais do país. Levantamento inédito realizado pelo projeto Comunica que Muda [...] mostra em números a intolerância do internauta tupiniquim. Entre abril e junho, um algoritmo vasculhou plataformas [...] atrás de mensagens e textos sobre temas sensíveis, como racismo, posicionamento político e homofobia. Foram identificadas 393 284 menções, sendo que 84% delas com abordagem negativa, de exposição do preconceito e da discriminação.\n" +
                    "\n" +
                    "Disponível em: https://oglobo.globo.com. Acesso em: 6 dez. 2017 (adaptado).\n" +
                    "\n" +
                    "Ao abordar a postura do internauta brasileiro mapeada por meio de uma pesquisa em plataformas virtuais, o texto:\n"
        textA.text =
            "a) Minimiza o alcance da comunicação digital."
        textB.text =
            "b) Refuta ideias preconcebidas sobre o brasileiro."
        textC.text =
            "c) Relativiza responsabilidades sobre a noção de respeito."
        textD.text =
            "d) Exemplifica conceitos contidos na literatura e na sociologia."
        textE.text =
            "d) Expõe a ineficácia dos estudos para alterar tal comportamento."
    }

    private fun trocarFragment(fragment: Fragment) {
        val fragmentTransaction = fragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.viewPage, fragment)?.commit()
    }

    private fun conferir(view: View) {
        view.txt_opcao_a.setOnClickListener {
            view.txt_opcao_a.setBackgroundColor(resources.getColor(R.color.vermelho))
            view.txt_opcao_b.setBackgroundColor(resources.getColor(R.color.verde))

            view.txt_opcao_b.setOnClickListener {
            }

            view.txt_opcao_c.setOnClickListener {
            }

            view.txt_opcao_d.setOnClickListener {
            }

            view.txt_opcao_e.setOnClickListener {
            }
        }

        view.txt_opcao_b.setOnClickListener {
            view.txt_opcao_b.setBackgroundColor(resources.getColor(R.color.verde))

            view.txt_opcao_a.setOnClickListener {
            }

            view.txt_opcao_c.setOnClickListener {
            }

            view.txt_opcao_d.setOnClickListener {
            }

            view.txt_opcao_e.setOnClickListener {
            }
        }

        view.txt_opcao_c.setOnClickListener {
            view.txt_opcao_c.setBackgroundColor(resources.getColor(R.color.vermelho))
            view.txt_opcao_b.setBackgroundColor(resources.getColor(R.color.verde))

            view.txt_opcao_a.setOnClickListener {
            }

            view.txt_opcao_b.setOnClickListener {
            }

            view.txt_opcao_d.setOnClickListener {
            }

            view.txt_opcao_e.setOnClickListener {
            }
        }

        view.txt_opcao_d.setOnClickListener {
            view.txt_opcao_d.setBackgroundColor(resources.getColor(R.color.vermelho))
            view.txt_opcao_b.setBackgroundColor(resources.getColor(R.color.verde))

            view.txt_opcao_a.setOnClickListener {
            }

            view.txt_opcao_b.setOnClickListener {
            }

            view.txt_opcao_c.setOnClickListener {
            }

            view.txt_opcao_e.setOnClickListener {
            }
        }

        view.txt_opcao_e.setOnClickListener {
            view.txt_opcao_e.setBackgroundColor(resources.getColor(R.color.vermelho))
            view.txt_opcao_b.setBackgroundColor(resources.getColor(R.color.verde))

            view.txt_opcao_a.setOnClickListener {
            }

            view.txt_opcao_b.setOnClickListener {
            }

            view.txt_opcao_c.setOnClickListener {
            }

            view.txt_opcao_d.setOnClickListener {
            }
        }
    }
}