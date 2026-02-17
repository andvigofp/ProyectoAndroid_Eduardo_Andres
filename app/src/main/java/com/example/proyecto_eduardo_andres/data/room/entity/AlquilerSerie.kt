package com.example.proyecto_eduardo_andres.data.room.entity

import androidx.room.Entity

/**
 *
 * Entidad que representa la relación de alquiler entre un usuario
 * y una serie dentro de la base de datos Room.
 *
 * Esta tabla modela una relación muchos-a-muchos entre User y Serie,
 * utilizando una clave primaria compuesta (userId + serieId).
 *
 * Permite gestionar:
 * - El estado actual del alquiler
 * - La fecha en la que se realiza el alquiler
 * - La fecha de devolución prevista o efectiva
 *
 * @author Andrés
 */
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

