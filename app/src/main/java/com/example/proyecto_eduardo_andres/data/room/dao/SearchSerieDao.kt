package com.example.proyecto_eduardo_andres.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.proyecto_eduardo_andres.data.room.entity.SearchSerie

/**
 *
 * DAO (Data Access Object) para la gestión de resultados de búsqueda
 * de series en la base de datos Room.
 *
 * Esta interfaz define las operaciones necesarias para almacenar,
 * consultar y eliminar resultados de búsqueda de series en la
 * base de datos local de la aplicación.
 *
 * Se utiliza dentro de una arquitectura moderna Android
 * (Room + Repository + ViewModel + Jetpack Compose),
 * garantizando persistencia eficiente y acceso asíncrono
 * mediante corrutinas.
 *
 * @author Andrés
 */
@Dao
interface SearchSerieDao {

    /**
     * Obtiene todas las series almacenadas en la tabla search_series.
     *
     * Esta función recupera la lista completa de resultados
     * guardados previamente en la base de datos local.
     *
     * Se declara como suspend para ejecutarse dentro de una
     * corrutina y evitar bloquear el hilo principal (Main Thread),
     * siguiendo las buenas prácticas en Android Studio.
     *
     * @return Lista completa de entidades SearchSerie.
     */
    @Query("SELECT * FROM search_series")
    suspend fun getAll(): List<SearchSerie>

    /**
     * Inserta múltiples series en la tabla search_series.
     *
     * @param series Lista de entidades SearchSerie que se desean
     * almacenar en la base de datos.
     *
     * Si existe un conflicto de clave primaria,
     * se reemplaza automáticamente el registro anterior
     * gracias a OnConflictStrategy.REPLACE.
     *
     * Se ejecuta dentro de corrutinas debido a que
     * realiza una operación de escritura en base de datos.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(series: List<SearchSerie>)

    /**
     * Elimina todas las series almacenadas en la tabla search_series.
     *
     * Esta operación suele utilizarse para:
     * - Limpiar resultados de búsquedas anteriores.
     * - Actualizar completamente los datos mostrados en pantalla.
     * - Reinicializar el estado local antes de una nueva consulta.
     *
     * Se ejecuta como función suspend para evitar bloquear
     * el hilo principal de la aplicación.
     */
    @Query("DELETE FROM search_series")
    suspend fun deleteAll()
}