package com.example.proyecto_eduardo_andres.viewData.alquilerDevolverPeliculasData

import com.example.proyecto_eduardo_andres.viewData.listaPeliculasData.VideoClubOnlinePeliculasData
import java.util.Date

data class AlquilarDevolverPeliculasUiState(
    val pelicula: VideoClubOnlinePeliculasData, // Serie seleccionada
    val peliculaAlquilada: Boolean = false, // Si está alquilada o no
    val fechaAlquiler: Date? = null, // Fecha de alquiler
    val fechaDevolucion: Date? = null // Fecha de devolución
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