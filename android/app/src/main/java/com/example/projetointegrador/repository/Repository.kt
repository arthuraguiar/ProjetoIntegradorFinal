package com.example.projetointegrador.repository


import com.example.projetointegrador.BuildConfig
import com.example.projetointegrador.models.DistanciaMap
import com.example.projetointegrador.models.RotaMinima
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception


object Repository{

    private var retrofit =  RetrofitFactory.getInstance("192.168.0.12:8080")

    fun solveDijkstra(cidadeOrigem: String,
                      rotas:MutableList<DistanciaMap>,
                      sucess: (MutableList<RotaMinima>?) -> Unit,
                      error: (String) -> Unit) {
        retrofit.solveDijkstra(cidadeOrigem, rotas).enqueue(callback<MutableList<RotaMinima>>(sucess, error))
    }

    private fun <T>callback(sucessCallback: (T?) -> Unit, erroCallback: (String) -> Unit):Callback<T> {
        return object : Callback<T>{
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful){
                    sucessCallback.invoke(response.body())
                }else{
                    val error = try {
                        if (response.errorBody()?.string().isNullOrEmpty())
                            response.raw().toString()
                        else
                            JSONObject(response.errorBody()?.string()).optString("error", "NO ERROR BODY")
                    }catch (e:Exception){
                        e.message
                    }

                    if(BuildConfig.DEBUG)
                        erroCallback.invoke("Erro no servidor: ${response.message()}\n$error")
                    else
                        erroCallback.invoke("Erro no servidor: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable?) {
                erroCallback.invoke("Erro ao estabelecer conexão")
            }
        }
    }
}
