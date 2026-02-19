package com.example.proyecto_eduardo_andres.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.proyecto_eduardo_andres.data.room.entity.AlquilerPelicula

/**
 *
 * DAO (Data Access Object) para la gestión de alquileres de películas en Room.
 *
 * Esta interfaz define las operaciones de base de datos relacionadas con:
 * - Insertar o actualizar alquileres
 * - Obtener el estado de alquiler de una película por usuario
 * - Obtener todas las películas alquiladas por un usuario
 * - Eliminar todos los registros de alquiler
 *
 * @author Andrés
 */
@Dao
interface AlquilerPeliculaDao {

    /**
     * Inserta o actualiza un registro de alquiler.
     *
     * @param alquiler Entidad AlquilerPelicula que contiene:
     *                 - userId
     *                 - peliculaId
     *                 - estado de alquiler
     *                 - fechas asociadas
     *
     * Si ya existe un registro con la misma clave primaria,
     * se reemplaza automáticamente.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(alquiler: AlquilerPelicula)

    /**
     * Obtiene el estado de alquiler de una película concreta para un usuario.
     *
     * @param userId Identificador único del usuario.
     * @param peliculaId Identificador único de la película.
     *
     * @return La entidad AlquilerPelicula si existe,
     * o null si no hay registro.
     */
    @Query("""
        SELECT * FROM alquiler_peliculas 
        WHERE userId = :userId AND peliculaId = :peliculaId
        LIMIT 1
    """)
    suspend fun getEstado(userId: String, peliculaId: String): AlquilerPelicula?


    /**
     * Obtiene todas las películas que actualmente están alquiladas
     * por un usuario específico.
     *
     * @param userId Identificador único del usuario.
     *
     * @return Lista de entidades AlquilerPelicula con estado activo.
     */
    @Query("""
        SELECT * FROM alquiler_peliculas 
        WHERE userId = :userId AND estaAlquilada = 1
    """)
    suspend fun getPeliculasAlquiladas(userId: String): List<AlquilerPelicula>

    /**
     * Elimina todos los registros de alquiler de la base de datos.
     *
     */
    @Query("DELETE FROM alquiler_peliculas")
    suspend fun deleteAll()
}