package com.example.projetointegrador

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projetointegrador.adapters.RotaMinimaAdapter
import com.example.projetointegrador.models.RotaMinima
import kotlinx.android.synthetic.main.fragment_cadastro.*


class RotasMinFragment(var rotasMinimas:MutableList<RotaMinima>) : DialogFragment() {
    private var recyclerView: RecyclerView? = null
    private var recyclerViewAdapter: RotaMinimaAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cadastro, container, false)
    }

    @ExperimentalStdlibApi
    override fun onStart() {
        super.onStart()
        val width = ViewGroup.LayoutParams.MATCH_PARENT
        val height = ViewGroup.LayoutParams.MATCH_PARENT
        dialog?.window?.setLayout(width, height)
        recyclerView = rota_min_recyclerview
        recyclerViewAdapter = RotaMinimaAdapter(rotasMinimas){displayTrajeto(it,generateRota(it))}
        recyclerView?.adapter = recyclerViewAdapter
        recyclerView?.layoutManager = GridLayoutManager(this.context, 1)
    }

    @ExperimentalStdlibApi
    private fun generateRota(rotaMin:RotaMinima):String{
        if(rotaMin.cidadeAlvo == "A")
            return " ESTA NA ORIGEM"

        var rota = "A -> "
        var antecessorPointer:RotaMinima = rotaMin
        val listaAnteriores = mutableListOf<String>()

        do{
            listaAnteriores.add(antecessorPointer.cidadeAlvo)
            antecessorPointer = rotasMinimas.first{ it.cidadeAlvo == antecessorPointer.cidadeAnterior }
        } while(antecessorPointer.cidadeAlvo != "A")


        while (listaAnteriores.size>0){
            val removeLastOrNull = listaAnteriores.removeLast()
            rota += removeLastOrNull
            if(listaAnteriores.size>0){
                rota +=" -> "
            }
        }

        return rota
    }

    private fun displayTrajeto(rotaMin: RotaMinima, trajeto: String){
        val builder = AlertDialog.Builder(this.context)
        builder.setTitle("Rota de A até ${rotaMin.cidadeAlvo}")
        builder.setMessage(trajeto)
        builder.show()
    }

}

