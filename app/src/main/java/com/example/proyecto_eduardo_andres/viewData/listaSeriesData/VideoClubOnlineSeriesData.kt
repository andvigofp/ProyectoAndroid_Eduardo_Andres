package com.example.proyecto_eduardo_andres.viewData.listaSeriesData

import com.example.proyecto_eduardo_andres.viewData.mediaItemData.MediaItemData

data class VideoClubOnlineSeriesData(
    override val nombre: Int,
    override val categoria: Int,
    override val imagen: Int?
) : MediaItemData

fun serie(categoria: Int, imagen: Int?, nombre: Int) =
    VideoClubOnlineSeriesData(
        nombre = nombre,
        categoria = categoria,
        imagen = imagen
    )