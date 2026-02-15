package com.example.proyecto_eduardo_andres.viewmodel.ustate

import androidx.annotation.StringRes
import com.example.proyecto_eduardo_andres.R
import com.example.proyecto_eduardo_andres.modelo.ContactoDto

data class InfoProyectoUiState(
    @StringRes val titulo: Int = R.string.titulo_info_proyecto,
    @StringRes val descripcion: Int = R.string.descripcion_info_proyecto,
    val integrantes: List<ContactoDto> = emptyList()
)
