package com.example.proyecto_eduardo_andres.modelo

import androidx.annotation.StringRes

data class InfoProyectoDto(
    @StringRes val titulo: Int,
    @StringRes val descripcion: Int,
    val integrantes: List<ContactoDto>
)

data class ContactoDto(
    @StringRes val nombre: Int,
    @StringRes val email: Int
)
