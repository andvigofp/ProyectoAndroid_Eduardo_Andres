package com.example.proyecto_eduardo_andres.viewmodel.ustate

import com.example.proyecto_eduardo_andres.modelo.VideoClubOnlineSeriesData
import java.util.Date

/**
 * @author Eduardo
 * @see AlquilarDevolverSeriesUiState
 *
 * @param UIState para alquilar y devolver series.
 *
 * @param serie Serie que se está alquilando o devolviendo.
 * @param serieAlquilada Indica si la serie se encuentra alquilada.
 * @param fechaAlquiler Fecha en la que se alquiló la serie.
 * @param fechaDevolucion Fecha en la que se devolvió la serie.
 * @param fechaLimiteDevolucion Fecha límite en la que se puede devolver la serie.
 * @param esMulta Indica si la serie tiene una multa.
 * @param isAlquilarButtonEnabled Indica si el botón de alquilar está habilitado.
 * @param isDevolverButtonEnabled Indica si el botón de devolver está habilitado.
 */
data class AlquilarDevolverSeriesUiState(
    val serie: VideoClubOnlineSeriesData,
    val serieAlquilada: Boolean = false,
    val fechaAlquiler: Date? = null,
    val fechaDevolucion: Date? = null,
    val fechaLimiteDevolucion: Date? = null,
    val esMulta: Boolean = false
) {
    val isAlquilarButtonEnabled: Boolean
        get() = !serieAlquilada

    val isDevolverButtonEnabled: Boolean
        get() = serieAlquilada

    val imagen: Int?
        get() = serie.imagen
    val nombreSerie: Int
        get() = serie.nombre
    val descripcion: Int
        get() = serie.descripcion
}