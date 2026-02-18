package com.example.proyecto_eduardo_andres.remote.dto

import com.google.gson.annotations.SerializedName

/**
 * @author Andrés
 * @see AlquilerSearchPeliculasDto
 *
 * @param DTO para obtener alquileres de películas.
 * @param id Identificador único de la película.
 * @param imagen URL de la imagen de la película.
 * @param nombre Nombre de la película.
 * @param categoria Categoría de la película
 */
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
