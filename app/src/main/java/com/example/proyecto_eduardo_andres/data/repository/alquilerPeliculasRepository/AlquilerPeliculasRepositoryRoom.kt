package com.example.proyecto_eduardo_andres.data.repository.alquilerPeliculasRepository

import com.example.proyecto_eduardo_andres.data.repository.peliculasRepository.IPeliculasRepository
import com.example.proyecto_eduardo_andres.data.room.dao.AlquilerPeliculaDao
import com.example.proyecto_eduardo_andres.data.room.entity.AlquilerPelicula
import com.example.proyecto_eduardo_andres.modelo.EstadoAlquilerDto
import com.example.proyecto_eduardo_andres.modelo.VideoClubOnlinePeliculasData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Date

/**
 * Repositorio híbrido Room para alquiler de películas
 *
 * Implementación local del repositorio de alquiler que trabaja
 * principalmente con Room (persistencia offline).
 *
 * Permite:
 * - Alquilar películas
 * - Devolver películas
 * - Consultar estado de alquiler
 * - Obtener películas alquiladas
 * - Obtener catálogo completo
 *
 * Utiliza un DAO para la persistencia y el repositorio de películas
 * para obtener el catálogo completo.
 *
 * @param alquilerDao DAO encargado de la persistencia de alquileres en Room.
 * @param peliculasRepository Repositorio de películas para obtener el catálogo completo.
 *
 * @author Andrés
 */
class AlquilerPeliculasRepositoryRoom(
    private val alquilerDao: AlquilerPeliculaDao,
    private val peliculasRepository: IPeliculasRepository
) : IAlquilerPeliculasRepository {

    /**
     * Registra el alquiler de una película para un usuario.
     *
     * Guarda la información en Room con una duración de 7 días.
     *
     * @param userId Identificador único del usuario.
     * @param pelicula Película que se desea alquilar.
     * @param onError Callback ejecutado si ocurre un error.
     * @param onSuccess Callback ejecutado cuando el alquiler se registra correctamente.
     */
    override fun alquilarPelicula(
        userId: String,
        pelicula: VideoClubOnlinePeliculasData,
        onError: (Throwable) -> Unit,
        onSuccess: () -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {

            try {
                // Intento remoto (si quieres usarlo)
                // api.alquilar(userId, pelicula.id)

            } catch (_: Exception) {
                // Si falla red seguimos offline
            }

            val ahora = System.currentTimeMillis()
            val devolucion = ahora + 7 * 24 * 60 * 60 * 1000L

            alquilerDao.insert(
                AlquilerPelicula(
                    userId = userId,
                    peliculaId = pelicula.id,
                    estaAlquilada = true,
                    fechaAlquiler = ahora,
                    fechaDevolucion = devolucion
                )
            )

            withContext(Dispatchers.Main) { onSuccess() }
        }
    }

    /**
     * Registra la devolución de una película.
     *
     * Actualiza el estado en la base de datos marcando
     * la película como no alquilada.
     *
     * @param userId Identificador único del usuario.
     * @param pelicula Película que se desea devolver.
     * @param onError Callback ejecutado si ocurre un error.
     * @param onSuccess Callback ejecutado cuando la devolución se realiza correctamente.
     */
    override fun devolverPelicula(
        userId: String,
        pelicula: VideoClubOnlinePeliculasData,
        onError: (Throwable) -> Unit,
        onSuccess: () -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {

            val actual = alquilerDao.getEstado(userId, pelicula.id)

            actual?.let {
                alquilerDao.insert(
                    it.copy(
                        estaAlquilada = false,
                        fechaDevolucion = System.currentTimeMillis()
                    )
                )
            }

            withContext(Dispatchers.Main) { onSuccess() }
        }
    }

    /**
     * Obtiene el estado actual de alquiler de una película.
     *
     * @param userId Identificador único del usuario.
     * @param pelicula Película a consultar.
     * @param onError Callback ejecutado si ocurre un error.
     * @param onSuccess Devuelve un DTO con el estado del alquiler.
     */
    override fun obtenerEstadoAlquiler(
        userId: String,
        pelicula: VideoClubOnlinePeliculasData,
        onError: (Throwable) -> Unit,
        onSuccess: (EstadoAlquilerDto) -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {

            val estado = alquilerDao.getEstado(userId, pelicula.id)

            val dto = estado?.let {
                EstadoAlquilerDto(
                    estaAlquilada = it.estaAlquilada,
                    fechaAlquiler = it.fechaAlquiler?.let { Date(it) },
                    fechaDevolucion = it.fechaDevolucion?.let { Date(it) }
                )
            } ?: EstadoAlquilerDto(false, null, null)

            withContext(Dispatchers.Main) { onSuccess(dto) }
        }
    }

    /**
     * Obtiene todas las películas actualmente alquiladas por un usuario.
     *
     * Cruza los datos almacenados en Room con el catálogo completo
     * proveniente del repositorio de películas.
     *
     * @param userId Identificador único del usuario.
     * @param onError Callback ejecutado si ocurre un error.
     * @param onSuccess Devuelve la lista de películas alquiladas.
     */
    override fun obtenerPeliculasAlquiladas(
        userId: String,
        onError: (Throwable) -> Unit,
        onSuccess: (List<VideoClubOnlinePeliculasData>) -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {

            val alquileres = alquilerDao.getPeliculasAlquiladas(userId)

            // Obtener catálogo real desde repository de películas
            peliculasRepository.obtenerPeliculas(
                onError = onError,
                onSuccess = { catalogo ->

                    val peliculasAlquiladas = catalogo.filter { pelicula ->
                        alquileres.any { it.peliculaId == pelicula.id && it.estaAlquilada }
                    }

                    CoroutineScope(Dispatchers.Main).launch {
                        onSuccess(peliculasAlquiladas)
                    }
                }
            )
        }
    }

    /**
     * Devuelve el catálogo completo de películas.
     *
     * Delegado directamente al repositorio de películas.
     *
     * @param onError Callback ejecutado si ocurre un error.
     * @param onSuccess Devuelve la lista completa de películas.
     */
    override fun obtenerPeliculas(
        onError: (Throwable) -> Unit,
        onSuccess: (List<VideoClubOnlinePeliculasData>) -> Unit
    ) {
        peliculasRepository.obtenerPeliculas(
            onError = onError,
            onSuccess = onSuccess
        )
    }
}