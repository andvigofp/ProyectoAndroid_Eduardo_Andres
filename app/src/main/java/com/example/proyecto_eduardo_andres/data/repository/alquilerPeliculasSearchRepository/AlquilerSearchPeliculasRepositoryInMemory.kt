package com.example.proyecto_eduardo_andres.data.repository.alquilerPeliculasSearchRepository

import com.example.proyecto_eduardo_andres.modelo.PeliculasDto
import com.example.proyecto_eduardo_andres.modelo.VideoClubOnlinePeliculasData

/**
 * Implementación ligera del repositorio de búsqueda de películas.
 *
 * Se utiliza principalmente para:
 * - Previews de Jetpack Compose
 * - Entornos sin necesidad de Context
 * - Tests rápidos sin acceso a red o base de datos
 *
 *  @author Eduardo
 *  @see Implementación en memoria para búsqueda de películas
 */
class AlquilerSearchPeliculasRepositoryInMemory : IAlquilerSearchPeliculasRepository {

    /**
     * Obtiene el listado de películas en memoria.
     *
     * @param onError Callback que se ejecuta si ocurre
     * algún error inesperado durante la obtención.
     *
     * @param onSuccess Callback que devuelve la lista
     * completa de películas disponibles.
     */
    override fun obtenerPeliculasSearch(
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

