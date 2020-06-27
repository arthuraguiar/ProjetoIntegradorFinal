package com.example.projetointegrador.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projetointegrador.R
import com.example.projetointegrador.models.DistanciaMap
import kotlinx.android.synthetic.main.rota_cardview.view.*

class RotasAdapter(var percursos:MutableList<DistanciaMap>) :RecyclerView.Adapter<RotasAdapter.ViewHolder>(){

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.casa1.text = percursos[position].cidade1
       holder.casa2.text = percursos[position].cidade2
       holder.distancia.text = String.format("%.2f",percursos[position].distancia)
    }

    override fun getItemCount(): Int = percursos.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.rota_cardview,
                parent,
                false
            )
        )
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var casa1:TextView = itemView.casa1_textview
        var casa2:TextView = itemView.casa2_textview
        var distancia:TextView = itemView.distancia_textview
    }
}