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

/**
 * ===============================================================
 * Implementación Retrofit del repositorio de alquiler de películas
 * ===============================================================
 *
 * Esta clase gestiona la comunicación con el servidor remoto
 * utilizando Retrofit para:
 * - Obtener películas
 * - Alquilar películas
 * - Devolver películas
 * - Consultar estado de alquiler
 *
 * Convierte los DTO recibidos desde la API en modelos de la capa UI.
 *
 * @param context Contexto de la aplicación necesario para resolver
 * recursos dinámicos (R.string / R.drawable).
 *
 * @author Andrés
 */
class AlquilerPeliculaRepositoryRetrofit(private val context: Context) : IAlquilerPeliculasRepository {

    private val api = RetrofitClient.alquilerPeliculaApiService

    /**
     * Obtiene todas las películas disponibles desde el servidor.
     *
     * @param onError Callback ejecutado si ocurre un error HTTP o de red.
     * @param onSuccess Devuelve la lista de películas mapeadas al modelo UI.
     */
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


    /**
     * Envía al servidor la solicitud para alquilar una película.
     *
     * @param userId Identificador único del usuario.
     * @param pelicula Película que se desea alquilar.
     * @param onError Callback ejecutado si ocurre un error en la petición.
     * @param onSuccess Callback ejecutado si el alquiler se realiza correctamente.
     */
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

    /**
     * Envía al servidor la solicitud para devolver una película.
     *
     * @param userId Identificador único del usuario.
     * @param pelicula Película que se desea devolver.
     * @param onError Callback ejecutado si ocurre un error en la petición.
     * @param onSuccess Callback ejecutado si la devolución se realiza correctamente.
     */
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

    /**
     * Obtiene el estado actual de alquiler de una película.
     *
     * @param userId Identificador único del usuario.
     * @param pelicula Película a consultar.
     * @param onError Callback ejecutado si ocurre un error.
     * @param onSuccess Devuelve el estado actual del alquiler.
     */
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

    /**
     * Obtiene desde el servidor las películas alquiladas por un usuario.
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

    /**
     * Resuelve dinámicamente un recurso String a partir de su nombre.
     *
     * @param Nombre del recurso en formato "R.string.nombre".
     * @return ID del recurso String correspondiente.
     */
    private fun resolveStringResource(value: String): Int {
        if (value.isBlank()) return R.string.app_name
        val name = value.substringAfter("R.string.").substringAfterLast('.')
        val resId = context.resources.getIdentifier(name, "string", context.packageName)
        return if (resId != 0) resId else R.string.app_name
    }


    /**
     * Resuelve dinámicamente un recurso Drawable a partir de su nombre.
     *
     * @param Nombre del recurso en formato "R.drawable.nombre".
     * @return ID del recurso Drawable o null si no existe.
     */
    private fun resolveDrawableResource(value: String): Int? {
        if (value.isBlank()) return null
        val name = value.substringAfter("R.drawable.").substringAfterLast('.')
        val resId = context.resources.getIdentifier(name, "drawable", context.packageName)
        return if (resId != 0) resId else null
    }
}
