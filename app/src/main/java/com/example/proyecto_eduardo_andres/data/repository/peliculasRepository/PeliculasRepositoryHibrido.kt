package com.example.proyecto_eduardo_andres.data.repository.peliculasRepository

import android.content.Context
import com.example.proyecto_eduardo_andres.R
import com.example.proyecto_eduardo_andres.data.room.dao.PeliculaDao
import com.example.proyecto_eduardo_andres.data.room.entity.Pelicula
import com.example.proyecto_eduardo_andres.modelo.VideoClubOnlinePeliculasData
import com.example.proyecto_eduardo_andres.remote.api.PeliApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PeliculasRepositoryHibrido(
    private val context: Context,
    private val api: PeliApiService,
    private val peliculaDao: PeliculaDao
) : IPeliculasRepository {

    override fun obtenerPeliculas(
        onError: (Throwable) -> Unit,
        onSuccess: (List<VideoClubOnlinePeliculasData>) -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {

            var peliculas: List<VideoClubOnlinePeliculasData>? = null

            try {
                // ==========================
                // 1️⃣ Intento REMOTO
                // ==========================
                val response = api.obtenerPeliculas()

                if (response.isSuccessful && response.body() != null) {

                    val body = response.body()!!

                    // Convertimos DTO → Room Entity
                    val entidades = body.map { dto ->
                        Pelicula(
                            id = dto.id,
                            nombre = dto.nombre,
                            categoria = dto.categoria,
                            imagen = dto.imagen,
                            descripcion = dto.descripcion
                        )
                    }

                    // Guardamos en Room
                    peliculaDao.deleteAll()
                    peliculaDao.insertAll(entidades)

                    // Convertimos a modelo UI
                    peliculas = entidades.map {
                        VideoClubOnlinePeliculasData(
                            id = it.id,
                            nombre = resolveStringResource(it.nombre),
                            categoria = resolveStringResource(it.categoria),
                            imagen = resolveDrawableResource(it.imagen),
                            descripcion = resolveStringResource(it.descripcion)
                        )
                    }
                }

            } catch (e: Exception) {
                // Si falla retrofit, seguimos con Room
            }

            // ==========================
            // LOCAL (Room)
            // ==========================
            if (peliculas == null) {

                val local = peliculaDao.getAll()

                peliculas = local.map {
                    VideoClubOnlinePeliculasData(
                        id = it.id,
                        nombre = resolveStringResource(it.nombre),
                        categoria = resolveStringResource(it.categoria),
                        imagen = resolveDrawableResource(it.imagen),
                        descripcion = resolveStringResource(it.descripcion)
                    )
                }
            }

            // ==========================
            // Resultado final
            // ==========================
            withContext(Dispatchers.Main) {
                if (peliculas.isNullOrEmpty())
                    onError(Throwable("No hay películas disponibles"))
                else
                    onSuccess(peliculas!!)
            }
        }
    }

    // =============================
    // Helpers igual que tu Retrofit
    // =============================

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
