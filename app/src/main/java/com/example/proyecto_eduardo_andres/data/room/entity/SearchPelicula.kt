package com.example.proyecto_eduardo_andres.data.room.entity

import android.content.Context
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.proyecto_eduardo_andres.R
import com.example.proyecto_eduardo_andres.modelo.VideoClubOnlinePeliculasData

/**
 *
 * Entidad que representa una película obtenida desde una búsqueda
 * y almacenada en la base de datos Room.
 *
 * Esta tabla ("search_peliculas") se utiliza para guardar
 * resultados temporales de búsqueda, normalmente obtenidos
 * desde una API externa.
 *
 * A diferencia de la entidad Pelicula, esta versión no incluye
 * descripción completa, ya que suele emplearse para listados
 * rápidos en la interfaz.
 *
 * @author Andrés
 */
@Entity(tableName = "search_peliculas")
data class SearchPelicula(
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "nombre") val nombre: String,
    @ColumnInfo(name = "categoria") val categoria: String,
    @ColumnInfo(name = "imagen") val imagen: String
) {

    /**
     * Convierte la entidad SearchPelicula en un modelo
     * de interfaz (VideoClubOnlinePeliculasData)
     * listo para ser utilizado en componentes Compose.
     *
     * @param context Contexto necesario para resolver recursos
     * string y drawable dinámicamente.
     *
     * @return Objeto VideoClubOnlinePeliculasData con recursos
     * convertidos en identificadores válidos.
     *
     * La descripción se establece como 0 porque los resultados
     * de búsqueda no incluyen descripción detallada.
     */
    fun toUiModel(context: Context): VideoClubOnlinePeliculasData {
        return VideoClubOnlinePeliculasData(
            id = id,
            nombre = resolveStringResource(context, nombre),
            categoria = resolveStringResource(context, categoria),
            imagen = resolveDrawableResource(context, imagen),
            descripcion = 0
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
