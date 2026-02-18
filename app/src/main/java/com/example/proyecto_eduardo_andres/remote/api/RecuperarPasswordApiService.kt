package com.example.proyecto_eduardo_andres.remote.api

import com.example.proyecto_eduardo_andres.remote.dto.UsuarioDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

/**
 * @author Eduardo
 * @see RecuperarPasswordApiService
 * @param Interfaz que define la API para obtener usuarios.
 *
 */
interface RecuperarPasswordApiService {
    @GET("json/user")
    suspend fun obtenerUsuarios(): Response<List<UsuarioDto>>

    /**
     * @param id Identificador único del usuario.
     * @param usuario Datos actualizados del usuario, la password.
     */
    @POST("json/user/{id}")
    suspend fun actualizarUsuario(@Path("id") id: String, @Body usuario: UsuarioDto): Response<UsuarioDto>
}