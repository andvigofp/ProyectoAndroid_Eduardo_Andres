package com.example.proyecto_eduardo_andres.remote.api

import com.example.proyecto_eduardo_andres.remote.dto.AlquilerSearchPeliculasDto
import retrofit2.Response
import retrofit2.http.GET

/**
 * @author Andrés
 * @see AlquilerSearchPeliculasApiService
 * @param Interfaz que define la API para obtener alquileres de películas.
 *
 */
interface AlquilerSearchPeliculasApiService {
    @GET("json/pelicula")
    suspend fun obtenerPeliculasSearch(): Response<List<AlquilerSearchPeliculasDto>>
}