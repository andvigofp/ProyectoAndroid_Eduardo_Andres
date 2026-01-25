package com.example.proyecto_eduardo_andres.repository.peliculasRepository

import com.example.proyecto_eduardo_andres.viewData.listaPeliculasData.PeliculasData
import com.example.proyecto_eduardo_andres.viewData.listaPeliculasData.VideoClubOnlinePeliculasData

class PeliculasRepositoryInMemory : IPeliculasRepository {
    override fun obtenerPeliculas(
        onError: (Throwable) -> Unit,
        onSuccess: (List<VideoClubOnlinePeliculasData>) -> Unit
    ) {
        try {
            val peliculas = PeliculasData().peliculas
            onSuccess(peliculas)
        } catch (e: Throwable) {
            onError(e)
        }
    }
}

