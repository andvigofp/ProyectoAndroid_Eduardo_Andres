package com.example.proyecto_eduardo_andres.remote.dto

import com.google.gson.annotations.SerializedName

/**
 * @author Andrés
 * @see RegisterDto
 *
 * @param DTO para el registro de usuarios.
 *
 * @param name Nombre del usuario.
 * @param email Email del usuario.
 * @param passwd Contraseña del usuario.
 */
data class RegisterDto (
    @SerializedName("name")
    val name: String,

    @SerializedName("email")
    val email: String,

    @SerializedName("passwd")
    val passwd: String
)
