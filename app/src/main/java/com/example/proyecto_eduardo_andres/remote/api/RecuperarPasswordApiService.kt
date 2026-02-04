package com.example.proyecto_eduardo_andres.remote.api

import com.example.proyecto_eduardo_andres.remote.dto.UsuarioDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface RecuperarPasswordApiService {
    @GET("json/user")
    suspend fun obtenerUsuarios(): Response<List<UsuarioDto>>

    @POST("json/user/{id}")
    suspend fun actualizarUsuario(@Path("id") id: String, @Body usuario: UsuarioDto): Response<UsuarioDto>

    // Alternativa: POST sin id (algunos backends aceptan la actualización en la colección)
    @POST("json/user")
    suspend fun actualizarUsuarioPostSinId(@Body usuario: UsuarioDto): Response<UsuarioDto>

    // Alternativa: PUT para reemplazar la lista completa de usuarios (en backends que exponen /json/user como fichero)
    @PUT("json/user")
    suspend fun reemplazarUsuarios(@Body usuarios: List<UsuarioDto>): Response<List<UsuarioDto>>
}