package com.example.proyecto_eduardo_andres.remote.dto

import com.google.gson.annotations.SerializedName

data class UsuarioDto(
    @SerializedName("id")
    val id: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("email")
    val email: String,

    @SerializedName("phone")
    val phone: String,

    //@SerializedName("address")
    //val address: AddressDto? = null,

    @SerializedName("username")
    val username: String? = null,

    @SerializedName("website")
    val website: String? = null
)
