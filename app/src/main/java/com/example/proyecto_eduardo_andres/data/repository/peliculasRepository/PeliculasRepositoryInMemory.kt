package com.example.proyecto_eduardo_andres.data.repository.peliculasRepository

import com.example.proyecto_eduardo_andres.modelo.PeliculasDto
import com.example.proyecto_eduardo_andres.modelo.VideoClubOnlinePeliculasData

class PeliculasRepositoryInMemory :
    com.example.proyecto_eduardo_andres.data.repository.peliculasRepository.IPeliculasRepository {
    override fun obtenerPeliculas(
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



