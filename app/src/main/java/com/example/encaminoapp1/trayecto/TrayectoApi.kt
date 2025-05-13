package com.example.encaminoapp1.trayecto

import com.example.encaminoandroid.trayecto.Trayecto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface TrayectoApi {

    @GET("trayectos/all")
    fun getAllTrayectos(): Call<List<Trayecto>>

    @POST("trayectos/create")
    fun createTrayecto(@Body trayecto: Trayecto): Call<Trayecto>

    @DELETE("trayectos/delete-{id}")
    fun deleteTrayecto(@Path("id") id: Long): Call<Void>
}
