package com.example.proyecto_eduardo_andres.viewData.alquilerDevolverSeriesData

import com.example.proyecto_eduardo_andres.viewData.listaSeriesData.VideoClubOnlineSeriesData
import java.util.Date

data class AlquilarDevolverSeriesUiState(
    val serie: VideoClubOnlineSeriesData, // Serie seleccionada
    val serieAlquilada: Boolean = false, // Si está alquilada o no
    val fechaAlquiler: Date? = null, // Fecha de alquiler
    val fechaDevolucion: Date? = null // Fecha de devolución
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
