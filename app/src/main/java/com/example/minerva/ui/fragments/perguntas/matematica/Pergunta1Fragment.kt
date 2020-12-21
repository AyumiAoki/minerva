package com.example.minerva.ui.fragments.perguntas.matematica

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.minerva.R
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
            "O objeto de estudo da sociologia, para Durkheim, é o fato social, que deve ser tratado como \"coisa\" e o sociólogo deve afastar suas prenoções e preconceitos. A construção durkheimiana do objeto de estudo da sociologia pode ser considerada:"
        textA.text =
            " a) Positivista, pois se fundamenta na busca de objetividade e neutralidade."
        textB.text =
            "b) Dialética , pois reconhece a existência de uma realidade exterior ao pesquisador."
        textC.text =
            "c) Kantiana, pois trata da \"coisa em si\" e realiza a coisificação da realidade."
        textD.text =
            "d) Nietzschiana, pois coloca a \"vontade de poder\" como fundamento para a pesquisa."
        textE.text =
            "e) Weberiana, pois aborda a ação social racional atribuída por um sujeito."
    }

    private fun trocarFragment(fragment: Fragment) {
        val fragmentTransaction = fragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.viewPage, fragment)?.commit()
    }

    private fun conferir(view: View) {
        view.txt_opcao_a.setOnClickListener {
            view.txt_opcao_a.setBackgroundColor(resources.getColor(R.color.verde))

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
            view.txt_opcao_b.setBackgroundColor(resources.getColor(R.color.vermelho))
            view.txt_opcao_a.setBackgroundColor(resources.getColor(R.color.verde))

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
            view.txt_opcao_a.setBackgroundColor(resources.getColor(R.color.verde))

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
            view.txt_opcao_a.setBackgroundColor(resources.getColor(R.color.verde))

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
            view.txt_opcao_a.setBackgroundColor(resources.getColor(R.color.verde))

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