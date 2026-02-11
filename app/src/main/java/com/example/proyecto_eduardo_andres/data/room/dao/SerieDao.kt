package com.example.proyecto_eduardo_andres.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.proyecto_eduardo_andres.data.room.entity.Serie

@Dao
interface SerieDao {

    @Query("SELECT * FROM series")
    fun getAll(): List<Serie>

    @Query("SELECT * FROM series WHERE id = :id")
    fun getById(id: String): Serie?

    @Query("SELECT * FROM series WHERE categoria = :categoria")
    fun getByCategoria(categoria: Int): List<Serie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(serie: Serie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(series: List<Serie>)

    @Update
    fun update(serie: Serie)

    @Delete
    fun delete(serie: Serie)

    @Query("DELETE FROM series")
    fun deleteAll()
}

