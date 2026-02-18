package com.example.proyecto_eduardo_andres.remote.api

import com.example.proyecto_eduardo_andres.remote.dto.AlquilerSearchSerieDto
import retrofit2.Response
import retrofit2.http.GET

/**
 * @author Eduardo
 * @see AlquilerSearchSerieApiService
 * @param Interfaz que define la API para obtener alquileres de series.
 *
 */
interface AlquilerSearchSerieApiService {
    @GET("json/serie")
    suspend fun obtenerSeriesSearch(): Response<List<AlquilerSearchSerieDto>>
}