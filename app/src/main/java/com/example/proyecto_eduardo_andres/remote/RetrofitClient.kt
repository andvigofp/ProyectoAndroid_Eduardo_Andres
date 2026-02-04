package com.example.proyecto_eduardo_andres.remote

import com.example.proyecto_eduardo_andres.remote.api.AuthApiService
import com.example.proyecto_eduardo_andres.remote.api.PeliApiService
import com.example.proyecto_eduardo_andres.remote.api.RecuperarPasswordApiService
import com.example.proyecto_eduardo_andres.remote.api.SerieApiService
import com.example.proyecto_eduardo_andres.remote.api.UsuarioApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private const val BASE_URL = "http://10.0.2.2:5131"

    // Use lazy without synchronization to avoid possible contention during startup
    private val okHttpClient: OkHttpClient by lazy(LazyThreadSafetyMode.NONE) {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    private val retrofit: Retrofit by lazy(LazyThreadSafetyMode.NONE) {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val usuarioApiService: UsuarioApiService by lazy(LazyThreadSafetyMode.NONE) { retrofit.create(UsuarioApiService::class.java) }
    val authApiService: AuthApiService by lazy(LazyThreadSafetyMode.NONE) { retrofit.create(AuthApiService::class.java) }
    val serieApiService: SerieApiService by lazy(LazyThreadSafetyMode.NONE) { retrofit.create(SerieApiService::class.java) }
    val peliApiService: PeliApiService by lazy(LazyThreadSafetyMode.NONE) { retrofit.create(PeliApiService::class.java) }
    val recuperarPasswordApiExterna: RecuperarPasswordApiService by lazy(LazyThreadSafetyMode.NONE) { retrofit.create(RecuperarPasswordApiService::class.java) }
}
