package com.example.proyecto_eduardo_andres.remote.api

import com.example.proyecto_eduardo_andres.remote.dto.PeliDto
import retrofit2.Response
import retrofit2.http.GET

/**
 * @author Eduardo
 * @see PeliApiService
 * @param Interfaz que define la API para obtener películas.
 *
 */
interface PeliApiService {
    @GET("json/pelicula")
    suspend fun obtenerPeliculas(): Response<List<PeliDto>>
}