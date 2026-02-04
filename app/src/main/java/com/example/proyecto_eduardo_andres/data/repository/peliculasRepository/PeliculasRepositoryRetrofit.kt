package com.example.proyecto_eduardo_andres.data.repository.peliculasRepository

import android.content.Context
import com.example.proyecto_eduardo_andres.R
import com.example.proyecto_eduardo_andres.modelo.VideoClubOnlinePeliculasData
import com.example.proyecto_eduardo_andres.remote.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PeliculasRepositoryRetrofit(private val context: Context) : IPeliculasRepository {
    private val api = RetrofitClient.peliApiService

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
                        val imagenRes = resolveDrawableResource(dto.imagen)
                        val nombreRes = resolveStringResource(dto.nombre)
                        val categoriaRes = resolveStringResource(dto.categoria)
                        val descripcionRes = resolveStringResource(dto.descripcion)

                        VideoClubOnlinePeliculasData(
                            id = dto.id,
                            nombre = nombreRes,
                            categoria = categoriaRes,
                            imagen = imagenRes,
                            descripcion = descripcionRes
                        )
                    }
                    withContext(Dispatchers.Main) { onSuccess(mapped) }
                } else {
                    withContext(Dispatchers.Main) {
                        onError(Throwable("No se pudo obtener las películas desde el servidor (HTTP ${response.code()} ${response.message()})."))
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) { onError(Throwable("No se pudo obtener las películas desde el servidor: ${e.message}")) }
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
