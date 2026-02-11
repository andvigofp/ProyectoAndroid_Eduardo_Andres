package com.example.proyecto_eduardo_andres.data.room.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "alquiler_peliculas",
    foreignKeys = [
        ForeignKey(
            entity = Pelicula::class,
            parentColumns = ["id"],
            childColumns = ["peliculaId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class AlquilerPelicula(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val userId: String,
    val peliculaId: String,
    val estaAlquilada: Boolean,
    val fechaAlquiler: Date?,
    val fechaDevolucion: Date?
)

