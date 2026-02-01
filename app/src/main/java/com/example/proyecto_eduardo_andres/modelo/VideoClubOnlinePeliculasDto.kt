package com.example.proyecto_eduardo_andres.modelo

import com.example.proyecto_eduardo_andres.data.repository.mediaItemRepository.MediaItemData

data class VideoClubOnlinePeliculasData(
    val id: Int,
    override val nombre: Int,
    override val categoria: Int,
    override val imagen: Int?,
    val descripcion: Int
) :MediaItemData


fun pelicula(id: Int, categoria: Int, imagen: Int?, nombre: Int, descripcion: Int) =
    VideoClubOnlinePeliculasData(
        id = id,
        nombre = nombre,
        categoria = categoria,
        imagen = imagen,
        descripcion = descripcion
    )

