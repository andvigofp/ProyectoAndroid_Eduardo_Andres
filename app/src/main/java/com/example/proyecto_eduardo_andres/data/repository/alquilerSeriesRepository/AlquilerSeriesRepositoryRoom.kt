package com.example.proyecto_eduardo_andres.data.repository.alquilerSeriesRepository

import com.example.proyecto_eduardo_andres.data.repository.seriesRepository.ISeriesRepository
import com.example.proyecto_eduardo_andres.data.room.dao.AlquilerSerieDao
import com.example.proyecto_eduardo_andres.data.room.entity.AlquilerSerie
import com.example.proyecto_eduardo_andres.modelo.EstadoAlquilerDto
import com.example.proyecto_eduardo_andres.modelo.VideoClubOnlineSeriesData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Date

class AlquilerSeriesRepositoryRoom(
    private val alquilerDao: AlquilerSerieDao,
    private val seriesRepository: ISeriesRepository
) : IAlquilerSeriesRepository {

    // ----------------------------------------------------
    // ALQUILAR
    // ----------------------------------------------------
    override fun alquilarSerie(
        userId: String,
        serie: VideoClubOnlineSeriesData,
        onError: (Throwable) -> Unit,
        onSuccess: () -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {

            try {
                // Intento remoto (si quieres usarlo)
                // api.alquilar(userId, serie.id)

            } catch (_: Exception) {
                // Si falla red seguimos offline
            }

            val ahora = System.currentTimeMillis()
            val devolucion = ahora + 7 * 24 * 60 * 60 * 1000L

            alquilerDao.insert(
                AlquilerSerie(
                    userId = userId,
                    serieId = serie.id,
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
    override fun devolverSerie(
        userId: String,
        serie: VideoClubOnlineSeriesData,
        onError: (Throwable) -> Unit,
        onSuccess: () -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {

            val actual = alquilerDao.getEstado(userId, serie.id)

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
        serie: VideoClubOnlineSeriesData,
        onError: (Throwable) -> Unit,
        onSuccess: (EstadoAlquilerDto) -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {

            val estado = alquilerDao.getEstado(userId, serie.id)

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
    override fun obtenerSeriesAlquiladas(
        userId: String,
        onError: (Throwable) -> Unit,
        onSuccess: (List<VideoClubOnlineSeriesData>) -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {

            val alquileres = alquilerDao.getSeriesAlquiladas(userId)

            // Obtener catálogo real desde repository de películas
            seriesRepository.obtenerSeries(
                onError = onError,
                onSuccess = { catalogo ->

                    val seriesAlquiladas = catalogo.filter { pelicula ->
                        alquileres.any { it.serieId == pelicula.id && it.estaAlquilada }
                    }

                    CoroutineScope(Dispatchers.Main).launch {
                        onSuccess(seriesAlquiladas)
                    }
                }
            )
        }
    }

    // ----------------------------------------------------
    // CATÁLOGO (IMPORTANTE - YA NO DEVUELVE EMPTY LIST)
    // ----------------------------------------------------
    override fun obtenerSeries(
        onError: (Throwable) -> Unit,
        onSuccess: (List<VideoClubOnlineSeriesData>) -> Unit
    ) {
        seriesRepository.obtenerSeries(
            onError = onError,
            onSuccess = onSuccess
        )
    }
}
