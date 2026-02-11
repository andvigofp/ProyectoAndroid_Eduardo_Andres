package com.example.proyecto_eduardo_andres.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.proyecto_eduardo_andres.data.room.entity.AlquilerPelicula

@Dao
interface AlquilerPeliculaDao {

    @Query("SELECT * FROM alquiler_peliculas WHERE userId = :userId")
    fun getByUserId(userId: String): List<AlquilerPelicula>

    @Query("SELECT * FROM alquiler_peliculas WHERE userId = :userId AND peliculaId = :peliculaId")
    fun getByUserAndPelicula(userId: String, peliculaId: String): AlquilerPelicula?

    @Query("SELECT * FROM alquiler_peliculas WHERE userId = :userId AND estaAlquilada = 1")
    fun getPeliculasAlquiladas(userId: String): List<AlquilerPelicula>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(alquiler: AlquilerPelicula)

    @Update
    fun update(alquiler: AlquilerPelicula)

    @Delete
    fun delete(alquiler: AlquilerPelicula)

    @Query("DELETE FROM alquiler_peliculas WHERE userId = :userId")
    fun deleteByUserId(userId: String)
}

