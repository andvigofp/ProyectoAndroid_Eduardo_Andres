package com.example.proyecto_eduardo_andres.data.room.entity

import android.content.Context
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.proyecto_eduardo_andres.R
import com.example.proyecto_eduardo_andres.modelo.VideoClubOnlineSeriesData

/**
 *
 * Entidad que representa una serie dentro de la base de datos Room.
 *
 * Esta clase modela la información principal de una serie
 * almacenada en la tabla "series".
 *
 * @author Andrés
 */
@Entity(tableName = "series")
data class Serie(
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "nombre") val nombre: String,
    @ColumnInfo(name = "categoria") val categoria: String,
    @ColumnInfo(name = "imagen") val imagen: String,
    @ColumnInfo(name = "descripcion") val descripcion: String
) {

    /**
     * Convierte la entidad Serie en un modelo de interfaz
     * (VideoClubOnlineSeriesData) listo para su uso
     * en componentes Jetpack Compose.
     *
     * @param context Contexto necesario para resolver recursos
     * string y drawable dinámicamente.
     *
     * @return Objeto VideoClubOnlineSeriesData con recursos
     * convertidos en identificadores válidos.
     */
    fun toUiModel(context: Context): VideoClubOnlineSeriesData {
        return VideoClubOnlineSeriesData(
            id = id,
            nombre = resolveStringResource(context, nombre),
            categoria = resolveStringResource(context, categoria),
            imagen = resolveDrawableResource(context, imagen),
            descripcion = resolveStringResource(context, descripcion)
        )
    }

    /**
     * Resuelve dinámicamente un recurso string a partir
     * del nombre almacenado como texto.
     *
     * @param context Contexto de la aplicación.
     * @param value Cadena que contiene la referencia
     * al recurso (ej: "R.string.nombre").
     *
     * @return Identificador del recurso string si existe;
     * en caso contrario devuelve R.string.app_name
     * como valor por defecto.
     */
    private fun resolveStringResource(context: Context, value: String): Int {
        if (value.isBlank()) return R.string.app_name
        val name = value.substringAfter("R.string.").substringAfterLast('.')
        val resId = context.resources.getIdentifier(name, "string", context.packageName)
        return if (resId != 0) resId else R.string.app_name
    }

    /**
     * Resuelve dinámicamente un recurso drawable a partir
     * del nombre almacenado como texto.
     *
     * @param context Contexto de la aplicación.
     * @param value Cadena que contiene la referencia
     * al recurso (ej: "R.drawable.imagen").
     *
     * @return Identificador del drawable si existe;
     * devuelve null si no se encuentra el recurso.
     */
    private fun resolveDrawableResource(context: Context, value: String): Int? {
        if (value.isBlank()) return null
        val name = value.substringAfter("R.drawable.").substringAfterLast('.')
        val resId = context.resources.getIdentifier(name, "drawable", context.packageName)
        return if (resId != 0) resId else null
    }
}