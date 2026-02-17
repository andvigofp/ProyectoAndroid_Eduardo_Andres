package com.example.proyecto_eduardo_andres.data.repository.peliculasRepository

import com.example.proyecto_eduardo_andres.modelo.VideoClubOnlinePeliculasData

/**
 *
 * Interfaz que define las operaciones necesarias para obtener
 * el catálogo completo de películas del sistema.
 *
 * @author Andrés
 * @see Repositorio de películas
 */
interface IPeliculasRepository {

    /**
     * Obtiene la lista completa de películas disponibles.
     *
     * @param onError Callback que se ejecuta si ocurre un error
     * durante la obtención de las películas.
     *
     * @param onSuccess Callback que devuelve la lista de
     * [VideoClubOnlinePeliculasData] cuando la operación
     * finaliza correctamente.
     */
    fun obtenerPeliculas(
        onError: (Throwable) -> Unit,
        onSuccess: (List<VideoClubOnlinePeliculasData>) -> Unit
    )
}



