package com.example.proyecto_eduardo_andres.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room

import com.example.proyecto_eduardo_andres.data.room.entity.User
import androidx.room.RoomDatabase
import com.example.proyecto_eduardo_andres.data.room.dao.AlquilerPeliculaDao
import com.example.proyecto_eduardo_andres.data.room.dao.AlquilerSerieDao
import com.example.proyecto_eduardo_andres.data.room.dao.PeliculaDao
import com.example.proyecto_eduardo_andres.data.room.dao.SerieDao
import com.example.proyecto_eduardo_andres.data.room.dao.UserDao

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun loginDao(): UserDao
    abstract fun perfilDao(): UserDao
    abstract fun  alquilerPeliculaDao(): AlquilerPeliculaDao
    abstract fun alquilerSerieDao(): AlquilerSerieDao
    abstract fun peliculaDao(): SerieDao
    abstract fun serieDao(): PeliculaDao
    
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
