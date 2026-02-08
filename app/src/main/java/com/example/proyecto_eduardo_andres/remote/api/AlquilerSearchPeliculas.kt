package com.example.proyecto_eduardo_andres.remote.api

import com.example.proyecto_eduardo_andres.remote.dto.AlquilerSearchPeliculasDto
import retrofit2.Response
import retrofit2.http.GET

interface AlquilerSearchPeliculas {
    @GET("json/pelicula")
    suspend fun obtenerPeliculasSearch(): Response<List<AlquilerSearchPeliculasDto>>
}