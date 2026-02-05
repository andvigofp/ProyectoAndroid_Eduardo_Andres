package com.example.proyecto_eduardo_andres.data.repository.alquilerPeliculasRepository

import android.content.Context
import com.example.proyecto_eduardo_andres.R
import com.example.proyecto_eduardo_andres.modelo.EstadoAlquilerDto
import com.example.proyecto_eduardo_andres.modelo.VideoClubOnlinePeliculasData
import com.example.proyecto_eduardo_andres.remote.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AlquilerPeliculaRepositoryRetrofit(private val context: Context) : IAlquilerPeliculasRepository {

    private val api = RetrofitClient.alquilerPeliculaApiService

    // ----------------------------------------------------
    // Obtener todas las películas disponibles
    // ----------------------------------------------------
    override fun obtenerPeliculas(
        onError: (Throwable) -> Unit,
        onSuccess: (List<VideoClubOnlinePeliculasData>) -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = api.obtenerPeliculas()
                if (response.isSuccessful) {
                    val body = response.body() ?: emptyList()
                    val mapped = body.map { dto ->
                        VideoClubOnlinePeliculasData(
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
                    onError(Throwable("Error al obtener películas: ${e.message}"))
                }
            }
        }
    }


    // ----------------------------------------------------
    // Implementación de IAlquilerPeliculasRepository
    // ----------------------------------------------------
    override fun alquilarPelicula(
        userId: String,
        pelicula: VideoClubOnlinePeliculasData,
        onError: (Throwable) -> Unit,
        onSuccess: () -> Unit
    ) {
        // Aquí iría la llamada Retrofit POST para alquilar
        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Ejemplo: api.alquilarPelicula(userId, pelicula.id)
                withContext(Dispatchers.Main) { onSuccess() }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) { onError(e) }
            }
        }
    }

    override fun devolverPelicula(
        userId: String,
        pelicula: VideoClubOnlinePeliculasData,
        onError: (Throwable) -> Unit,
        onSuccess: () -> Unit
    ) {
        // Aquí iría la llamada Retrofit POST para devolver
        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Ejemplo: api.devolverPelicula(userId, pelicula.id)
                withContext(Dispatchers.Main) { onSuccess() }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) { onError(e) }
            }
        }
    }

    override fun obtenerEstadoAlquiler(
        userId: String,
        pelicula: VideoClubOnlinePeliculasData,
        onError: (Throwable) -> Unit,
        onSuccess: (EstadoAlquilerDto) -> Unit
    ) {
        // Aquí iría la llamada Retrofit GET para obtener el estado de alquiler
        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Ejemplo: val estado = api.obtenerEstado(userId, pelicula.id)
                val estado = EstadoAlquilerDto(false, null, null)
                withContext(Dispatchers.Main) { onSuccess(estado) }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) { onError(e) }
            }
        }
    }

    override fun obtenerPeliculasAlquiladas(
        userId: String,
        onError: (Throwable) -> Unit,
        onSuccess: (List<VideoClubOnlinePeliculasData>) -> Unit
    ) {
        // Aquí iría la llamada Retrofit GET para obtener películas alquiladas
        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Ejemplo: val response = api.obtenerPeliculasAlquiladas(userId)
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
