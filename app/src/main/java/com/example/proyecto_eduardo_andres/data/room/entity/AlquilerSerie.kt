package com.example.proyecto_eduardo_andres.data.room.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "alquiler_series",
    foreignKeys = [
        ForeignKey(
            entity = Serie::class,
            parentColumns = ["id"],
            childColumns = ["serieId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class AlquilerSerie(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val userId: String,
    val serieId: String,
    val estaAlquilada: Boolean,
    val fechaAlquiler: Date?,
    val fechaDevolucion: Date?
)

