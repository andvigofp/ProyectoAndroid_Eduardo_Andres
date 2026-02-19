package com.example.proyecto_eduardo_andres.data.repository.alquilerPeliculasRepository


import com.example.proyecto_eduardo_andres.modelo.EstadoAlquilerDto
import com.example.proyecto_eduardo_andres.modelo.PeliculasDto
import com.example.proyecto_eduardo_andres.modelo.VideoClubOnlinePeliculasData
import java.util.Date
/**
 *
 * Implementación en memoria del repositorio de alquiler
 *
 * @param Simula el almacenamiento de alquileres sin base de datos.
 * Los datos se mantienen únicamente mientras la aplicación está activa.
 *
 * @author Andrés
 */
class AlquilerPeliculasRepositoryInMemory : IAlquilerPeliculasRepository {

    /**
     * Mapa principal:
     * userId -> (peliculaId -> EstadoAlquilerDto)
     */
    private val alquileres: MutableMap<String, MutableMap<String, EstadoAlquilerDto>> =
        mutableMapOf()

    /**
     * Alquila una película durante 7 días.
     *
     * @param userId Identificador único del usuario
     * @param pelicula Película que se desea alquilar
     * @param onError Callback ejecutado si ocurre un error
     * @param onSuccess Callback ejecutado si el alquiler se realiza correctamente
     */
    override fun alquilarPelicula(
        userId: String,
        pelicula: VideoClubOnlinePeliculasData,
        onError: (Throwable) -> Unit,
        onSuccess: () -> Unit
    ) {
        try {
            val fechaAlquiler = Date()
            val fechaDevolucion = Date(fechaAlquiler.time + 7 * 24 * 60 * 60 * 1000L)

            val peliculasUsuario = alquileres.getOrPut(userId) { mutableMapOf() }

            peliculasUsuario[pelicula.id] = EstadoAlquilerDto(
                estaAlquilada = true,
                fechaAlquiler = fechaAlquiler,
                fechaDevolucion = fechaDevolucion
            )

            onSuccess()

        } catch (e: Throwable) {
            onError(e)
        }
    }

    /**
     * Devuelve una película previamente alquilada.
     *
     * @param userId Identificador único del usuario
     * @param pelicula Película que se desea devolver
     * @param onError Callback ejecutado si ocurre un error
     * @param onSuccess Callback ejecutado si la devolución se realiza correctamente
     */
    override fun devolverPelicula(
        userId: String,
        pelicula: VideoClubOnlinePeliculasData,
        onError: (Throwable) -> Unit,
        onSuccess: () -> Unit
    ) {
        try {
            val peliculasUsuario = alquileres[userId]

            peliculasUsuario?.get(pelicula.id)?.let { estadoActual ->
                peliculasUsuario[pelicula.id] = estadoActual.copy(
                    estaAlquilada = false,
                    fechaDevolucion = Date()
                )
            }

            onSuccess()

        } catch (e: Throwable) {
            onError(e)
        }
    }

    /**
     * Obtiene el estado actual del alquiler de una película.
     *
     * @param userId Identificador único del usuario
     * @param pelicula Película a consultar
     * @param onError Callback ejecutado si ocurre un error
     * @param onSuccess Devuelve el estado actual del alquiler
     */
    override fun obtenerEstadoAlquiler(
        userId: String,
        pelicula: VideoClubOnlinePeliculasData,
        onError: (Throwable) -> Unit,
        onSuccess: (EstadoAlquilerDto) -> Unit
    ) {
        try {
            val peliculasUsuario = alquileres[userId]

            val estado = peliculasUsuario?.get(pelicula.id)
                ?: EstadoAlquilerDto(false, null, null)

            onSuccess(estado)

        } catch (e: Throwable) {
            onError(e)
        }
    }

    /**
     * Obtiene el estado actual del alquiler de una película.
     *
     * @param userId Identificador único del usuario
     * @param pelicula Película a consultar
     * @param onError Callback ejecutado si ocurre un error
     * @param onSuccess Devuelve el estado actual del alquiler
     */
    override fun obtenerPeliculasAlquiladas(
        userId: String,
        onError: (Throwable) -> Unit,
        onSuccess: (List<VideoClubOnlinePeliculasData>) -> Unit
    ) {
        try {
            val peliculasUsuario = alquileres[userId] ?: emptyMap()
            val todasLasPeliculas = PeliculasDto().peliculas

            val peliculasAlquiladas = todasLasPeliculas.filter { pelicula ->
                peliculasUsuario[pelicula.id]?.estaAlquilada == true
            }

            onSuccess(peliculasAlquiladas)

        } catch (e: Throwable) {
            onError(e)
        }
    }

    /**
     * Obtiene el estado actual del alquiler de una película.
     *
     * @param userId Identificador único del usuario
     * @param pelicula Película a consultar
     * @param onError Callback ejecutado si ocurre un error
     * @param onSuccess Devuelve el estado actual del alquiler
     */
    override fun obtenerPeliculas(
        onError: (Throwable) -> Unit,
        onSuccess: (List<VideoClubOnlinePeliculasData>) -> Unit
    ) {
        try {
            val peliculas = PeliculasDto().peliculas
            onSuccess(peliculas)

        } catch (e: Throwable) {
            onError(e)
        }
    }
}
