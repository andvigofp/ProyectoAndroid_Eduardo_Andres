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

/**
 *
 * Repositorio encargado de obtener las películas desde el servicio remoto.
 * Si la llamada a la API falla, utiliza los datos almacenados localmente
 * en Room como mecanismo de respaldo (offline-first).
 *
 * Flujo de trabajo:
 * 1. Intenta obtener datos desde la API.
 * 2. Si tiene éxito → guarda en Room y devuelve datos actualizados.
 * 3. Si falla → obtiene datos almacenados en Room.
 *
 * @author Andrés
 * @see Implementación híbrida Retrofit + Room para búsqueda de películas
 * @param context Contexto necesario para transformar las entidades
 * de base de datos en modelos de UI mediante la función `toUiModel`.
 *
 * @param api Servicio Retrofit que realiza la llamada remota
 * para obtener las películas desde el servidor.
 *
 * @param searchDao DAO de Room encargado de acceder y persistir
 * las películas de búsqueda en la base de datos local.
 */
class AlquilerSearchPeliculasRepositoryRoom(
    private val context: Context,
    private val api: AlquilerSearchPeliculasApiService,
    private val searchDao: SearchPeliculaDao
) : IAlquilerSearchPeliculasRepository {

    /**
     * Obtiene las películas desde la API o desde Room si falla la red.
     *
     * @param onError Callback que se ejecuta si ocurre un error y
     * no existen datos disponibles en la base de datos local.
     *
     * @param onSuccess Callback que devuelve la lista de películas
     * transformadas a `VideoClubOnlinePeliculasData`.
     */
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

            // Fallback Room (modo offline)
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
