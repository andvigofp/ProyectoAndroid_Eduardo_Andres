package com.example.proyecto_eduardo_andres.viewData.listaPeliculasData

import com.example.proyecto_eduardo_andres.viewData.mediaItemData.MediaItemData

data class VideoClubOnlinePeliculasData(
    val id: Int,
    override val nombre: Int,
    override val categoria: Int,
    override val imagen: Int?,
    val descripcion: Int
) : MediaItemData


fun pelicula(id: Int, categoria: Int, imagen: Int?, nombre: Int, descripcion: Int) =
    VideoClubOnlinePeliculasData(
        id = id,
        nombre = nombre,
        categoria = categoria,
        imagen = imagen,
        descripcion = descripcion
    )

