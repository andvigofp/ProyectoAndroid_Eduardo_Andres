package com.example.proyecto_eduardo_andres.modelo

import java.util.Date

/**
 * Modelo de datos que representa el estado de alquiler
 * de una película o serie.
 *
 * @author Andrés
 *
 * @param estaAlquilada Indica si la película o serie está alquilada o no.
 * @param fechaAlquiler Fecha en la que la película o serie fue alquilada.
 * @param fechaDevolucion Fecha en la que la película o serie fue devuelta.
 */
data class EstadoAlquilerDto(
    val estaAlquilada: Boolean,
    val fechaAlquiler: Date? = null,
    val fechaDevolucion: Date? = null
)