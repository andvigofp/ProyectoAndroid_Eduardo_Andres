package com.example.proyecto_eduardo_andres.data.repository.alquilerSeriesSearchRepository

import com.example.proyecto_eduardo_andres.modelo.VideoClubOnlineSeriesData

/**
 *
 * Define las operaciones necesarias para obtener el listado de
 * series desde el sistema de búsqueda (puede ser remoto, local o híbrido).
 *
 * @author Eduardo
 * @see Repositorio encargado de gestionar la búsqueda de series.
 */
interface IAlquilerSearchSeriesRepository {

    /**
     * Obtiene la lista de series disponibles en la búsqueda.
     *
     * @param onError Callback que se ejecuta si ocurre un error
     * durante la obtención de los datos.
     *
     * @param onSuccess Callback que devuelve la lista de
     * [VideoClubOnlineSeriesData] obtenida correctamente.
     */
    fun obtenerSeriesSearch(
        onError: (Throwable) -> Unit,
        onSuccess: (List<VideoClubOnlineSeriesData>) -> Unit
    )
}

