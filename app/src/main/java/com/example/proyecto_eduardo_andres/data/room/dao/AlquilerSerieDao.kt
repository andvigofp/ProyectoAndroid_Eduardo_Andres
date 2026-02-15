package com.example.proyecto_eduardo_andres.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.proyecto_eduardo_andres.data.room.entity.AlquilerSerie

@Dao
interface AlquilerSerieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(alquiler: AlquilerSerie)

    @Query("""
        SELECT * FROM alquiler_series 
        WHERE userId = :userId AND serieId = :serieId
        LIMIT 1
    """)
    suspend fun getEstado(userId: String, serieId: String): AlquilerSerie?

    @Query("""
        SELECT * FROM alquiler_series 
        WHERE userId = :userId AND estaAlquilada = 1
    """)
    suspend fun getSeriesAlquiladas(userId: String): List<AlquilerSerie>

    @Query("DELETE FROM alquiler_series")
    suspend fun deleteAll()
}

