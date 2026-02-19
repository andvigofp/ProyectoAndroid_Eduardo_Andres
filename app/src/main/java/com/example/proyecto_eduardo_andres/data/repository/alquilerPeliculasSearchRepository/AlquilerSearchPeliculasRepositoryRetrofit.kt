package com.example.proyecto_eduardo_andres.data.repository.alquilerPeliculasSearchRepository

import android.content.Context
import com.example.proyecto_eduardo_andres.R
import com.example.proyecto_eduardo_andres.modelo.VideoClubOnlinePeliculasData
import com.example.proyecto_eduardo_andres.remote.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Repositorio encargado de obtener las películas desde el servicio remoto
 * utilizando Retrofit.
 *
 * Esta implementación transforma los DTO recibidos desde la API
 * en modelos de dominio `VideoClubOnlinePeliculasData`,
 * resolviendo dinámicamente los recursos de tipo String y Drawable.
 *
 * @author Andrés
 * @see Implementación Retrofit para búsqueda de películas
 * @param context Contexto de la aplicación necesario para resolver
 * dinámicamente los recursos (strings y drawables).
 */
class AlquilerSearchPeliculasRepositoryRetrofit(private val context: Context) : IAlquilerSearchPeliculasRepository {

    private val api = RetrofitClient.alquilerSearchPeliculas

    /**
     * Obtiene las películas desde el servicio remoto de búsqueda.
     *
     * Realiza la llamada en un hilo IO y devuelve el resultado
     * en el hilo principal (Main).
     *
     * @param onError Callback que se ejecuta si ocurre un error
     * en la petición HTTP o en el procesamiento de datos.
     *
     * @param onSuccess Callback que devuelve la lista de películas
     * mapeadas a `VideoClubOnlinePeliculasData`.
     */
    override  fun obtenerPeliculasSearch(
        onError: (Throwable) -> Unit,
        onSuccess: (List<VideoClubOnlinePeliculasData>) -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = api.obtenerPeliculasSearch()
                if (response.isSuccessful) {
                    val body = response.body() ?: emptyList()
                    val mapped = body.map { dto ->
                        VideoClubOnlinePeliculasData(
                            id = dto.id,
                            nombre = resolveStringResource(dto.nombre),
                            imagen = resolveDrawableResource(dto.imagen),
                            descripcion = 0,
                            categoria = resolveStringResource(dto.categoria)
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
                    onError(Throwable("Error al obtener películas de búsqueda: ${e.message}"))
                }
            }
        }
    }

    /**
     * Resuelve dinámicamente un recurso String a partir de su nombre.
     *
     * @param Nombre del recurso en formato
     * "R.string.nombre_recurso".
     *
     * @return Id del recurso String correspondiente.
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
     * @param Nombre del recurso en formato
     * "R.drawable.nombre_imagen".
     *
     * @return Id del recurso Drawable o null si no existe.
     */
    private fun resolveDrawableResource(value: String): Int? {
        if (value.isBlank()) return null
        val name = value.substringAfter("R.drawable.").substringAfterLast('.')
        val resId = context.resources.getIdentifier(name, "drawable", context.packageName)
        return if (resId != 0) resId else null
    }
}