package com.example.proyecto_eduardo_andres.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey val uid: String,
    @ColumnInfo(name = "name") val user: String?,
    @ColumnInfo(name = "email") val email: String?,
    @ColumnInfo(name = "keep_logged") val keepLogged: Boolean
)