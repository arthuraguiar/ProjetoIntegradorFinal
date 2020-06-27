package com.example.projetointegrador.repository


import com.example.projetointegrador.models.DistanciaMap
import com.example.projetointegrador.models.RotaMinima
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface ServiceEndPoints {

    @POST("/solve/{cidadeorigem}")
    fun solveDijkstra(@Path("cidadeorigem") cidadeOrigem: String, @Body rotas:MutableList<DistanciaMap>):Call<MutableList<RotaMinima>>
}