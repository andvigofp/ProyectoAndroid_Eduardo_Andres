package com.example.proyecto_eduardo_andres.viewData.buttonsData

enum class ButtonType { PRIMARY, SECONDARY, DANGER, OUTLINE }

data class ButtonData(
    val nombre: Int,              // <-- aquí pones el ID del string (R.string.alquilar)
    val type: ButtonType = ButtonType.PRIMARY,
    val enabled: Boolean = true
)
