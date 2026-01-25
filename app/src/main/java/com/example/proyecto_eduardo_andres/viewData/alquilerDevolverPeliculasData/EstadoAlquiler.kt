package com.example.proyecto_eduardo_andres.viewData.alquilerDevolverPeliculasData

import java.util.Date

data class EstadoAlquiler(
    val estaAlquilada: Boolean,
    val fechaAlquiler: Date? = null,
    val fechaDevolucion: Date? = null
)