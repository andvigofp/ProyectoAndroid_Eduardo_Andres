package com.example.proyecto_eduardo_andres.data.repository.alquilerPeliculasSearchRepository

import android.content.Context
import com.example.proyecto_eduardo_andres.data.room.dao.SearchPeliculaDao
import com.example.proyecto_eduardo_andres.data.room.entity.SearchPelicula
import com.example.proyecto_eduardo_andres.modelo.VideoClubOnlinePeliculasData
import com.example.proyecto_eduardo_andres.remote.api.AlquilerSearchPeliculasApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AlquilerSearchPeliculasRepositoryRoom(
    private val context: Context,
    private val api: AlquilerSearchPeliculasApiService,
    private val searchDao: SearchPeliculaDao
) : IAlquilerSearchPeliculasRepository {

    override fun obtenerPeliculasSearch(
        onError: (Throwable) -> Unit,
        onSuccess: (List<VideoClubOnlinePeliculasData>) -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {

            try {
                val response = api.obtenerPeliculasSearch()

                if (response.isSuccessful && response.body() != null) {

                    val entidades = response.body()!!.map { dto ->
                        SearchPelicula(
                            id = dto.id,
                            nombre = dto.nombre,
                            categoria = dto.categoria,
                            imagen = dto.imagen,
                        )
                    }

                    searchDao.deleteAll()
                    searchDao.insertAll(entidades)

                    val resultado = entidades.map { it.toUiModel(context) }

                    withContext(Dispatchers.Main) {
                        onSuccess(resultado)
                    }

                    return@launch
                }

            } catch (_: Exception) {
                // Si falla red → seguimos offline
            }

            // Fallback Room
            val local = searchDao.getAll()

            withContext(Dispatchers.Main) {
                if (local.isEmpty())
                    onError(Throwable("No hay resultados de búsqueda"))
                else
                    onSuccess(local.map { it.toUiModel(context) })
            }
        }
    }
}
