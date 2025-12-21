package com.example.proyecto_eduardo_andres.repository.AlquilerPeliculasRepository

import com.example.proyecto_eduardo_andres.viewData.alquilerDevolverPeliculasData.VideoClubOnlineAlquilarPeliculasUiState

class AlquilerPeliculasRepositoryInMemory : IAlquilerPeliculasRepository {

    private val alquileres: MutableMap<Int, MutableList<VideoClubOnlineAlquilarPeliculasUiState>> = mutableMapOf()

    override fun alquilarPelicula(
        userId: Int,
        pelicula: VideoClubOnlineAlquilarPeliculasUiState,
        onError: (Throwable) -> Unit,
        onSuccess: () -> Unit
    ) {
        try {
            val peliculasUsuario = alquileres.getOrPut(userId) { mutableListOf() }
            peliculasUsuario.add(pelicula)
            onSuccess()
        } catch (e: Throwable) {
            onError(e)
        }
    }

    override fun devolverPelicula(
        userId: Int,
        pelicula: VideoClubOnlineAlquilarPeliculasUiState,
        onError: (Throwable) -> Unit,
        onSuccess: () -> Unit
    ) {
        try {
            val peliculasUsuario = alquileres[userId]
            peliculasUsuario?.remove(pelicula)
            onSuccess()
        } catch (e: Throwable) {
            onError(e)
        }
    }
}
