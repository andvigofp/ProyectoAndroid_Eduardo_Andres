package com.example.proyecto_eduardo_andres.data.repository.seriesRepository

import com.example.proyecto_eduardo_andres.modelo.VideoClubOnlineSeriesData

/**
 *
 * Interfaz que define las operaciones necesarias para obtener
 * el catálogo de series del VideoClub.
 *
 * Puede tener implementaciones en memoria, Retrofit o híbridas con Room.
 *
 * @author Eduardo
 * @see Repositorio de acceso a datos para Series
 */
interface ISeriesRepository {
    fun obtenerSeries(
        onError: (Throwable) -> Unit,
        onSuccess: (List<VideoClubOnlineSeriesData>) -> Unit
    )
}



