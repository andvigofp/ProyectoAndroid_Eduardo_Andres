package com.example.proyecto_eduardo_andres.remote.dto

import com.google.gson.annotations.SerializedName

data class SerieDto(
    @SerializedName("id")
    val id: String,

    @SerializedName("categoria")
    val categoria: String,

    @SerializedName("imagen")
    val imagen: String,

    @SerializedName("nombre")
    val nombre: String,

    @SerializedName("descripcion")
    val descripcion: String,

)
