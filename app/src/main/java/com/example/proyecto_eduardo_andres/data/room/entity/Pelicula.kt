package com.example.proyecto_eduardo_andres.data.room.entity

import android.content.Context
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.proyecto_eduardo_andres.R
import com.example.proyecto_eduardo_andres.modelo.VideoClubOnlinePeliculasData

/**
 *
 *
 * Entidad que representa una película dentro de la base de datos Room.
 *
 * Esta clase modela la información básica de una película
 * almacenada en la tabla "peliculas".
 *
 * @author Andrés
 */
@Entity(tableName = "peliculas")
data class Pelicula(
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "nombre") val nombre: String,
    @ColumnInfo(name = "categoria") val categoria: String,
    @ColumnInfo(name = "imagen") val imagen: String,
    @ColumnInfo(name = "descripcion") val descripcion: String
) {

    /**
     * Convierte la entidad Pelicula en un modelo de interfaz
     * (VideoClubOnlinePeliculasData) listo para ser utilizado
     * en componentes Compose.
     *
     * @param context Contexto necesario para resolver recursos
     * string y drawable dinámicamente.
     *
     * @return Objeto VideoClubOnlinePeliculasData con recursos
     * ya transformados en identificadores válidos.
     */
    fun toUiModel(context: Context): VideoClubOnlinePeliculasData {
        return VideoClubOnlinePeliculasData(
            id = id,
            nombre = resolveStringResource(context, nombre),
            categoria = resolveStringResource(context, categoria),
            imagen = resolveDrawableResource(context, imagen),
            descripcion = resolveStringResource(context, descripcion)
        )
    }

    /**
     * Resuelve dinámicamente un recurso string a partir
     * de su nombre almacenado como texto.
     *
     * @param context Contexto de la aplicación.
     * @param value Cadena que contiene la referencia
     * al recurso (ej: "R.string.nombre").
     *
     * @return Identificador del recurso string si existe;
     * en caso contrario, devuelve R.string.app_name
     * como valor por defecto.
     */
    private fun resolveStringResource(context: Context, value: String): Int {
        if (value.isBlank()) return R.string.app_name
        val name = value.substringAfter("R.string.").substringAfterLast('.')
        val resId = context.resources.getIdentifier(name, "string", context.packageName)
        return if (resId != 0) resId else R.string.app_name
    }

    private fun resolveDrawableResource(context: Context, value: String): Int? {
        if (value.isBlank()) return null
        val name = value.substringAfter("R.drawable.").substringAfterLast('.')
        val resId = context.resources.getIdentifier(name, "drawable", context.packageName)
        return if (resId != 0) resId else null
    }
}