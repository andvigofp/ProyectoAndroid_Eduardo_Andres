package com.example.proyecto_eduardo_andres.data.repository.alquilerPeliculasRepository

import com.example.proyecto_eduardo_andres.modelo.EstadoAlquilerDto
import com.example.proyecto_eduardo_andres.modelo.VideoClubOnlinePeliculasData

/**
 *
 * Repositorio de Alquiler de Películas
 *
 * @param Define las operaciones relacionadas con el alquiler y devolución
 * de películas dentro de la aplicación.
 *
 * @param Esta interfaz permite trabajar con diferentes implementaciones:
 * - Retrofit (API remota)
 * - Room (base de datos local)
 * - Híbrido (online + offline)
 *
 * @author Andrés
 */
interface IAlquilerPeliculasRepository {
    /**
     * Realiza el alquiler de una película para un usuario.
     *
     * @param userId Identificador único del usuario.
     * @param pelicula Película que se desea alquilar.
     * @param onError Callback que se ejecuta si ocurre un error.
     * @param onSuccess Callback que se ejecuta cuando el alquiler se realiza correctamente.
     */
    fun alquilarPelicula(
        userId: String,
        pelicula: VideoClubOnlinePeliculasData,
        onError: (Throwable) -> Unit,
        onSuccess: () -> Unit
    )

    /**
     * Realiza la devolución de una película previamente alquilada.
     *
     * @param userId Identificador único del usuario.
     * @param pelicula Película que se desea devolver.
     * @param onError Callback que se ejecuta si ocurre un error.
     * @param onSuccess Callback que se ejecuta cuando la devolución es exitosa.
     */
    fun devolverPelicula(
        userId: String,
        pelicula: VideoClubOnlinePeliculasData,
        onError: (Throwable) -> Unit,
        onSuccess: () -> Unit
    )

    /**
     * Obtiene el estado actual del alquiler de una película.
     *
     * @param userId Identificador único del usuario.
     * @param pelicula Película a consultar.
     * @param onError Callback que se ejecuta si ocurre un error.
     * @param onSuccess Devuelve un EstadoAlquilerDto con la información del alquiler.
     */
    fun obtenerEstadoAlquiler(
        userId: String,
        pelicula: VideoClubOnlinePeliculasData,
        onError: (Throwable) -> Unit,
        onSuccess: (EstadoAlquilerDto) -> Unit
    )

    /**
     * Obtiene la lista de películas actualmente alquiladas por un usuario.
     *
     * @param userId Identificador único del usuario.
     * @param onError Callback que se ejecuta si ocurre un error.
     * @param onSuccess Devuelve la lista de películas alquiladas.
     */
    fun obtenerPeliculasAlquiladas(
        userId: String,
        onError: (Throwable) -> Unit,
        onSuccess: (List<VideoClubOnlinePeliculasData>) -> Unit
    )

    /**
     * Obtiene la lista completa de películas disponibles.
     *
     * @param onError Callback que se ejecuta si ocurre un error.
     * @param onSuccess Devuelve la lista de películas disponibles.
     */
    fun obtenerPeliculas(
        onError: (Throwable) -> Unit,
        onSuccess: (List<VideoClubOnlinePeliculasData>) -> Unit
    )
}
