package com.example.proyecto_eduardo_andres.modelo

/**
 * @autor Andrés
 * @param id Identificador único del usuario.
 * @param name Nombre del usuario.
 * @param email Correo electrónico del usuario.
 * @param password Contraseña del usuario.
 * @param keepLogged Indica si el usuario desea mantener la sesión iniciada.
 */
data class UserDTO(
    val id: String?,
    val name: String,
    val email: String,
    val password: String,
    val keepLogged: Boolean,
) {

    /**
     * Convierte este DTO en una entidad User
     * compatible con la base de datos Room.
     *
     * @return Objeto User creado a partir
     * de los datos de este DTO.
     *
     * Se realiza:
     * - Conversión de password → passwd
     * - Asignación de id vacío si es null
     */
}
