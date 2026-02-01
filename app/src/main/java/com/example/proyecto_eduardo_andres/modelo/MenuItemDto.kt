package com.example.proyecto_eduardo_andres.modelo

import androidx.compose.ui.graphics.vector.ImageVector

data class MenuItemDto(
    val title: String,
    val icon: ImageVector,
    val onClick: () -> Unit
)