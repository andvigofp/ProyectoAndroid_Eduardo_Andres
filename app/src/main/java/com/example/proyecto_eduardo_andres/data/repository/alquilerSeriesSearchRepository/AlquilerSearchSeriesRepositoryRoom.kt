package com.example.proyecto_eduardo_andres.data.repository.alquilerSeriesSearchRepository

import android.content.Context
import com.example.proyecto_eduardo_andres.data.repository.alquilerPeliculasSearchRepository.IAlquilerSearchPeliculasRepository
import com.example.proyecto_eduardo_andres.data.room.dao.SearchPeliculaDao
import com.example.proyecto_eduardo_andres.data.room.dao.SearchSerieDao
import com.example.proyecto_eduardo_andres.data.room.entity.SearchPelicula
import com.example.proyecto_eduardo_andres.data.room.entity.SearchSerie
import com.example.proyecto_eduardo_andres.modelo.VideoClubOnlinePeliculasData
import com.example.proyecto_eduardo_andres.modelo.VideoClubOnlineSeriesData
import com.example.proyecto_eduardo_andres.remote.api.AlquilerSearchPeliculasApiService
import com.example.proyecto_eduardo_andres.remote.api.AlquilerSearchSerieApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AlquilerSearchSeriesRepositoryRoom(
    private val context: Context,
    private val api: AlquilerSearchSerieApiService,
    private val searchDao: SearchSerieDao
) : IAlquilerSearchSeriesRepository {

    override fun obtenerSeriesSearch(
        onError: (Throwable) -> Unit,
        onSuccess: (List<VideoClubOnlineSeriesData>) -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {

            try {
                val response = api.obtenerSeriesSearch()

                if (response.isSuccessful && response.body() != null) {

                    val entidades = response.body()!!.map { dto ->
                        SearchSerie(
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
