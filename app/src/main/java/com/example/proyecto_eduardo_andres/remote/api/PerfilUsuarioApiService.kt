package com.example.proyecto_eduardo_andres.remote.api


import com.example.proyecto_eduardo_andres.remote.dto.UsuarioDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PerfilUsuarioApiService {
    @GET("json/user")
    suspend fun obtenerPerfilUsuario(): Response<List<UsuarioDto>>

    @POST("json/user/{id}")
    suspend fun actualizarUsuario(@Path("id") id: String, @Body usuario: UsuarioDto): Response<UsuarioDto>
}