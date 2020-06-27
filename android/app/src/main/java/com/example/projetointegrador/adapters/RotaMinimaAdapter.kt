package com.example.projetointegrador.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projetointegrador.R
import com.example.projetointegrador.models.RotaMinima
import kotlinx.android.synthetic.main.rota_min_cardview.view.*

class RotaMinimaAdapter(var percursos:MutableList<RotaMinima>) :RecyclerView.Adapter<RotaMinimaAdapter.ViewHolder>(){

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.casa1.text = percursos[position].cidadeAlvo
        holder.casa2.text = percursos[position].cidadeAnterior
        holder.distancia.text = String.format("%.2f",percursos[position].distanciaMinima)
    }

    override fun getItemCount(): Int = percursos.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.rota_min_cardview,
                parent,
                false
            )
        )
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var casa1:TextView = itemView.casaOrigem_textview
        var casa2:TextView = itemView.casaAnterior_textview
        var distancia:TextView = itemView.distanciamin_textview
    }
}