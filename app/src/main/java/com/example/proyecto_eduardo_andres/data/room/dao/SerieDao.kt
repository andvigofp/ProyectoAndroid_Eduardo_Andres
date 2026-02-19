package com.example.proyecto_eduardo_andres.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.proyecto_eduardo_andres.data.room.entity.Serie

/**
 *
 * DAO (Data Access Object) para la gestión de series
 * en la base de datos Room.
 *
 * Esta interfaz define las operaciones CRUD sobre la entidad Serie:
 * - Consultas completas
 * - Búsqueda por identificador
 * - Filtrado por categoría
 * - Inserciones individuales o múltiples
 * - Actualizaciones
 * - Eliminaciones
 *
 * Se utiliza dentro de una arquitectura moderna Android
 *  * (Room + Repository + ViewModel + Jetpack Compose),
 *  * garantizando persistencia eficiente y acceso asíncrono
 *  * mediante corrutinas.
 *
 * @author Andrés
 */
@Dao
interface SerieDao {

    /**
     * Obtiene todas las series almacenadas en la base de datos.
     *
     * @return Lista completa de entidades Serie.
     */
    @Query("SELECT * FROM series")
    fun getAll(): List<Serie>

    /**
     * Obtiene una serie concreta a partir de su identificador.
     *
     * @param id Identificador único de la serie.
     *
     * @return La entidad Serie correspondiente o null si no existe.
     */
    @Query("SELECT * FROM series WHERE id = :id")
    fun getById(id: String): Serie?

    /**
     * Obtiene todas las series que pertenecen a una categoría específica.
     *
     * @param categoria Identificador numérico de la categoría.
     *
     * @return Lista de series que coinciden con la categoría indicada.
     */
    @Query("SELECT * FROM peliculas WHERE categoria = :categoria")
    fun getByCategoria(categoria: Int): List<Serie>

    /**
     * Inserta una serie en la base de datos.
     *
     * @param serie Entidad Serie que se desea guardar.
     *
     * Si ya existe una serie con el mismo ID,
     * se reemplaza automáticamente gracias a
     * OnConflictStrategy.REPLACE.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(serie: Serie)


    /**
     * Inserta múltiples series en la base de datos.
     *
     * @param series Lista de entidades Serie a insertar.
     *
     * Si existen conflictos de clave primaria,
     * se reemplazan automáticamente.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(series: List<Serie>)


    /**
     * Actualiza una serie existente en la base de datos.
     *
     * @param serie Entidad Serie con los nuevos valores
     * que reemplazarán a los actuales.
     */
    @Update
    fun update(serie: Serie)


    /**
     * Elimina una serie específica de la base de datos.
     *
     * @param serie Entidad Serie que se desea eliminar.
     */
    @Delete
    fun delete(serie: Serie)

    /**
     * Elimina todas las series almacenadas en la base de datos.
     *
     */
    @Query("DELETE FROM series")
    fun deleteAll()
}

