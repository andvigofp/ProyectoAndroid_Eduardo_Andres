package com.example.proyecto_eduardo_andres.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.proyecto_eduardo_andres.data.room.dao.AlquilerPeliculaDao
import com.example.proyecto_eduardo_andres.data.room.dao.AlquilerSerieDao
import com.example.proyecto_eduardo_andres.data.room.dao.PeliculaDao
import com.example.proyecto_eduardo_andres.data.room.dao.SerieDao
import com.example.proyecto_eduardo_andres.data.room.dao.UserPreferencesDao
import com.example.proyecto_eduardo_andres.data.room.entity.AlquilerPelicula
import com.example.proyecto_eduardo_andres.data.room.entity.AlquilerSerie
import com.example.proyecto_eduardo_andres.data.room.entity.Pelicula
import com.example.proyecto_eduardo_andres.data.room.entity.Serie
import com.example.proyecto_eduardo_andres.data.room.entity.User

@Database(
    entities = [
        User::class,
        Pelicula::class,
        Serie::class,
        AlquilerPelicula::class,
        AlquilerSerie::class
    ],
    version = 1
)
@TypeConverters(DateConverter::class)
abstract class AppDatabase(): RoomDatabase() {
    abstract fun userDao(): UserPreferencesDao
    abstract fun peliculaDao(): PeliculaDao
    abstract fun serieDao(): SerieDao
    abstract fun alquilerPeliculaDao(): AlquilerPeliculaDao
    abstract fun alquilerSerieDao(): AlquilerSerieDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: android.content.Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = androidx.room.Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app-database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}