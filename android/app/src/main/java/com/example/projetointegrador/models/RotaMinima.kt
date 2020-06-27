package com.example.projetointegrador.models

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

class RotaMinima (
    @SerializedName(value = "Cidade")
    var cidadeAlvo:String = "",

    @SerializedName(value = "CidadeAnterior")
    var cidadeAnterior:String = "A",

    @SerializedName(value = "DistanciaMinima")
    var distanciaMinima: BigDecimal = BigDecimal.ZERO
)