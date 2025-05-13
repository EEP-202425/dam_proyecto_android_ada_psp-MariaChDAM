package com.example.encaminoandroid.trayecto

data class Trayecto(
    val idTrayecto: Long? = null,
    val origen: String,
    val destino: String,
    val fecha: String, // Usa formato: "2024-05-07"
    val hora: String,  // Usa formato: "12:30"
    val plazas: Int,
    val precio: Double
)
