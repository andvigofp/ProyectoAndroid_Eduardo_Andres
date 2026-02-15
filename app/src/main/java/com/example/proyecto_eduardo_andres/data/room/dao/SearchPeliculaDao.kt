package com.example.proyecto_eduardo_andres.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.proyecto_eduardo_andres.data.room.entity.SearchPelicula

@Dao
interface SearchPeliculaDao {

    @Query("SELECT * FROM search_peliculas")
    suspend fun getAll(): List<SearchPelicula>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(peliculas: List<SearchPelicula>)

    @Query("DELETE FROM search_peliculas")
    suspend fun deleteAll()
}
