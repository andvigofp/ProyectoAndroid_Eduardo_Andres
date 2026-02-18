package com.example.proyecto_eduardo_andres.modelo

/**
 *
 * Data Transfer Object (DTO) que representa la respuesta
 * recibida desde el servidor tras una operación de autenticación.
 *
 * Esta clase se utiliza normalmente en la capa de red (API)
 * para mapear la respuesta JSON del backend.
 *
 * @author Andrés
 */
data class AuthResponseDto(
    val id: String,
    val name: String,
    val email: String,
    val message: String
)
