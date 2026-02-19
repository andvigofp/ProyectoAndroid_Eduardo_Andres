package com.example.proyecto_eduardo_andres.modelo

import androidx.compose.ui.graphics.Color

/**
 * @author Eduardo
 * @param nombreCategoria Identificador de recurso string (R.string.*)
 * que representa el nombre de la categoría.
 *
 * @param color Color asociado visualmente a la categoría..
 */
data class VideoClubOnlineCategoriasDto(
    val nombreCategoria: Int,
    val color: Color
)