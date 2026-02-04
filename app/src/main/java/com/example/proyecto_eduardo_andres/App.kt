package com.example.proyecto_eduardo_andres

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        // Preinicializar clientes de red en background para evitar trabajo en hilo principal
        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Force lazy initialization of RetrofitClient singletons
                com.example.proyecto_eduardo_andres.remote.RetrofitClient.peliApiService
                com.example.proyecto_eduardo_andres.remote.RetrofitClient.serieApiService
                com.example.proyecto_eduardo_andres.remote.RetrofitClient.authApiService
                com.example.proyecto_eduardo_andres.remote.RetrofitClient.usuarioApiService
            } catch (e: Throwable) {
                // No propagamos la excepción; solo log si es necesario
                e.printStackTrace()
            }
        }
    }
}
