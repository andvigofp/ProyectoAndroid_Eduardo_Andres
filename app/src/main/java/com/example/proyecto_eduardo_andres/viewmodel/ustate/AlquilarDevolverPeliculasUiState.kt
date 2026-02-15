package com.example.proyecto_eduardo_andres.viewmodel.ustate

import com.example.proyecto_eduardo_andres.modelo.VideoClubOnlinePeliculasData
import java.util.Date

data class AlquilarDevolverPeliculasUiState(

    val pelicula: VideoClubOnlinePeliculasData? = null,

    val peliculaAlquilada: Boolean = false,

    val fechaAlquiler: Date? = null,

    val fechaDevolucion: Date? = null,

    val fechaLimiteDevolucion: Date? = null,

    val esMulta: Boolean = false

) {

    val isAlquilarButtonEnabled: Boolean
        get() = !peliculaAlquilada

    val isDevolverButtonEnabled: Boolean
        get() = peliculaAlquilada
}
