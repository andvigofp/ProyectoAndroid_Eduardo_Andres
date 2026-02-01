package com.example.proyecto_eduardo_andres.modelo

enum class ButtonType { PRIMARY, SECONDARY, DANGER, OUTLINE }

data class ButtonData(
    val nombre: Int,
    val type: ButtonType = ButtonType.PRIMARY,
    val enabled: Boolean = true
)
