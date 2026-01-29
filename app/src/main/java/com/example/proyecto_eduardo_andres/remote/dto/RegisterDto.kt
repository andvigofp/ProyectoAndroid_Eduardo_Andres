package com.example.proyecto_eduardo_andres.remote.dto

import com.google.gson.annotations.SerializedName

data class RegisterDto (
    @SerializedName("name")
    val name: String,

    @SerializedName("email")
    val email: String,

    @SerializedName("passwd")
    val passwd: String
)
