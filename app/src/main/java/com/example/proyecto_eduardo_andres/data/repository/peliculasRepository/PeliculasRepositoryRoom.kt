package com.example.proyecto_eduardo_andres.data.repository.peliculasRepository


import android.content.Context
import com.example.proyecto_eduardo_andres.data.room.dao.PeliculaDao
import com.example.proyecto_eduardo_andres.data.room.entity.Pelicula
import com.example.proyecto_eduardo_andres.modelo.VideoClubOnlinePeliculasData
import com.example.proyecto_eduardo_andres.remote.api.PeliApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PeliculasRepositoryRoom(
    private val context: Context,
    private val api: PeliApiService,
    private val peliculaDao: PeliculaDao
) : IPeliculasRepository {

    override fun obtenerPeliculas(
        onError: (Throwable) -> Unit,
        onSuccess: (List<VideoClubOnlinePeliculasData>) -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {

            try {
                // Intento remoto
                val response = api.obtenerPeliculas()

                if (response.isSuccessful && response.body() != null) {

                    val entidades = response.body()!!.map { dto ->
                        Pelicula(
                            id = dto.id,
                            nombre = dto.nombre,
                            categoria = dto.categoria,
                            imagen = dto.imagen,
                            descripcion = dto.descripcion
                        )
                    }

                    peliculaDao.deleteAll()
                    peliculaDao.insertAll(entidades)

                    val resultado = entidades.map { it.toUiModel(context) }

                    withContext(Dispatchers.Main) {
                        onSuccess(resultado)
                    }

                    return@launch
                }

            } catch (_: Exception) {
                // Si falla red → seguimos offline
            }

            // Local
            val local = peliculaDao.getAll()

            withContext(Dispatchers.Main) {
                if (local.isEmpty()) {
                    onError(Throwable("No hay películas disponibles"))
                } else {
                    onSuccess(local.map { it.toUiModel(context) })
                }
            }
        }
    }
}
