package com.example.proyecto_eduardo_andres.modelo

import androidx.compose.ui.graphics.vector.ImageVector

/**
 * @author Eduardo
 * @param title Título del elemento del menú.
 * @param icon Ícono asociado al elemento del menú burguer.
 * @param onClick Función que se ejecuta al hacer clic en el elemento del menú.
 */
data class MenuItemDto(
    val title: String,
    val icon: ImageVector,
    val onClick: () -> Unit
)