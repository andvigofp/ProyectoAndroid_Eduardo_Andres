package com.example.proyecto_eduardo_andres.modelo

import androidx.annotation.StringRes

data class RecuperarPasswordButtonDto(
    @StringRes val cancelar: Int,
    @StringRes val recuperarPassword: Int
)