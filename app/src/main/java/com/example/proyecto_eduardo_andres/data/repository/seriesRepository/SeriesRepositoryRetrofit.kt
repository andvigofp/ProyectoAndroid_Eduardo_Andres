package com.example.proyecto_eduardo_andres.data.repository.seriesRepository
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
 * Implementación del repositorio de series utilizando Retrofit.
 *
 * Esta clase obtiene las series desde el servidor remoto y convierte
 * los valores recibidos (String con referencia a recursos) en recursos
 * locales de Android (R.string y R.drawable).
 *
 * @author Eduardo
 * @param context Contexto necesario para resolver recursos dinámicamente.
 */
class SeriesRepositoryRetrofit(private val context: Context) : ISeriesRepository {

    private val api = RetrofitClient.serieApiService

    /**
     * Obtiene la lista de series desde el servidor remoto.
     *
     * @param onError Callback que se ejecuta si ocurre un error HTTP o de red.
     * @param onSuccess Callback que devuelve la lista de series mapeadas correctamente.
     */
    override fun obtenerSeries(onError: (Throwable) -> Unit, onSuccess: (List<VideoClubOnlineSeriesData>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = api.obtenerSeries()
                if (response.isSuccessful) {
                    val body = response.body() ?: emptyList()
                    val mapped = body.map { dto ->
                        val imagenRes = resolveDrawableResource(dto.imagen)
                        val nombreRes = resolveStringResource(dto.nombre)
                        val categoriaRes = resolveStringResource(dto.categoria)
                        val descripcionRes = resolveStringResource(dto.descripcion)
                        VideoClubOnlineSeriesData(
                            id = dto.id,
                            nombre = nombreRes,
                            categoria = categoriaRes,
                            imagen = imagenRes,
                            descripcion = descripcionRes
                        )
                    }
                    withContext(Dispatchers.Main) { onSuccess(mapped) }
                } else {
                    // Error HTTP: notificar que no se pudieron obtener las series
                    withContext(Dispatchers.Main) {
                        onError(Throwable("No se pudo obtener las series desde el servidor (HTTP ${response.code()} ${response.message()})."))
                    }
                }
            } catch (e: Exception) {
                // Excepción (ej. fallo de red): notificar el error
                withContext(Dispatchers.Main) { onError(Throwable("No se pudo obtener las series desde el servidor: ${e.message}")) }
            }
        }
    }

    /**
     * Convierte una referencia tipo "R.string.nombre" en un recurso real.
     *
     * @param value String recibido desde la API.
     * @return ID del recurso string correspondiente o app_name por defecto.
     */
    private fun resolveStringResource(value: String): Int {
        if (value.isBlank()) return R.string.app_name
        val name = value.substringAfter("R.string.").substringAfterLast('.')
        val resId = context.resources.getIdentifier(name, "string", context.packageName)
        return if (resId != 0) resId else R.string.app_name
    }

    /**
     * Convierte una referencia tipo "R.drawable.imagen" en un recurso drawable real.
     *
     * @param value String recibido desde la API.
     * @return ID del recurso drawable o null si no existe.
     */
    private fun resolveDrawableResource(value: String): Int? {
        if (value.isBlank()) return null
        val name = value.substringAfter("R.drawable.").substringAfterLast('.')
        val resId = context.resources.getIdentifier(name, "drawable", context.packageName)
        return if (resId != 0) resId else null
    }
}
