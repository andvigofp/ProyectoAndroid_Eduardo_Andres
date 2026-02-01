package com.example.proyecto_eduardo_andres.modelo

import java.util.Date

data class EstadoAlquilerDto(
    val estaAlquilada: Boolean,
    val fechaAlquiler: Date? = null,
    val fechaDevolucion: Date? = null
)