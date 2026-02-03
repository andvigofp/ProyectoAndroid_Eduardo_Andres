package com.example.proyecto_eduardo_andres.data.repository.seriesRepository
import android.content.Context
import com.example.proyecto_eduardo_andres.R
import com.example.proyecto_eduardo_andres.modelo.VideoClubOnlineSeriesData
import com.example.proyecto_eduardo_andres.remote.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
class SeriesRepositoryRetrofit(private val context: Context) : ISeriesRepository {
    private val api = RetrofitClient.serieApiService
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
                    withContext(Dispatchers.Main) { onError(Throwable("HTTP ${response.code()} ${response.message()}")) }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) { onError(e) }
            }
        }
    }
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
