package com.example.proyecto_eduardo_andres.viewData.listaPeliculasData

import com.example.proyecto_eduardo_andres.viewData.mediaItemData.MediaItemData

data class VideoClubOnlinePeliculasData(
    override val nombre: Int,
    override val categoria: Int,
    override val imagen: Int?
) : MediaItemData

// Función de ayuda para no repetir VideoClubOnlinePeliculasData
fun pelicula(categoria: Int, imagen: Int?, nombre: Int) =
    VideoClubOnlinePeliculasData(
        nombre = nombre,
        categoria = categoria,
        imagen = imagen
    )

