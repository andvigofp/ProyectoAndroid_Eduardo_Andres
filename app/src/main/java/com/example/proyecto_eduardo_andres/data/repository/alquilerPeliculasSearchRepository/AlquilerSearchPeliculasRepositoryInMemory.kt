package com.example.proyecto_eduardo_andres.data.repository.alquilerPeliculasSearchRepository

import com.example.proyecto_eduardo_andres.modelo.PeliculasDto
import com.example.proyecto_eduardo_andres.modelo.VideoClubOnlinePeliculasData

// Implementación en memoria ligera para usar en Previews sin Context
class AlquilerSearchPeliculasRepositoryInMemory : IAlquilerSearchPeliculasRepository {
    override fun obtenerPeliculasSearch(
        onError: (Throwable) -> Unit,
        onSuccess: (List<VideoClubOnlinePeliculasData>) -> Unit
    ) {
        try {
            val peliculas = PeliculasDto().peliculas
            onSuccess(peliculas)
        } catch (e: Throwable) {
            onError(e)
        }
    }
}

