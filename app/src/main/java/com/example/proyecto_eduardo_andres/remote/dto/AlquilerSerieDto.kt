package com.example.proyecto_eduardo_andres.remote.dto

import com.google.gson.annotations.SerializedName

data class AlquilerSerieDto(
    @SerializedName("id")
    val id: String,

    @SerializedName("imagen")
    val imagen: String,

    @SerializedName("nombre")
    val nombre: String,

    @SerializedName("descripcion")
    val descripcion: String,
)
