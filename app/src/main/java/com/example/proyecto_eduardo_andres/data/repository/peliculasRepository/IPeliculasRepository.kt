package com.example.proyecto_eduardo_andres.data.repository.peliculasRepository

import com.example.proyecto_eduardo_andres.modelo.VideoClubOnlinePeliculasData

interface IPeliculasRepository {
    fun obtenerPeliculas(
        onError: (Throwable) -> Unit,
        onSuccess: (List<VideoClubOnlinePeliculasData>) -> Unit
    )
}



