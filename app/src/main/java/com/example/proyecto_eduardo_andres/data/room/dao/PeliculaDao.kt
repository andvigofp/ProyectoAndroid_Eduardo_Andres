package com.example.proyecto_eduardo_andres.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.proyecto_eduardo_andres.data.room.entity.Pelicula

/**
 *
 * DAO (Data Access Object) para la gestión de películas en la base de datos Room.
 *
 * Esta interfaz define todas las operaciones CRUD sobre la entidad Pelicula:
 * - Consultas completas
 * - Búsquedas por ID o categoría
 * - Inserciones individuales o masivas
 * - Actualizaciones
 * - Eliminaciones
 *
 * @author Andrés
 */
@Dao
interface PeliculaDao {

    /**
     * Obtiene todas las películas almacenadas en la base de datos.
     *
     * @return Lista completa de entidades Pelicula.
     */
    @Query("SELECT * FROM peliculas")
    fun getAll(): List<Pelicula>

    /**
     * Obtiene una película concreta a partir de su identificador.
     *
     * @param id Identificador único de la película.
     *
     * @return La entidad Pelicula correspondiente o null si no existe.
     */
    @Query("SELECT * FROM peliculas WHERE id = :id")
    fun getById(id: String): Pelicula?


    /**
     * Obtiene todas las películas que pertenecen a una categoría específica.
     *
     * @param categoria Nombre o identificador de la categoría.
     *
     * @return Lista de películas que coinciden con la categoría indicada.
     */
    @Query("SELECT * FROM peliculas WHERE categoria = :categoria")
    fun getByCategoria(categoria: String): List<Pelicula>

    /**
     * Inserta una película en la base de datos.
     *
     * @param pelicula Entidad Pelicula que se desea guardar.
     *
     * Si ya existe una película con el mismo ID,
     * se reemplaza automáticamente gracias a
     * OnConflictStrategy.REPLACE.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(pelicula: Pelicula)

    /**
     * Inserta múltiples películas en la base de datos.
     *
     * @param peliculas Lista de entidades Pelicula a insertar.
     *
     * Si existen conflictos de ID, se reemplazan automáticamente.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(peliculas: List<Pelicula>)

    /**
     * Actualiza una película existente en la base de datos.
     *
     * @param pelicula Entidad Pelicula con los nuevos valores.
     */
    @Update
    fun update(pelicula: Pelicula)

    /**
     * Elimina una película específica de la base de datos.
     *
     * @param pelicula Entidad Pelicula que se desea eliminar.
     */
    @Delete
    fun delete(pelicula: Pelicula)

    /**
     * Elimina todas las películas almacenadas en la base de datos.
     *
     * pruebas o reinicialización del catálogo.
     */
    @Query("DELETE FROM peliculas")
    fun deleteAll()
}

