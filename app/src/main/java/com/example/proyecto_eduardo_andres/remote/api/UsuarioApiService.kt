package com.example.proyecto_eduardo_andres.remote.api

import com.example.proyecto_eduardo_andres.remote.dto.UsuarioDto
import retrofit2.Response
import retrofit2.http.GET

interface UsuarioApiService {

    @GET("json/users")
    suspend fun obtenerUsuarios(): Response<UsuarioDto>
}