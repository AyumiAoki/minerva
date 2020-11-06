package com.example.minerva.ui.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.minerva.R
import com.example.minerva.ui.activities.CadastroNotaActivity
import com.example.minerva.ui.activities.NotaActivity
import com.example.minerva.ui.adapter.NotaAdapter
import com.example.minerva.ui.listener.NotaListener
import com.example.minerva.ui.viewmodel.AnotacaoViewModel

class AnotacaoFragment : Fragment() {

    private lateinit var anotacaoViewModel: AnotacaoViewModel
    private val mAdapter: NotaAdapter = NotaAdapter()
    private lateinit var  mListener1: NotaListener
    private lateinit var mListener: CreateAnotacaoListener

    override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
    ): View? {
        anotacaoViewModel =
            ViewModelProviders.of(this).get(AnotacaoViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_anotacao, container, false)

        val recycler = root.findViewById<RecyclerView>(R.id.list_nota)

        // Atribui um layout que diz como a RecyclerView se comporta
        recycler.layoutManager = LinearLayoutManager(context)

        // Defini um adapater - Faz a ligação da RecyclerView com a listagem de itens
        recycler.adapter = mAdapter

        // Cria os observadores
        observe()

        mListener1 = object : NotaListener {
            override fun onClick(id: Int) {
                val intent = Intent(context, NotaActivity::class.java)

                val bundle = Bundle()
                bundle.putInt("notaID", id)

                intent.putExtras(bundle)
                startActivity(intent)
            }

            override fun onDelete(id: Int) {
                anotacaoViewModel.delete(id)
                anotacaoViewModel.load()
            }
        }

        val button = root.findViewById<View>(R.id.button_adicionar_anotacao)
        button.setOnClickListener(View.OnClickListener {
            startActivity(Intent(mListener as Context, CadastroNotaActivity::class.java))
            /*Toast.makeText(context, "Sem conexão com o Firebase", Toast.LENGTH_LONG)
                .show()*/
        })
        return root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mListener = try {
            activity as CreateAnotacaoListener
        } catch (e: ClassCastException) {
            throw ClassCastException(activity.toString() + " deve implementar CreateEmailListener")
        }

    }

    interface CreateAnotacaoListener{
        fun onCreateAnotacao()
    }

    override fun onResume() {
        super.onResume()
        mAdapter.attachListener(mListener1)
        println("kdjsfldfokadlfkçoksdfçokjadfçojfçdaosjfçojafdsojfoajdçofjamsçdfjma")
        anotacaoViewModel.load()
    }

    /**
     * Cria os observadores
     */
    private fun observe() {
        anotacaoViewModel.notaList.observe(viewLifecycleOwner, Observer {
            mAdapter.updateNota(it)
        })
    }


}