package com.example.proyecto_eduardo_andres.remote.dto

import com.google.gson.annotations.SerializedName

/**
 * @author Andrés
 * @see LoginDto
 *
 * @param DTO para el inicio de sesión.
 *
 * @param email Email del usuario.
 * @param passwd Contraseña del usuario.
 */
data class LoginDto(
    @SerializedName("email")
    val email: String,

    @SerializedName("passwd")
    val passwd: String
)
