package com.example.proyecto_eduardo_andres.remote.api

import com.example.proyecto_eduardo_andres.modelo.AuthResponseDto
import com.example.proyecto_eduardo_andres.remote.dto.LoginDto
import com.example.proyecto_eduardo_andres.remote.dto.RegisterDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {

    @POST("auth/login")
    suspend fun login(
        @Body request: LoginDto
    ): Response<AuthResponseDto>

    @POST("auth/register")
    suspend fun register(
        @Body request: RegisterDto
    ): Response<AuthResponseDto>
}
