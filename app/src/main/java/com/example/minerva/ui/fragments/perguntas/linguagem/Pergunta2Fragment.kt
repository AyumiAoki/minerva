package com.example.minerva.ui.fragments.perguntas.linguagem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.minerva.R
import kotlinx.android.synthetic.main.fragment_exercicio.view.*

class Pergunta2Fragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view : View = inflater.inflate(R.layout.fragment_exercicio, container, false)

        inicializar(view)
        conferir(view)

        view.btn_proximo.setOnClickListener {
            trocarFragment(Pergunta3Fragment())
        }

        view.btn_voltar.setOnClickListener {
            trocarFragment(Pergunta1Fragment())
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
            "(ENEM/2018) Deficientes visuais já podem ir a algumas salas de cinema e teatros para curtir, em maior intensidade, as atrações em cartaz. Quem ajuda na tarefa é o aplicativo Whatscine, recém-chegado ao Brasil e disponível para os sistemas operacionais iOS (Apple) ou Android (Google). Ao ser conectado à rede wi-fi de cinemas e teatros, o app sincroniza um áudio que descreve o que ocorre na tela ou no palco com o espetáculo em andamento: o usuário, então, pode ouvir a narração em seu celular.\n" +
                    "O programa foi desenvolvido por pesquisadores da Universidade Carlos III, em Madri. “Na Espanha, 200 salas de cinema já oferecem o recurso e filmes de grandes estúdios já são exibidos com o recurso do Whatscine!”, diz o brasileiro Luis Mauch, que trouxe a tecnologia para o país. “No Brasil, já fechamos parceria com a São Paulo Companhia de Dança para adaptar os espetáculos deles! Isso já é um avanço. Concorda?”\n" +
                    "Disponível em: http://veja.abril.com.br. Acesso em: 25 jun.2014 (adaptado).\n" +
                    "\n" +
                    "Por ser múltipla e apresentar peculiaridades de acordo com a intenção do emissor, a linguagem apresenta funções diferentes. Nesse fragmento, predomina a função referencial da linguagem, porque há a presença de elementos que:\n"
        textA.text =
            "a) Buscam convencer o leitor, incitando o uso do aplicativo."
        textB.text =
            "b) Definem o aplicativo, revelando o ponto de vista da autora."
        textC.text =
            "c) Evidenciam a subjetividade, explorando a entonação emotiva."
        textD.text =
            "d) Expõem dados sobre o aplicativo, usando linguagem denotativa."
        textE.text =
            "e) Objetivam manter um diálogo com o leitor, recorrendo a uma indagação."
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