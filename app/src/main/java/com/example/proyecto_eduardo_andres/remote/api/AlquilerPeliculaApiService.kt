package com.example.proyecto_eduardo_andres.remote.api

import com.example.proyecto_eduardo_andres.remote.dto.AlquilerPeliculaDto
import com.example.proyecto_eduardo_andres.remote.dto.AlquilerSerieDto
import retrofit2.Response
import retrofit2.http.GET

interface AlquilerPeliculaApiService {
    @GET("json/pelicula")
    suspend fun obtenerPeliculas(): Response<List<AlquilerPeliculaDto>>
}