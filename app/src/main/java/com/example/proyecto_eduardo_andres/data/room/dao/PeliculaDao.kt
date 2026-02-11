package com.example.proyecto_eduardo_andres.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.proyecto_eduardo_andres.data.room.entity.Pelicula

@Dao
interface PeliculaDao {

    @Query("SELECT * FROM peliculas")
    fun getAll(): List<Pelicula>

    @Query("SELECT * FROM peliculas WHERE id = :id")
    fun getById(id: String): Pelicula?

    @Query("SELECT * FROM peliculas WHERE categoria = :categoria")
    fun getByCategoria(categoria: Int): List<Pelicula>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(pelicula: Pelicula)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(peliculas: List<Pelicula>)

    @Update
    fun update(pelicula: Pelicula)

    @Delete
    fun delete(pelicula: Pelicula)

    @Query("DELETE FROM peliculas")
    fun deleteAll()
}

