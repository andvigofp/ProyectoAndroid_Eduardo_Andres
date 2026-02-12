package com.example.proyecto_eduardo_andres.data.room.dao

import androidx.room.*
import com.example.proyecto_eduardo_andres.data.room.entity.User

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    suspend fun getAll(): List<User>

    @Query("SELECT * FROM user WHERE id = :id")
    suspend fun getById(id: String): User?

    @Query("SELECT * FROM user WHERE email = :email AND passwd = :password LIMIT 1")
    suspend fun login(email: String, password: String): User?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<User>)

    @Query("UPDATE user SET keep_logged = :keepLogged WHERE id = :id")
    suspend fun updateKeepLogged(id: String, keepLogged: Boolean)

    @Query("DELETE FROM user")
    suspend fun deleteAll()
}