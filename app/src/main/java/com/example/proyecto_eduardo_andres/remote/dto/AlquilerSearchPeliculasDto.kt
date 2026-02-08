package com.example.proyecto_eduardo_andres.remote.dto

import com.google.gson.annotations.SerializedName

data class AlquilerSearchPeliculasDto(
    @SerializedName("id")
    val id: String,

    @SerializedName("imagen")
    val imagen: String,

    @SerializedName("nombre")
    val nombre: String,

    @SerializedName("categoria")
    val categoria: String,
)
