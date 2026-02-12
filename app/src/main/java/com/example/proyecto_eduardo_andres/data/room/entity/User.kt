package com.example.proyecto_eduardo_andres.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey val id: String,
    val name: String,
    val email: String,
    val passwd: String,
    @ColumnInfo(name = "keep_logged") val keepLogged: Boolean = false
)
