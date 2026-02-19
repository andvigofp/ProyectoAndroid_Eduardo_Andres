package com.example.proyecto_eduardo_andres.data.repository.peliculasRepository

import android.content.Context
import com.example.proyecto_eduardo_andres.R
import com.example.proyecto_eduardo_andres.modelo.VideoClubOnlinePeliculasData
import com.example.proyecto_eduardo_andres.remote.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Implementación del repositorio de películas utilizando Retrofit.
 *
 * Esta clase obtiene el catálogo de películas desde un servicio remoto
 * mediante llamadas HTTP. Los datos recibidos se transforman a
 * {VideoClubOnlinePeliculasData} resolviendo los recursos
 * de tipo String y Drawable dinámicamente.
 *
 * @property context Contexto de la aplicación necesario para resolver
 * recursos dinámicamente.
 *
 * @author Andrés
 * @see IPeliculasRepository
 */
class PeliculasRepositoryRetrofit(private val context: Context) : IPeliculasRepository {
    private val api = RetrofitClient.peliApiService

    /**
     * Obtiene la lista completa de películas desde el servidor remoto.
     *
     * @param onError Callback que se ejecuta si ocurre un error de red,
     * excepción o respuesta HTTP no exitosa.
     * @param onSuccess Callback que devuelve la lista de
     * {VideoClubOnlinePeliculasData} cuando la operación finaliza correctamente.
     *
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

    /**
     * Resuelve dinámicamente un recurso String a partir de su nombre.
     *
     * @param value Nombre completo del recurso (ej. "R.string.nombre").
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
     * @param value Nombre completo del recurso (ej. "R.drawable.imagen").
     * @return ID del recurso Drawable correspondiente o null si no existe.
     */
    private fun resolveDrawableResource(value: String): Int? {
        if (value.isBlank()) return null
        val name = value.substringAfter("R.drawable.").substringAfterLast('.')
        val resId = context.resources.getIdentifier(name, "drawable", context.packageName)
        return if (resId != 0) resId else null
    }
}