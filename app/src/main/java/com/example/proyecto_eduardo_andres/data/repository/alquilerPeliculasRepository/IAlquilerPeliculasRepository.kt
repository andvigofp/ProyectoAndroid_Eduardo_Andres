package com.example.proyecto_eduardo_andres.data.repository.alquilerPeliculasRepository

import com.example.proyecto_eduardo_andres.modelo.EstadoAlquilerDto
import com.example.proyecto_eduardo_andres.modelo.VideoClubOnlinePeliculasData

interface IAlquilerPeliculasRepository {
    fun alquilarPelicula(
        userId: String,
        pelicula: VideoClubOnlinePeliculasData,
        onError: (Throwable) -> Unit,
        onSuccess: () -> Unit
    )

    fun devolverPelicula(
        userId: String,
        pelicula: VideoClubOnlinePeliculasData,
        onError: (Throwable) -> Unit,
        onSuccess: () -> Unit
    )

    fun obtenerEstadoAlquiler(
        userId: String,
        pelicula: VideoClubOnlinePeliculasData,
        onError: (Throwable) -> Unit,
        onSuccess: (EstadoAlquilerDto) -> Unit
    )

    fun obtenerPeliculasAlquiladas(
        userId: String,
        onError: (Throwable) -> Unit,
        onSuccess: (List<VideoClubOnlinePeliculasData>) -> Unit
    )
}
