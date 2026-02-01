package com.example.proyecto_eduardo_andres.modelo

import com.example.proyecto_eduardo_andres.data.repository.mediaItemRepository.MediaItemData

data class VideoClubOnlineSeriesData(
    val id: Int,
    override val nombre: Int,
    override val categoria: Int,
    override val imagen: Int?,
    val descripcion: Int
) : MediaItemData


fun serie(id: Int, categoria: Int, imagen: Int?, nombre: Int, descripcion: Int) =
    VideoClubOnlineSeriesData(
        id = id,
        nombre = nombre,
        categoria = categoria,
        imagen = imagen,
        descripcion = descripcion
    )