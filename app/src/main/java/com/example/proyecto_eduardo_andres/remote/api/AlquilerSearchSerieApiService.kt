package com.example.proyecto_eduardo_andres.remote.api

import com.example.proyecto_eduardo_andres.remote.dto.AlquilerSearchSerieDto
import retrofit2.Response
import retrofit2.http.GET

interface AlquilerSearchSerieApiService {
    @GET("json/serie")
    suspend fun obtenerSeriesSearch(): Response<List<AlquilerSearchSerieDto>>
}