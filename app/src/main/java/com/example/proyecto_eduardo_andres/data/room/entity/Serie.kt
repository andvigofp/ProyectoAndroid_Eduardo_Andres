package com.example.proyecto_eduardo_andres.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "series")
data class Serie(
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "nombre") val nombre: String,
    @ColumnInfo(name = "categoria") val categoria: String,
    @ColumnInfo(name = "imagen") val imagen: String,
    @ColumnInfo(name = "descripcion") val descripcion: String
)

