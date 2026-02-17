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

/**
 *
 * Repositorio encargado de obtener las series desde la API remota
 * y almacenarlas en la base de datos local (Room).
 *
 * Si la llamada remota es exitosa:
 *  - Guarda los resultados en Room
 *  - Devuelve los datos mapeados a modelo UI
 *
 * Si la llamada falla:
 *  - Recupera los datos almacenados localmente
 *  - Devuelve los resultados offline
 *
 * @author Andrés
 * @see Implementación híbrida (Retrofit + Room) para búsqueda de series
 *
 * @param context Contexto necesario para convertir las entidades
 * en modelos UI utilizando recursos del sistema.
 * @param api Servicio Retrofit encargado de obtener las series
 * desde el endpoint remoto.
 * @param searchDao DAO de Room utilizado para almacenar y recuperar
 * las series de búsqueda en modo offline.
 */
class AlquilerSearchSeriesRepositoryRoom(
    private val context: Context,
    private val api: AlquilerSearchSerieApiService,
    private val searchDao: SearchSerieDao
) : IAlquilerSearchSeriesRepository {

    /**
     * Obtiene las series desde la API remota.
     * Si la red falla, utiliza los datos almacenados en Room.
     *
     * @param onError Callback ejecutado cuando no hay datos disponibles
     * (ni remotos ni locales) o ocurre un error crítico.
     * @param onSuccess Callback que devuelve la lista de series
     * convertidas a modelo de UI.
     */
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