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

class AlquilerPeliculasRepositoryRoom(
    private val alquilerDao: AlquilerPeliculaDao,
    private val peliculasRepository: IPeliculasRepository
) : IAlquilerPeliculasRepository {

    // ----------------------------------------------------
    // ALQUILAR
    // ----------------------------------------------------
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

    // ----------------------------------------------------
    // DEVOLVER
    // ----------------------------------------------------
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

    // ----------------------------------------------------
    // ESTADO ALQUILER
    // ----------------------------------------------------
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

    // ----------------------------------------------------
    // PELÍCULAS ALQUILADAS
    // ----------------------------------------------------
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

    // ----------------------------------------------------
    // CATÁLOGO (IMPORTANTE - YA NO DEVUELVE EMPTY LIST)
    // ----------------------------------------------------
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
