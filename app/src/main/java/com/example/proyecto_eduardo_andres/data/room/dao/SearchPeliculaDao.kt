package com.example.proyecto_eduardo_andres.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.proyecto_eduardo_andres.data.room.entity.SearchPelicula

/**
 *
 * DAO (Data Access Object) para la gestión de películas de búsqueda
 * en la base de datos Room.
 *
 * Esta interfaz define las operaciones necesarias para almacenar
 * y recuperar resultados de búsqueda de películas.
 *
 * Se utiliza normalmente para:
 * - Guardar resultados obtenidos desde una API
 * - Mostrar datos en interfaces desarrolladas con Jetpack Compose
 * - Mantener persistencia local en aplicaciones Android
 *
 * Forma parte de la arquitectura recomendada en Android Studio
 * utilizando Room como capa de persistencia.
 *
 * @author Andrés
 */
@Dao
interface SearchPeliculaDao {

    /**
     * Obtiene todas las películas almacenadas en la tabla search_peliculas.
     *
     * Esta consulta devuelve la lista completa de resultados
     * guardados previamente en la base de datos local.
     *
     * La función es suspend porque está pensada para ejecutarse
     * dentro de corrutinas (Kotlin Coroutines),
     * siguiendo las buenas prácticas de Android.
     *
     * @return Lista completa de entidades SearchPelicula.
     */
    @Query("SELECT * FROM search_peliculas")
    suspend fun getAll(): List<SearchPelicula>

    /**
     * Inserta múltiples películas en la tabla search_peliculas.
     *
     * @param peliculas Lista de entidades SearchPelicula
     * que se desean guardar en la base de datos.
     *
     * Si existe un conflicto de clave primaria,
     * se reemplaza automáticamente el registro anterior
     * gracias a OnConflictStrategy.REPLACE.
     *
     * La función es suspend porque realiza una operación
     * de escritura en base de datos.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(peliculas: List<SearchPelicula>)

    /**
     * Elimina todas las películas almacenadas en la tabla search_peliculas.
     *
     * Se utiliza habitualmente para:
     * - Limpiar resultados anteriores antes de guardar nuevos
     * - Reiniciar el estado de búsquedas
     * - Gestionar actualizaciones completas de datos
     *
     * La operación se ejecuta dentro de corrutinas
     * para evitar bloquear el hilo principal (Main Thread).
     */
    @Query("DELETE FROM search_peliculas")
    suspend fun deleteAll()
}
