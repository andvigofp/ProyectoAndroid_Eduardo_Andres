package com.example.proyecto_eduardo_andres.data.repository.alquilerSeriesSearchRepository

import android.content.Context
import com.example.proyecto_eduardo_andres.R
import com.example.proyecto_eduardo_andres.modelo.VideoClubOnlineSeriesData
import com.example.proyecto_eduardo_andres.remote.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 *
 * Repositorio encargado de obtener las series desde la API remota
 * utilizando Retrofit. Convierte los datos recibidos del backend
 * en modelos de UI (VideoClubOnlineSeriesData).
 *
 * @author Andrés
 * @see Implementación Retrofit para búsqueda de series
 *
 * @param context Contexto necesario para resolver dinámicamente
 * recursos String y Drawable a partir de los valores recibidos en la API.
 */
class AlquilerSearchSeriesRepositoryRetrofit(private val context: Context) : IAlquilerSearchSeriesRepository {

    private val api = RetrofitClient.alquilerSearchSeries

    /**
     * Obtiene la lista de series desde el servicio remoto de búsqueda.
     *
     * @param onError Callback que se ejecuta si ocurre un error
     * en la llamada HTTP o en el procesamiento de datos.
     * @param onSuccess Callback que devuelve la lista de series
     * correctamente mapeadas a modelos de UI.
     */
    override fun obtenerSeriesSearch(
        onError: (Throwable) -> Unit,
        onSuccess: (List<VideoClubOnlineSeriesData>) -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = api.obtenerSeriesSearch()
                if (response.isSuccessful) {
                    val body = response.body() ?: emptyList()
                    val mapped = body.map { dto ->
                        VideoClubOnlineSeriesData(
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
     * Resuelve un nombre de recurso String recibido desde la API
     * en su identificador correspondiente en R.string.
     *
     * @param value Nombre del recurso recibido desde backend.
     * @return ID del recurso String o un valor por defecto si no existe.
     */
    private fun resolveStringResource(value: String): Int {
        if (value.isBlank()) return R.string.app_name
        val name = value.substringAfter("R.string.").substringAfterLast('.')
        val resId = context.resources.getIdentifier(name, "string", context.packageName)
        return if (resId != 0) resId else R.string.app_name
    }

    /**
     * Resuelve un nombre de recurso Drawable recibido desde la API
     * en su identificador correspondiente en R.drawable.
     *
     * @param value Nombre del recurso recibido desde backend.
     * @return ID del recurso Drawable o null si no existe.
     */
    private fun resolveDrawableResource(value: String): Int? {
        if (value.isBlank()) return null
        val name = value.substringAfter("R.drawable.").substringAfterLast('.')
        val resId = context.resources.getIdentifier(name, "drawable", context.packageName)
        return if (resId != 0) resId else null
    }
}