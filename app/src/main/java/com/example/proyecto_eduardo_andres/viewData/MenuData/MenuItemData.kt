package com.example.proyecto_eduardo_andres.viewData.MenuData

import androidx.compose.ui.graphics.vector.ImageVector

data class MenuItemData(
    val title: String,
    val icon: ImageVector,
    val onClick: () -> Unit
)