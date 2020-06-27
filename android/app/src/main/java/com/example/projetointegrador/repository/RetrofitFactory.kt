package com.example.projetointegrador.repository

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitFactory(ip: String) {
    private val URL = "http://$ip/"
    private val timeout: Long = 60000

    private var gson = GsonBuilder()
        .setLenient()
        .create()

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(URL)
        .client(httpClient())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    private fun httpClient(): OkHttpClient {

        val clientBuilder = OkHttpClient.Builder()
            .connectTimeout(timeout, TimeUnit.SECONDS)
            .writeTimeout(timeout, TimeUnit.SECONDS)
            .readTimeout(timeout, TimeUnit.SECONDS)


        return clientBuilder.build()
    }

    companion object {
        fun getInstance(ip: String): ServiceEndPoints {
            val client = RetrofitFactory(ip)
            return client.retrofit.create(ServiceEndPoints::class.java)
        }
    }
}
