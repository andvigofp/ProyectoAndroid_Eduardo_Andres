package com.example.proyecto_eduardo_andres.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.proyecto_eduardo_andres.data.room.entity.AlquilerSerie

@Dao
interface AlquilerSerieDao {

    @Query("SELECT * FROM alquiler_series WHERE userId = :userId")
    fun getByUserId(userId: String): List<AlquilerSerie>

    @Query("SELECT * FROM alquiler_series WHERE userId = :userId AND serieId = :serieId")
    fun getByUserAndSerie(userId: String, serieId: String): AlquilerSerie?

    @Query("SELECT * FROM alquiler_series WHERE userId = :userId AND estaAlquilada = 1")
    fun getSeriesAlquiladas(userId: String): List<AlquilerSerie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(alquiler: AlquilerSerie)

    @Update
    fun update(alquiler: AlquilerSerie)

    @Delete
    fun delete(alquiler: AlquilerSerie)

    @Query("DELETE FROM alquiler_series WHERE userId = :userId")
    fun deleteByUserId(userId: String)
}

