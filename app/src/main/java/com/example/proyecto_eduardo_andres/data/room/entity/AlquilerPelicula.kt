package com.example.proyecto_eduardo_andres.data.room.entity

import androidx.room.Entity

/**
 * @author Andrés
 *
 * Entidad que representa la relación de alquiler entre un usuario
 * y una película dentro de la base de datos Room.
 *
 * Esta tabla modela una relación muchos-a-muchos entre User y Pelicula,
 * utilizando una clave primaria compuesta (userId + peliculaId).
 *
 * Se emplea para gestionar:
 * - Estado de alquiler de una película
 * - Fecha de inicio del alquiler
 * - Fecha prevista o real de devolución
 * @author Andrés
 */
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

