package com.example.proyecto_eduardo_andres.data.repository.alquilerSeriesSearchRepository

import com.example.proyecto_eduardo_andres.modelo.SeriesDto
import com.example.proyecto_eduardo_andres.modelo.VideoClubOnlineSeriesData

/**
 *
 * Implementación ligera del repositorio de búsqueda de series.
 * Se utiliza principalmente en Previews o en entornos donde
 * no se necesita conexión a red ni base de datos local.
 *
 * Devuelve datos simulados obtenidos desde SeriesDto.
 *
 * @author Eduardo
 * @see Implementación en memoria para búsqueda de series
 */
class AlquilerSearchSeriesRepositoryInMemory : IAlquilerSearchSeriesRepository {

    /**
     * Obtiene la lista de series disponibles para búsqueda.
     *
     * @param onError Callback que se ejecuta si ocurre un error durante la obtención.
     * @param onSuccess Callback que devuelve la lista de series disponibles.
     */
    override fun obtenerSeriesSearch(
        onError: (Throwable) -> Unit,
        onSuccess: (List<VideoClubOnlineSeriesData>) -> Unit
    ) {
        try {
            val series = SeriesDto().series
            onSuccess(series)
        } catch (e: Throwable) {
            onError(e)
        }
    }
}

