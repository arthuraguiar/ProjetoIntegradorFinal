package com.example.projetointegrador.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class DistanciaMap (
    @SerializedName("Cidade1")
    var cidade1:String = "",

    @SerializedName("Cidade2")
    var cidade2:String = "",

    @SerializedName("Distancia")
    var distancia: Double = 0.0
):Serializable