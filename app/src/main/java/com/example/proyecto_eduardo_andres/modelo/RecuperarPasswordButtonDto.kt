package com.example.proyecto_eduardo_andres.modelo

import androidx.annotation.StringRes

/**
 * @author Eduardo
 * @param cancelar Texto del botón de cancelar.
 * @param recuperarPassword Texto del botón de recuperar contraseña.
 */
data class RecuperarPasswordButtonDto(
    @StringRes val cancelar: Int,
    @StringRes val recuperarPassword: Int
)