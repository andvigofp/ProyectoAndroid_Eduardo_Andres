package com.example.proyecto_eduardo_andres.repository.alquilerPeliculasRepository

import com.example.proyecto_eduardo_andres.viewData.listaPeliculasData.VideoClubOnlinePeliculasData

// Interfaz del repositorio de películas
interface IAlquilerPeliculasRepository {
    fun alquilarPelicula(
        userId: Int,
        pelicula: VideoClubOnlinePeliculasData,
        onError: (Throwable) -> Unit,
        onSuccess: () -> Unit
    )

    fun devolverPelicula(
        userId: Int,
        pelicula: VideoClubOnlinePeliculasData,
        onError: (Throwable) -> Unit,
        onSuccess: () -> Unit
    )
}
