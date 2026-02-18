package com.example.proyecto_eduardo_andres.remote.dto

import com.google.gson.annotations.SerializedName

/**
 * @author Andrés
 * @see AlquilerPeliculaDto
 *
 * @param DTO para obtener alquileres de películas.
 *
 * @param id Identificador único de la película.
 * @param imagen URL de la imagen de la película.
 * @param nombre Nombre de la película.
 * @param descripcion Descripción de la película.
 *
 */
data class AlquilerPeliculaDto(
    @SerializedName("id")
    val id: String,

    @SerializedName("imagen")
    val imagen: String,

    @SerializedName("nombre")
    val nombre: String,

    @SerializedName("descripcion")
    val descripcion: String,
)
