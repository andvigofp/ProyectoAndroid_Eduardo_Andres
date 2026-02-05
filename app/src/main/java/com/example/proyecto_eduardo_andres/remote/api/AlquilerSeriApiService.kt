package com.example.proyecto_eduardo_andres.remote.api

import com.example.proyecto_eduardo_andres.remote.dto.SerieDto
import retrofit2.Response
import retrofit2.http.GET

interface AlquilerSeriApiService {
    @GET("json/serie")
    suspend fun obtenerSeries(): Response<List<SerieDto>>
}