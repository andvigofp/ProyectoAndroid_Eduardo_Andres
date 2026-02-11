package com.example.proyecto_eduardo_andres.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "series")
data class Serie(
    @PrimaryKey
    val id: String,
    val nombre: Int,
    val categoria: Int,
    val imagen: Int?,
    val descripcion: Int
)

