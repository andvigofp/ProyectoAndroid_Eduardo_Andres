package com.example.proyecto_eduardo_andres.data.repository.peliculasRepository

import com.example.proyecto_eduardo_andres.modelo.PeliculasDto
import com.example.proyecto_eduardo_andres.modelo.VideoClubOnlinePeliculasData

/**
 * Implementación en memoria del repositorio de películas.
 *
 * Esta clase devuelve un listado local de películas utilizando
 * la clase {PeliculasDto}. Se utiliza principalmente
 * para pruebas, previews o cuando no se requiere conexión
 * a red ni base de datos.
 *
 * @author Andrés
 * @see IPeliculasRepository
 */
class PeliculasRepositoryInMemory :
    IPeliculasRepository {

    /**
     * Obtiene la lista completa de películas almacenadas en memoria.
     *
     * @param onError Callback que se ejecuta si ocurre algún error
     * durante la obtención de datos.
     * @param onSuccess Callback que devuelve la lista de
     * {VideoClubOnlinePeliculasData cuando la operación es exitosa.
     */
    override fun obtenerPeliculas(
        onError: (Throwable) -> Unit,
        onSuccess: (List<VideoClubOnlinePeliculasData>) -> Unit
    ) {
        try {
            val peliculas = PeliculasDto().peliculas
            onSuccess(peliculas)
        } catch (e: Throwable) {
            onError(e)
        }
    }
}



