package com.example.proyecto_eduardo_andres.repository.alquilerPeliculasRepository


import com.example.proyecto_eduardo_andres.viewData.listaPeliculasData.VideoClubOnlinePeliculasData

class AlquilerPeliculasRepositoryInMemory : IAlquilerPeliculasRepository {

    private val alquileres =
        mutableMapOf<Int, MutableList<VideoClubOnlinePeliculasData>>()


    override fun alquilarPelicula(
        userId: Int,
        pelicula: VideoClubOnlinePeliculasData,
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
        pelicula: VideoClubOnlinePeliculasData,
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
