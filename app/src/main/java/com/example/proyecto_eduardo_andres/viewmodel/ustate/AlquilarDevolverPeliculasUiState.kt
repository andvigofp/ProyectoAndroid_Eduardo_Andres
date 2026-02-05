package com.example.proyecto_eduardo_andres.viewmodel.ustate

import com.example.proyecto_eduardo_andres.modelo.VideoClubOnlinePeliculasData
import java.util.Date

data class AlquilarDevolverPeliculasUiState(
    val pelicula: VideoClubOnlinePeliculasData,
    val peliculaAlquilada: Boolean = false,
    val fechaAlquiler: Date? = null,
    val fechaDevolucion: Date? = null,
    val fechaLimiteDevolucion: Date? = null, // NUEVO: fecha límite para devolver
    val esMulta: Boolean = false              // NUEVO: si la devolución tiene multa
) {
    val isAlquilarButtonEnabled: Boolean
        get() = !peliculaAlquilada

    val isDevolverButtonEnabled: Boolean
        get() = peliculaAlquilada

    val imagen: Int?
        get() = pelicula.imagen
    val nombrePelicula: Int
        get() = pelicula.nombre
    val descripcion: Int
        get() = pelicula.descripcion
}
