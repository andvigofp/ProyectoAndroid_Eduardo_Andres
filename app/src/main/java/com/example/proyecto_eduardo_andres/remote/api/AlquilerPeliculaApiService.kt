package com.example.proyecto_eduardo_andres.remote.api

import com.example.proyecto_eduardo_andres.remote.dto.AlquilerPeliculaDto
import retrofit2.Response
import retrofit2.http.GET

/**
 * @author Andrés
 * @see AlquilerPeliculaApiService
 * @param Interfaz que define la API para obtener alquileres de películas.
 *
 */
interface AlquilerPeliculaApiService {
    @GET("json/pelicula")
    suspend fun obtenerPeliculas(): Response<List<AlquilerPeliculaDto>>
}