package com.example.encaminoapp1.network

import com.example.encaminoapp1.trayecto.TrayectoApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private const val BASE_URL = "http://10.0.2.2:8080/api/"
object RetrofitInstance {
    val api: TrayectoApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TrayectoApi::class.java)
    }
}
