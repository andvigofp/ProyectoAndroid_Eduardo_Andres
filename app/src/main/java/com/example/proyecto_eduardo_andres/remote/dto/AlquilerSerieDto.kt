package com.example.proyecto_eduardo_andres.remote.dto

import com.google.gson.annotations.SerializedName

/**
 * @author Eduardo
 * @see AlquilerSerieDto
 *
 * @param DTO para obtener alquileres de series.
 *
 * @param id Identificador único de la serie.
 * @param imagen URL de la imagen de la serie.
 * @param nombre Nombre de la serie.
 *
 */
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
