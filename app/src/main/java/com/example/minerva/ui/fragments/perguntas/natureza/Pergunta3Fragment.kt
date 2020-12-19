package com.example.minerva.ui.fragments.perguntas.natureza

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.minerva.R
import com.example.minerva.ui.fragments.perguntas.linguagem.Pergunta2Fragment
import kotlinx.android.synthetic.main.fragment_exercicio.view.*

class Pergunta3Fragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view : View = inflater.inflate(R.layout.fragment_exercicio, container, false)

        inicializar(view)
        conferir(view)

        view.btn_proximo.setOnClickListener {
            trocarFragment(Pergunta4Fragment())
        }

        view.btn_voltar.setOnClickListener {
            trocarFragment(Pergunta2Fragment())
        }

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
            "(Enem 2016) Os andróginos tentaram escalar o céu para combater os deuses. No entanto, os deuses em um primeiro momento pensam em matá-los de forma sumária. Depois decidem puni-los da forma mais cruel: dividem-nos em dois.\n" +
                    "\n" +
                    "Por exemplo, é como se pegássemos um ovo cozido e, com uma linha, dividíssemos ao meio. Desta forma, até hoje as metades separadas buscam reunir-se. Cada um com saudade de sua metade, tenta juntar-se novamente a ela, abraçando-se, enlaçando-se um ao outro, desejando formar um único ser.\n" +
                    "\n" +
                    "PLATÃO. O banquete. São Paulo: Nova Cultural, 1987\n" +
                    "\n" +
                    "No trecho da obra O banquete, Platão explicita, por meio de uma alegoria, o:"
        textA.text =
            "a) Bem supremo como fim do homem."
        textB.text =
            "b) Prazer perene como fundamento da felicidade."
        textC.text =
            "c) Ideal inteligível como transcendência desejada."
        textD.text =
            "d) Amor como falta constituinte do ser humano."
        textE.text =
            "e) Autoconhecimento como caminho da verdade."
    }

    private fun trocarFragment(fragment: Fragment){
        val fragmentTransaction = fragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.viewPage, fragment)?.commit()
    }

    private fun conferir(view: View) {
        view.txt_opcao_a.setOnClickListener {
            view.txt_opcao_a.setBackgroundColor(resources.getColor(R.color.vermelho))
            view.txt_opcao_d.setBackgroundColor(resources.getColor(R.color.verde))

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
            view.txt_opcao_d.setBackgroundColor(resources.getColor(R.color.verde))

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
            view.txt_opcao_d.setBackgroundColor(resources.getColor(R.color.verde))

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
            view.txt_opcao_d.setBackgroundColor(resources.getColor(R.color.verde))

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
            view.txt_opcao_d.setBackgroundColor(resources.getColor(R.color.verde))

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