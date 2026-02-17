package com.example.proyecto_eduardo_andres.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *
 * Entidad que representa un usuario dentro de la base de datos Room.
 *
 * Esta clase modela la información necesaria para:
 * - Registro de usuario
 * - Autenticación (login)
 * - Gestión de sesión persistente ("Recordar sesión")
 *
 * @author Andrés
 */
@Entity(tableName = "user")
data class User(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "passwd") val passwd: String,
    @ColumnInfo(name = "keep_logged") val keepLogged: Boolean
)
