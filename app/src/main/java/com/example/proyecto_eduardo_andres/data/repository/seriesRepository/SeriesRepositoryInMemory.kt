package com.example.proyecto_eduardo_andres.data.repository.seriesRepository

import com.example.proyecto_eduardo_andres.modelo.SeriesDto
import com.example.proyecto_eduardo_andres.modelo.VideoClubOnlineSeriesData

/**
 *
 * Implementación en memoria del repositorio de series.
 *
 * Esta clase proporciona una fuente de datos local simulada
 * utilizando un DTO estático (SeriesDto), ideal para pruebas
 * y Previews sin necesidad de conexión a red.
 *
 * @author Eduardo
 */
class SeriesRepositoryInMemory :
    ISeriesRepository {

    /**
     * Obtiene la lista completa de series disponibles.
     *
     * @param onError Callback que se ejecuta si ocurre un error durante la obtención de datos.
     * @param onSuccess Callback que devuelve la lista de series disponibles.
     */
    override fun obtenerSeries(
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



