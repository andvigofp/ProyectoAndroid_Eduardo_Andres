package com.example.proyecto_eduardo_andres.data.repository.alquilerSeriesRepository

import com.example.proyecto_eduardo_andres.modelo.EstadoAlquilerDto
import com.example.proyecto_eduardo_andres.modelo.SeriesDto
import com.example.proyecto_eduardo_andres.modelo.VideoClubOnlinePeliculasData
import com.example.proyecto_eduardo_andres.modelo.VideoClubOnlineSeriesData
import com.example.proyecto_eduardo_andres.modelo.serie
import java.util.Date
import kotlin.collections.set

/**
 *
 * Implementación ligera de IAlquilerSeriesRepository utilizada
 * principalmente para pruebas, previews o funcionamiento offline.
 *
 * Los datos se almacenan en memoria usando un MutableMap
 * donde la clave es el userId y el valor es la lista de series alquiladas.
 *
 * @author Eduardo
 * @see Implementación en memoria para el alquiler de series
 */
class AlquilerSeriesRepositoryInMemory : IAlquilerSeriesRepository {

    /**
     * Mapa en memoria que guarda las series alquiladas por usuario.
     * Key = userId
     * Value = Lista de series alquiladas por ese usuario
     */
    private val alquileres =
        mutableMapOf<String, MutableList<VideoClubOnlineSeriesData>>()

    /**
     * Alquila una serie para un usuario.
     *
     * @param userId Identificador único del usuario.
     * @param serie Serie que se desea alquilar.
     * @param onError Callback que se ejecuta si ocurre un error durante el proceso.
     * @param onSuccess Callback que se ejecuta cuando el alquiler se realiza correctamente.
     */
    override fun alquilarSerie(
        userId: String,
        serie: VideoClubOnlineSeriesData,
        onError: (Throwable) -> Unit,
        onSuccess: () -> Unit
    ) {
        try {
            val seriesUsuario = alquileres.getOrPut(userId) { mutableListOf() }

            // Evitar duplicados
            if (!seriesUsuario.any { it.nombre == serie.nombre }) {
                seriesUsuario.add(serie)
            }

            onSuccess()
        } catch (e: Throwable) {
            onError(e)
        }
    }

    /**
     * Devuelve una serie previamente alquilada.
     *
     * @param userId Identificador del usuario.
     * @param serie Serie que se desea devolver.
     * @param onError Callback que se ejecuta en caso de error.
     * @param onSuccess Callback que se ejecuta cuando la devolución se realiza correctamente.
     */
    override fun devolverSerie(
        userId: String,
        serie: VideoClubOnlineSeriesData,
        onError: (Throwable) -> Unit,
        onSuccess: () -> Unit
    ) {
        try {
            alquileres[userId]?.removeIf { it.nombre == serie.nombre }
            onSuccess()
        } catch (e: Throwable) {
            onError(e)
        }
    }

    /**
     * Obtiene el estado de alquiler de una serie para un usuario.
     *
     * @param userId Identificador del usuario.
     * @param serie Serie cuya información de alquiler se desea consultar.
     * @param onError Callback que se ejecuta si ocurre un error.
     * @param onSuccess Callback que devuelve un EstadoAlquilerDto con el estado actual.
     */
    override fun obtenerEstadoAlquiler(
        userId: String,
        serie: VideoClubOnlineSeriesData,
        onError: (Throwable) -> Unit,
        onSuccess: (EstadoAlquilerDto) -> Unit
    ) {
        try {
            val estaAlquilada = alquileres[userId]
                ?.any { it.nombre == serie.nombre }
                ?: false

            onSuccess(
                EstadoAlquilerDto(
                    estaAlquilada = estaAlquilada,
                    fechaAlquiler = null,
                    fechaDevolucion = null
                )
            )
        } catch (e: Throwable) {
            onError(e)
        }
    }

    /**
     * Obtiene todas las series alquiladas por un usuario.
     *
     * @param userId Identificador del usuario.
     * @param onError Callback que se ejecuta si ocurre un error.
     * @param onSuccess Callback que devuelve la lista de series alquiladas.
     */
    override fun obtenerSeriesAlquiladas(
        userId: String,
        onError: (Throwable) -> Unit,
        onSuccess: (List<VideoClubOnlineSeriesData>) -> Unit
    ) {
        try {
            onSuccess(alquileres[userId] ?: emptyList())
        } catch (e: Throwable) {
            onError(e)
        }
    }

    /**
     * Obtiene el catálogo completo de series disponibles.
     *
     * @param onError Callback que se ejecuta si ocurre un error al obtener el catálogo.
     * @param onSuccess Callback que devuelve la lista completa de series.
     */
    override fun obtenerSeries(
        onError: (Throwable) -> Unit,
        onSuccess: (List<VideoClubOnlineSeriesData>) -> Unit
    ) {
        try {
            onSuccess(SeriesDto().series)
        } catch (e: Throwable) {
            onError(e)
        }
    }
}