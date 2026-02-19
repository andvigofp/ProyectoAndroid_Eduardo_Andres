package com.example.proyecto_eduardo_andres.data.repository.alquilerSeriesRepository

import android.content.Context
import com.example.proyecto_eduardo_andres.R
import com.example.proyecto_eduardo_andres.modelo.EstadoAlquilerDto
import com.example.proyecto_eduardo_andres.modelo.VideoClubOnlineSeriesData
import com.example.proyecto_eduardo_andres.remote.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 *
 * Repositorio que conecta con la API remota mediante Retrofit
 * para gestionar el catálogo y el estado de alquiler de series.
 *
 *  @author Eduardo
 *  @see Implementación Retrofit para el alquiler de series
 * @property context Contexto de la aplicación utilizado para resolver
 * recursos dinámicos (strings y drawables).
 */
class AlquilerSerieRepositoryRetrofit(private val context: Context) :
    IAlquilerSeriesRepository {

    private val api = RetrofitClient.aquilerSerieApiService

    /**
     * Obtiene todas las series disponibles desde la API remota.
     *
     * @param onError Callback que se ejecuta si ocurre un error
     * durante la llamada a la API.
     * @param onSuccess Callback que devuelve la lista de series
     * mapeadas al modelo de UI.
     */
    override fun obtenerSeries(
        onError: (Throwable) -> Unit,
        onSuccess: (List<VideoClubOnlineSeriesData>) -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = api.obtenerSeries()
                if (response.isSuccessful) {
                    val body = response.body() ?: emptyList()
                    val mapped = body.map { dto ->
                        VideoClubOnlineSeriesData(
                            id = dto.id,
                            nombre = resolveStringResource(dto.nombre),
                            imagen = resolveDrawableResource(dto.imagen),
                            descripcion = resolveStringResource(dto.descripcion),
                            categoria = 0
                        )
                    }
                    withContext(Dispatchers.Main) { onSuccess(mapped) }
                } else {
                    withContext(Dispatchers.Main) {
                        onError(Throwable("Error HTTP ${response.code()} ${response.message()}"))
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    onError(Throwable("Error al obtener series: ${e.message}"))
                }
            }
        }
    }


    /**
     * Realiza el alquiler de una serie mediante llamada a la API.
     *
     * @param userId Identificador del usuario que realiza el alquiler.
     * @param serie Serie que se desea alquilar.
     * @param onError Callback que se ejecuta si ocurre un error
     * durante la operación.
     * @param onSuccess Callback que se ejecuta cuando el alquiler
     * se completa correctamente.
     */
    override fun alquilarSerie(
        userId: String,
        serie: VideoClubOnlineSeriesData,
        onError: (Throwable) -> Unit,
        onSuccess: () -> Unit
    ) {
        // Aquí iría la llamada Retrofit POST para alquilar
        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Ejemplo: api.alquilarSerie(userId, serie.id)
                withContext(Dispatchers.Main) { onSuccess() }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) { onError(e) }
            }
        }
    }

    /**
     * Devuelve una serie previamente alquilada.
     *
     * @param userId Identificador del usuario.
     * @param serie Serie que se desea devolver.
     * @param onError Callback que se ejecuta si ocurre un error.
     * @param onSuccess Callback que se ejecuta cuando la devolución
     * se realiza correctamente.
     */
    override fun devolverSerie(
        userId: String,
        serie: VideoClubOnlineSeriesData,
        onError: (Throwable) -> Unit,
        onSuccess: () -> Unit
    ) {
        // Aquí iría la llamada Retrofit POST para devolver
        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Ejemplo: api.devolverSerie(userId, pelicula.id)
                withContext(Dispatchers.Main) { onSuccess() }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) { onError(e) }
            }
        }
    }

    /**
     * Obtiene el estado de alquiler de una serie para un usuario.
     *
     * @param userId Identificador del usuario.
     * @param serie Serie cuya información de alquiler se desea consultar.
     * @param onError Callback que se ejecuta si ocurre un error.
     * @param onSuccess Callback que devuelve un EstadoAlquilerDto
     * con el estado actual.
     */
    override fun obtenerEstadoAlquiler(
        userId: String,
        serie: VideoClubOnlineSeriesData,
        onError: (Throwable) -> Unit,
        onSuccess: (EstadoAlquilerDto) -> Unit
    ) {
        // Aquí iría la llamada Retrofit GET para obtener el estado de alquiler
        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Ejemplo: val estado = api.obtenerEstado(userId, serie.id)
                val estado = EstadoAlquilerDto(false, null, null)
                withContext(Dispatchers.Main) { onSuccess(estado) }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) { onError(e) }
            }
        }
    }

    /**
     * Obtiene la lista de series alquiladas por un usuario.
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
        // Aquí iría la llamada Retrofit GET para obtener series alquiladas
        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Ejemplo: val response = api.obtenerSeriesAlquiladas(userId)
                withContext(Dispatchers.Main) { onSuccess(emptyList()) }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) { onError(e) }
            }
        }
    }

    /**
     * Resuelve dinámicamente un String Resource a partir
     * del nombre recibido desde la API.
     *
     * @param value Nombre del recurso en formato "R.string.nombre".
     * @return Identificador del recurso String.
     */
    private fun resolveStringResource(value: String): Int {
        if (value.isBlank()) return R.string.app_name
        val name = value.substringAfter("R.string.").substringAfterLast('.')
        val resId = context.resources.getIdentifier(name, "string", context.packageName)
        return if (resId != 0) resId else R.string.app_name
    }

    /**
     * Resuelve dinámicamente un Drawable Resource a partir
     * del nombre recibido desde la API.
     *
     * @param value Nombre del recurso en formato "R.drawable.nombre".
     * @return Identificador del recurso Drawable o null si no existe.
     */
    private fun resolveDrawableResource(value: String): Int? {
        if (value.isBlank()) return null
        val name = value.substringAfter("R.drawable.").substringAfterLast('.')
        val resId = context.resources.getIdentifier(name, "drawable", context.packageName)
        return if (resId != 0) resId else null
    }
}