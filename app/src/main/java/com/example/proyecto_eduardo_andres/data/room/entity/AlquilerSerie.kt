package com.example.proyecto_eduardo_andres.data.room.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.Date
@Entity(
    tableName = "alquiler_series",
    primaryKeys = ["userId", "serieId"]
)
data class AlquilerSerie(
    val userId: String,
    val serieId: String,
    val estaAlquilada: Boolean,
    val fechaAlquiler: Long?,
    val fechaDevolucion: Long?
)

