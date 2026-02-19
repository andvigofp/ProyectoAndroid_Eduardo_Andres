package com.example.proyecto_eduardo_andres.data.repository.alquilerPeliculasSearchRepository

import com.example.proyecto_eduardo_andres.modelo.VideoClubOnlinePeliculasData

/**
 *
 * Interface que define las operaciones necesarias para
 * obtener el catálogo de películas destinado a la pantalla
 * de búsqueda.
 *
 * Permite desacoplar la fuente de datos (Retrofit, Room o híbrido)
 * de la lógica de presentación.
 *
 * @author Andrés
 * @see Búsqueda de películas en módulo Alquiler
 */
interface IAlquilerSearchPeliculasRepository {
    /**
     * Obtiene el listado completo de películas disponibles
     * para la funcionalidad de búsqueda.
     *
     * @param onError Callback que se ejecuta si ocurre un error
     * durante la obtención de datos (red o base de datos).
     *
     * @param onSuccess Callback que devuelve la lista de
     * películas formateadas para la capa UI.
     */
    fun obtenerPeliculasSearch(
        onError: (Throwable) -> Unit,
        onSuccess: (List<VideoClubOnlinePeliculasData>) -> Unit
    )
}

