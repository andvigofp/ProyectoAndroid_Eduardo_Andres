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

class AlquilerSerieRepositoryRetrofit(private val context: Context) :
    IAlquilerSeriesRepository {

    private val api = RetrofitClient.aquilerSerieApiService

    // ----------------------------------------------------
    // Obtener todas las series disponibles
    // ----------------------------------------------------
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


    // ----------------------------------------------------
    // Implementación de IAlquilerPeliculasRepository
    // ----------------------------------------------------
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

    // ----------------------------------------------------
    // Helpers para recursos
    // ----------------------------------------------------
    private fun resolveStringResource(value: String): Int {
        if (value.isBlank()) return R.string.app_name
        val name = value.substringAfter("R.string.").substringAfterLast('.')
        val resId = context.resources.getIdentifier(name, "string", context.packageName)
        return if (resId != 0) resId else R.string.app_name
    }

    private fun resolveDrawableResource(value: String): Int? {
        if (value.isBlank()) return null
        val name = value.substringAfter("R.drawable.").substringAfterLast('.')
        val resId = context.resources.getIdentifier(name, "drawable", context.packageName)
        return if (resId != 0) resId else null
    }
}
