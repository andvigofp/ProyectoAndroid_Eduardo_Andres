package com.example.proyecto_eduardo_andres.remote.dto

import com.google.gson.annotations.SerializedName

data class RecuperarPasswordDto(
    @SerializedName("id")
    val id: String,

    @SerializedName("email")
    val email: String,

    @SerializedName("passwd")
    val passwd: String,
)
