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

/**
 *
 * Repositorio híbrido que gestiona el alquiler de series utilizando
 * almacenamiento local con Room y delegando el catálogo completo
 * al repository principal de series.
 *
 * @author Andrés
 * @see Implementación Room para el alquiler de series
 *
 * @property alquilerDao DAO encargado de las operaciones
 * sobre la tabla de alquiler de series.
 * @property seriesRepository Repository que proporciona
 * el catálogo completo de series.
 */
class AlquilerSeriesRepositoryRoom(
    private val alquilerDao: AlquilerSerieDao,
    private val seriesRepository: ISeriesRepository
) : IAlquilerSeriesRepository {

    /**
     * Registra el alquiler de una serie para un usuario.
     *
     * @param userId Identificador único del usuario.
     * @param serie Serie que se desea alquilar.
     * @param onError Callback que se ejecuta si ocurre un error
     * durante el proceso de inserción.
     * @param onSuccess Callback que se ejecuta cuando el alquiler
     * se registra correctamente.
     */
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

    /**
     * Registra la devolución de una serie alquilada.
     *
     * @param userId Identificador del usuario.
     * @param serie Serie que se desea devolver.
     * @param onError Callback que se ejecuta si ocurre un error.
     * @param onSuccess Callback que se ejecuta cuando la devolución
     * se registra correctamente.
     */
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

    /**
     * Obtiene el estado actual de alquiler de una serie.
     *
     * @param userId Identificador del usuario.
     * @param serie Serie cuya información de alquiler se desea consultar.
     * @param onError Callback que se ejecuta si ocurre un error.
     * @param onSuccess Callback que devuelve un EstadoAlquilerDto
     * con la información del estado.
     */
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

    /**
     * Obtiene la lista de series actualmente alquiladas por un usuario.
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

    /**
     * Obtiene el catálogo completo de series delegando
     * la responsabilidad al repository principal.
     *
     * @param onError Callback que se ejecuta si ocurre un error.
     * @param onSuccess Callback que devuelve la lista completa de series.
     */
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