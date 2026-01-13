package com.example.proyecto_eduardo_andres.network




import com.example.proyecto_eduardo_andres.modelo.UserDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface VideoClubApiService {
    @GET(value = "json/user/{id}")
    suspend fun getUser(@Path("id") id : String): Response<UserDTO>
}