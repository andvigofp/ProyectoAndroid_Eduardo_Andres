package com.example.proyecto_eduardo_andres.viewmodel.ustate

import com.example.proyecto_eduardo_andres.modelo.VideoClubOnlinePeliculasData
import java.util.Date

/**
 * @author Andrés
 * @see AlquilarDevolverPeliculasUiState
 *
 * @param UIState para alquilar y devolver películas.
 *
 * @param pelicula Película que se está alquilando o devolviendo.
 * @param peliculaAlquilada Indica si la película se encuentra alquilada.
 * @param fechaAlquiler Fecha en la que se alquiló la película.
 * @param fechaDevolucion Fecha en la que se devolvió la película.
 * @param fechaLimiteDevolucion Fecha límite en la que se puede devolver la película.
 * @param esMulta Indica si la película tiene una multa.
 * @param isAlquilarButtonEnabled Indica si el botón de alquilar está habilitado.
 * @param isDevolverButtonEnabled Indica si el botón de devolver está habilitado.
 */
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
