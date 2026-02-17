package com.example.proyecto_eduardo_andres.data.repository.alquilerSeriesRepository

import com.example.proyecto_eduardo_andres.modelo.EstadoAlquilerDto
import com.example.proyecto_eduardo_andres.modelo.VideoClubOnlinePeliculasData
import com.example.proyecto_eduardo_andres.modelo.VideoClubOnlineSeriesData

/**
 *
 * Interfaz que define las operaciones necesarias para gestionar
 * el alquiler y devolución de series dentro de la aplicación.
 *
 * Permite:
 * - Alquilar una serie
 * - Devolver una serie
 * - Consultar el estado de alquiler
 * - Obtener las series alquiladas por un usuario
 * - Obtener el catálogo completo de series
 *
 * @author Eduardo
 * @see Contrato para la gestión del alquiler de series
 */
interface IAlquilerSeriesRepository {

    /**
     * Permite alquilar una serie para un usuario.
     *
     * @param userId Identificador único del usuario que realiza el alquiler.
     * @param serie Serie que se desea alquilar.
     * @param onError Callback que se ejecuta si ocurre un error durante el proceso.
     * @param onSuccess Callback que se ejecuta si el alquiler se realiza correctamente.
     */
    fun alquilarSerie(
        userId: String,
        serie: VideoClubOnlineSeriesData,
        onError: (Throwable) -> Unit,
        onSuccess: () -> Unit
    )

    /**
     * Permite devolver una serie previamente alquilada.
     *
     * @param userId Identificador único del usuario que devuelve la serie.
     * @param serie Serie que se desea devolver.
     * @param onError Callback que se ejecuta si ocurre un error.
     * @param onSuccess Callback que se ejecuta cuando la devolución se completa correctamente.
     */
    fun devolverSerie(
        userId: String,
        serie: VideoClubOnlineSeriesData,
        onError: (Throwable) -> Unit,
        onSuccess: () -> Unit
    )

    /**
     * Obtiene el estado actual de alquiler de una serie para un usuario.
     *
     * @param userId Identificador del usuario.
     * @param serie Serie cuya información de alquiler se desea consultar.
     * @param onError Callback que se ejecuta en caso de error.
     * @param onSuccess Callback que devuelve el estado de alquiler encapsulado
     * en un objeto EstadoAlquilerDto.
     */
    fun obtenerEstadoAlquiler(
        userId: String,
        serie: VideoClubOnlineSeriesData,
        onError: (Throwable) -> Unit,
        onSuccess: (EstadoAlquilerDto) -> Unit
    )

    /**
     * Obtiene la lista de series actualmente alquiladas por un usuario.
     *
     * @param userId Identificador del usuario.
     * @param onError Callback que se ejecuta si ocurre un error.
     * @param onSuccess Callback que devuelve la lista de series alquiladas.
     */
    fun obtenerSeriesAlquiladas(
        userId: String,
        onError: (Throwable) -> Unit,
        onSuccess: (List<VideoClubOnlineSeriesData>) -> Unit
    )

    /**
     * Obtiene el catálogo completo de series disponibles.
     *
     * @param onError Callback que se ejecuta si ocurre un error al obtener el catálogo.
     * @param onSuccess Callback que devuelve la lista completa de series disponibles.
     */
    fun obtenerSeries(
        onError: (Throwable) -> Unit,
        onSuccess: (List<VideoClubOnlineSeriesData>) -> Unit
    )
}
