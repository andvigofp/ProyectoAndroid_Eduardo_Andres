package com.example.proyecto_eduardo_andres.remote.dto

import com.google.gson.annotations.SerializedName

/**
 * @author Eduardo
 * @see SerieDto
 *
 * @param DTO para obtener series.
 * @param id Identificador único de la serie.
 * @param categoria Categoría de la serie.
 * @param imagen URL de la imagen de la serie.
 * @param nombre Nombre de la serie.
 * @param descripcion Descripción de la serie.
 *
 */
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
