package com.example.proyecto_eduardo_andres.remote.api


import com.example.proyecto_eduardo_andres.remote.dto.UsuarioDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * @author Andrés
 * @see PerfilUsuarioApiService
 * @param Interfaz que define la API para obtener el perfil del usuario.
 * */
interface PerfilUsuarioApiService {
    @GET("json/user")
    suspend fun obtenerPerfilUsuario(): Response<List<UsuarioDto>>

    /**
     * @param id Identificador único del usuario.
     * @param usuario Datos actualizados del usuario.
     */
    @POST("json/user/{id}")
    suspend fun actualizarUsuario(@Path("id") id: String, @Body usuario: UsuarioDto): Response<UsuarioDto>
}