package com.example.proyecto_eduardo_andres.modelo


/**
 * @author Eduardo
 * @param represnta los campos del perfil del usuario.
 * @param nombreUsuaro Nombre del usuario.
 * @param email Email del usuario.
 * @param password Contraseña del usuario.
 */
data class PerfilUsuarioDto(
    val nombreUsuaro: String ="",
    val email: String = "",
    val password: String = "",
)