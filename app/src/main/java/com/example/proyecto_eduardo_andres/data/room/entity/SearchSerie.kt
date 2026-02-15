package com.example.proyecto_eduardo_andres.data.room.entity

import android.content.Context
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.proyecto_eduardo_andres.R
import com.example.proyecto_eduardo_andres.modelo.VideoClubOnlineSeriesData

@Entity(tableName = "search_series")
data class SearchSerie(
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "nombre") val nombre: String,
    @ColumnInfo(name = "categoria") val categoria: String,
    @ColumnInfo(name = "imagen") val imagen: String
) {
    fun toUiModel(context: Context): VideoClubOnlineSeriesData {
        return VideoClubOnlineSeriesData(
            id = id,
            nombre = resolveStringResource(context, nombre),
            categoria = resolveStringResource(context, categoria),
            imagen = resolveDrawableResource(context, imagen),
            descripcion = 0
        )
    }

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