package com.example.proyecto_eduardo_andres.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.proyecto_eduardo_andres.data.room.entity.AlquilerPelicula

@Dao
interface AlquilerPeliculaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(alquiler: AlquilerPelicula)

    @Query("""
        SELECT * FROM alquiler_peliculas 
        WHERE userId = :userId AND peliculaId = :peliculaId
        LIMIT 1
    """)
    suspend fun getEstado(userId: String, peliculaId: String): AlquilerPelicula?

    @Query("""
        SELECT * FROM alquiler_peliculas 
        WHERE userId = :userId AND estaAlquilada = 1
    """)
    suspend fun getPeliculasAlquiladas(userId: String): List<AlquilerPelicula>

    @Query("DELETE FROM alquiler_peliculas")
    suspend fun deleteAll()
}
