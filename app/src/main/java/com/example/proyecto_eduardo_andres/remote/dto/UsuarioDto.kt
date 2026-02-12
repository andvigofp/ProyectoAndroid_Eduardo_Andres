package com.example.proyecto_eduardo_andres.remote.dto

import com.example.proyecto_eduardo_andres.data.room.entity.User
import com.google.gson.annotations.SerializedName

data class UsuarioDto(
    @SerializedName("id")
    val id: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("email")
    val email: String,

    @SerializedName("passwd")
    val passwd: String,
) {
    fun toUser(): User {
        return User(
            id = id,
            name = name,
            email = email,
            passwd = passwd
        )
    }
}
