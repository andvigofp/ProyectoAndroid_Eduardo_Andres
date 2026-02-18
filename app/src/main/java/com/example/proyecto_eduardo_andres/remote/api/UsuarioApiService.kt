package com.example.proyecto_eduardo_andres.remote.api

import com.example.proyecto_eduardo_andres.remote.dto.UsuarioDto
import retrofit2.Response
import retrofit2.http.GET

/**
 * @author Eduardo
 * @see UsuarioApiService
 * @param Interfaz que define la API para obtener usuarios.
 *
 */
interface UsuarioApiService {
    @GET("json/user")
    suspend fun obtenerUsuarios(): Response<List<UsuarioDto>>
}
