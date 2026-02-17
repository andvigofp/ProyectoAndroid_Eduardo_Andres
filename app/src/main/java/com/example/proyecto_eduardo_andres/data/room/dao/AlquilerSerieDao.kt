package com.example.proyecto_eduardo_andres.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.proyecto_eduardo_andres.data.room.entity.AlquilerSerie

/**
 *
 *
 * DAO (Data Access Object) para la gestión de alquileres de series en Room.
 *
 * Esta interfaz define las operaciones de base de datos relacionadas con:
 * - Insertar o actualizar alquileres de series
 * - Consultar el estado de alquiler de una serie por usuario
 * - Obtener todas las series alquiladas por un usuario
 * - Eliminar todos los registros almacenados
 *
 * @author Andrés
 */
@Dao
interface AlquilerSerieDao {

    /**
     * Inserta o actualiza un registro de alquiler de serie.
     *
     * @param alquiler Entidad AlquilerSerie que contiene:
     *                 - userId
     *                 - serieId
     *                 - estado de alquiler
     *                 - fecha de alquiler
     *                 - fecha de devolución
     *
     * Si el registro ya existe, se reemplaza automáticamente
     * gracias a OnConflictStrategy.REPLACE.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(alquiler: AlquilerSerie)

    /**
     * Obtiene el estado de alquiler de una serie concreta para un usuario.
     *
     * @param userId Identificador único del usuario.
     * @param serieId Identificador único de la serie.
     *
     * @return La entidad AlquilerSerie si existe registro,
     *         o null si no existe alquiler asociado.
     */
    @Query("""
        SELECT * FROM alquiler_series 
        WHERE userId = :userId AND serieId = :serieId
        LIMIT 1
    """)
    suspend fun getEstado(userId: String, serieId: String): AlquilerSerie?

    /**
     * Obtiene todas las series que están actualmente alquiladas
     * por un usuario específico.
     *
     * @param userId Identificador único del usuario.
     *
     * @return Lista de entidades AlquilerSerie con estado activo.
     */
    @Query("""
        SELECT * FROM alquiler_series 
        WHERE userId = :userId AND estaAlquilada = 1
    """)
    suspend fun getSeriesAlquiladas(userId: String): List<AlquilerSerie>

    /**
     * Elimina todos los registros de alquiler de series.
     *
     * pruebas o reinicio de datos.
     */
    @Query("DELETE FROM alquiler_series")
    suspend fun deleteAll()
}

