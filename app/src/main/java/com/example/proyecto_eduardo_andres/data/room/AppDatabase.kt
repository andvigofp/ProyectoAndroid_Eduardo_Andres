package com.example.proyecto_eduardo_andres.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import com.example.proyecto_eduardo_andres.data.room.entity.User
import androidx.room.RoomDatabase
import com.example.proyecto_eduardo_andres.data.room.dao.AlquilerPeliculaDao
import com.example.proyecto_eduardo_andres.data.room.dao.AlquilerSerieDao
import com.example.proyecto_eduardo_andres.data.room.dao.PeliculaDao
import com.example.proyecto_eduardo_andres.data.room.dao.SearchPeliculaDao
import com.example.proyecto_eduardo_andres.data.room.dao.SearchSerieDao
import com.example.proyecto_eduardo_andres.data.room.dao.SerieDao
import com.example.proyecto_eduardo_andres.data.room.dao.UserDao
import com.example.proyecto_eduardo_andres.data.room.entity.AlquilerPelicula
import com.example.proyecto_eduardo_andres.data.room.entity.AlquilerSerie
import com.example.proyecto_eduardo_andres.data.room.entity.Pelicula
import com.example.proyecto_eduardo_andres.data.room.entity.SearchPelicula
import com.example.proyecto_eduardo_andres.data.room.entity.SearchSerie
import com.example.proyecto_eduardo_andres.data.room.entity.Serie

/**
 * @author Andrés
 *
 * Clase principal que define la base de datos de la aplicación
 * utilizando Room como sistema de persistencia local.
 *
 * Esta clase centraliza todas las entidades y DAO del proyecto,
 * formando la capa de almacenamiento dentro de una arquitectura
 * moderna Android:
 *
 * Room (Base de datos) +
 * Repository +
 * ViewModel +
 * Jetpack Compose (Material 3).
 *
 * Configuración:
 * - version = 1 → Versión actual de la base de datos.
 * - exportSchema = false → No exporta el esquema a un archivo.
 */
@Database(
    entities = [
        User::class,
        Pelicula::class,
        Serie::class,
        SearchPelicula::class,
        SearchSerie::class,
        AlquilerPelicula::class,
        AlquilerSerie::class
    ],
    version = 1,
    exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun peliculaDao(): PeliculaDao
    abstract fun serieDao(): SerieDao
    abstract fun searchPeliculaDao(): SearchPeliculaDao
    abstract fun searchSerieDao(): SearchSerieDao
    abstract fun alquilerPeliculaDao(): AlquilerPeliculaDao
    abstract fun alquilerSerieDao(): AlquilerSerieDao

    /**
    * Instancia única de la base de datos.
    *
    * Se marca como @Volatile para garantizar que los cambios
    * sean visibles inmediatamente entre hilos.
    */
    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        /**
         * Devuelve la instancia única de la base de datos.
         *
         * Implementa el patrón Singleton para evitar
         * múltiples instancias de RoomDatabase.
         *
         * @param context Contexto de la aplicación.
         *
         * @return Instancia única de AppDatabase.
         */
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}