package com.example.proyecto_eduardo_andres.data.repository.alquilerPeliculasSearchRepository

import android.content.Context
import com.example.proyecto_eduardo_andres.R
import com.example.proyecto_eduardo_andres.modelo.VideoClubOnlinePeliculasData
import com.example.proyecto_eduardo_andres.remote.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AlquilerSearchPeliculasRepository(private val context: Context) {

    private val api = RetrofitClient.alquilerSearchPeliculas

    // ----------------------------------------------------
    // Obtener películas desde la búsqueda
    // ----------------------------------------------------
    fun obtenerPeliculasSearch(
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