package com.example.proyecto_eduardo_andres.data.repository.alquilerPeliculasRepository

import com.example.proyecto_eduardo_andres.modelo.EstadoAlquilerDto
import com.example.proyecto_eduardo_andres.modelo.VideoClubOnlinePeliculasData

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

    fun obtenerEstadoAlquiler(
        userId: Int,
        pelicula: VideoClubOnlinePeliculasData,
        onError: (Throwable) -> Unit,
        onSuccess: (EstadoAlquilerDto) -> Unit
    )

    fun obtenerPeliculasAlquiladas(
        userId: Int,
        onError: (Throwable) -> Unit,
        onSuccess: (List<VideoClubOnlinePeliculasData>) -> Unit
    )
}
