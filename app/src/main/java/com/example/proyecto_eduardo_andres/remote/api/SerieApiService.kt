package com.example.proyecto_eduardo_andres.remote.api

import com.example.proyecto_eduardo_andres.remote.dto.SerieDto
import retrofit2.Response
import retrofit2.http.GET

/**
 * @author Eduardo
 * @see SerieApiService
 * @param Interfaz que define la API para obtener las series.
 *
 */
interface SerieApiService {
    @GET("json/serie")
    suspend fun obtenerSeries(): Response<List<SerieDto>>
}