package com.example.minerva.ui.fragments.perguntas.natureza

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.minerva.R
import kotlinx.android.synthetic.main.fragment_exercicio.view.*

class Pergunta4Fragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view : View = inflater.inflate(R.layout.fragment_exercicio, container, false)

        inicializar(view)
        conferir(view)

        view.btn_proximo.setOnClickListener {
            trocarFragment(Pergunta5Fragment())
        }

        view.btn_voltar.setOnClickListener {
            trocarFragment(Pergunta3Fragment())
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
            "(Enem 2008) Em discurso proferido em 17 de março de 1939, o primeiro-ministro inglês à época, Neville Chamberlain, sustentou sua posição política:\n" +
                    "\n" +
                    "“Não necessito defender minhas visitas à Alemanha no outono passado, que alternativa existia? Nada do que pudéssemos ter feito, nada do que a França pudesse ter feito, ou mesmo a Rússia, teria salvado a Tchecoslováquia da destruição. Mas eu também tinha outro propósito ao ir até Munique. Era o de prosseguir com a política por vezes chamada de ‘apaziguamento europeu’, e Hitler repetiu o que já havia dito, ou seja, que os Sudetos, região de população alemã na Tchecoslováquia, eram a sua última ambição territorial na Europa, e que não queria incluir na Alemanha outros povos que não os alemães.”\n" +
                    "\n" +
                    "Sabendo-se que o compromisso assumido por Hitler em 1938, mencionado no texto acima, foi rompido pelo líder alemão em 1939, infere-se que:"
        textA.text =
            "a) Hitler ambicionava o controle de mais territórios na Europa além da região dos Sudetos."
        textB.text =
            "b) A aliança entre a Inglaterra, a França e a Rússia poderia ter salvado a Tchecoslováquia."
        textC.text =
            "c) O rompimento desse compromisso inspirou a política de ‘apaziguamento europeu’."
        textD.text =
            "d) A política de Chamberlain de apaziguar o líder alemão era contrária à posição assumida pelas potências aliadas."
        textE.text =
            "e) A forma que Chamberlain escolheu para lidar com o problema dos Sudetos deu origem a destruição da Tchecoslováquia."
    }

    private fun trocarFragment(fragment: Fragment){
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