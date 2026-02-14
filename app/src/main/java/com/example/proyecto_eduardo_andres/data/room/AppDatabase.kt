package com.example.proyecto_eduardo_andres.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import com.example.proyecto_eduardo_andres.data.room.entity.User
import androidx.room.RoomDatabase
import com.example.proyecto_eduardo_andres.data.room.dao.PeliculaDao
import com.example.proyecto_eduardo_andres.data.room.dao.SerieDao
import com.example.proyecto_eduardo_andres.data.room.dao.UserDao
import com.example.proyecto_eduardo_andres.data.room.entity.Pelicula
import com.example.proyecto_eduardo_andres.data.room.entity.Serie

@Database(
    entities = [User::class, Pelicula::class, Serie::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun peliculaDao(): PeliculaDao
    abstract fun serieDao(): SerieDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .fallbackToDestructiveMigration() // SOLO desarrollo
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}
