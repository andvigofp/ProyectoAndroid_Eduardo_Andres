package com.example.proyecto_eduardo_andres.remote.dto

import com.example.proyecto_eduardo_andres.data.room.entity.User
import com.google.gson.annotations.SerializedName

/**
 * @author Andrés
 * @see UsuarioDto
 *
 * @param DTO para obtener usuarios.
 * @param id Identificador único del usuario.
 * @param name Nombre del usuario.
 * @param email Email del usuario.
 * @param passwd Contraseña del usuario.
 * @param keepLogged Indica si el usuario desea mantener la sesión iniciada.
 */
data class UsuarioDto(
    @SerializedName("id")
    val id: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("email")
    val email: String,

    @SerializedName("passwd")
    val passwd: String,

    @SerializedName("keepLogged")
    val keepLogged: Boolean
) {
    fun toUser(): User {
        return User(
            id = id,
            name = name,
            email = email,
            passwd = passwd,
            keepLogged = keepLogged
        )
    }
}
