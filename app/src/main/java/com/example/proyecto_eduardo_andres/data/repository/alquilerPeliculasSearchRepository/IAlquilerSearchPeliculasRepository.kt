package com.example.proyecto_eduardo_andres.data.repository.alquilerPeliculasSearchRepository

import com.example.proyecto_eduardo_andres.modelo.VideoClubOnlinePeliculasData

interface IAlquilerSearchPeliculasRepository {
    fun obtenerPeliculasSearch(
        onError: (Throwable) -> Unit,
        onSuccess: (List<VideoClubOnlinePeliculasData>) -> Unit
    )
}

