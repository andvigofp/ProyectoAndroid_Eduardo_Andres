package com.example.proyecto_eduardo_andres.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.proyecto_eduardo_andres.data.room.entity.SearchSerie

@Dao
interface SearchSerieDao {
    @Query("SELECT * FROM search_series")
    suspend fun getAll(): List<SearchSerie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(series: List<SearchSerie>)

    @Query("DELETE FROM search_series")
    suspend fun deleteAll()
}