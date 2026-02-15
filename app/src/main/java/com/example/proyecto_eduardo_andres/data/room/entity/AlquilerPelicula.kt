package com.example.proyecto_eduardo_andres.data.room.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "alquiler_peliculas",
    primaryKeys = ["userId", "peliculaId"]
)
data class AlquilerPelicula(
    val userId: String,
    val peliculaId: String,
    val estaAlquilada: Boolean,
    val fechaAlquiler: Long?,
    val fechaDevolucion: Long?
)

