package com.example.proyecto_eduardo_andres.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.proyecto_eduardo_andres.data.room.entity.User

@Dao
interface UserPreferencesDao {

    @Query("SELECT * FROM user")
    fun getAll(): List<User>

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<User>

    @Query("SELECT * FROM user WHERE name LIKE :name LIMIT 1")
    fun findByName(name: String): User

    @Insert
    fun insertAll(vararg users: User)

    @Query("DELETE FROM user")
    fun delete()


}