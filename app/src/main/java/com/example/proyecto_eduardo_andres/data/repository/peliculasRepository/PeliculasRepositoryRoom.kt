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

/**
 * Implementación híbrida (Retrofit + Room) del repositorio de películas.
 *
 * Esta clase intenta primero obtener el catálogo desde el servidor remoto.
 * Si la llamada es exitosa, guarda los datos en Room y devuelve el resultado.
 * Si falla la red, utiliza los datos almacenados localmente en la base de datos.
 *
 * @property context Contexto de la aplicación necesario para convertir
 * las entidades Room a modelos UI resolviendo recursos.
 * @property api Servicio Retrofit encargado de las llamadas remotas.
 * @property peliculaDao DAO de Room para acceder y persistir películas localmente.
 *
 * @author Andrés
 * @see IPeliculasRepository
 */
class PeliculasRepositoryRoom(
    private val context: Context,
    private val api: PeliApiService,
    private val peliculaDao: PeliculaDao
) : IPeliculasRepository {

    /**
     * Obtiene el catálogo de películas.
     *
     * Primero intenta obtener los datos desde el servidor remoto.
     * Si la respuesta es válida:
     *  - Borra los datos locales.
     *  - Guarda las nuevas entidades en Room.
     *  - Devuelve el resultado convertido a modelo UI.
     *
     * Si falla la red:
     *  - Recupera los datos almacenados localmente.
     *
     * @param onError Callback que se ejecuta si no hay datos disponibles
     * ni en remoto ni en local.
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

            // Fallback local (Room)
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