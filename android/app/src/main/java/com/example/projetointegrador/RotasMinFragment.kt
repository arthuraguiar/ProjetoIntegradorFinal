package com.example.projetointegrador

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

    override fun onStart() {
        super.onStart()
        val width = ViewGroup.LayoutParams.MATCH_PARENT
        val height = ViewGroup.LayoutParams.MATCH_PARENT
        dialog?.window?.setLayout(width, height)
        recyclerView = rota_min_recyclerview
        recyclerViewAdapter = RotaMinimaAdapter(rotasMinimas)
        recyclerView?.adapter = recyclerViewAdapter
        recyclerView?.layoutManager = GridLayoutManager(this.context, 1)
    }

}

