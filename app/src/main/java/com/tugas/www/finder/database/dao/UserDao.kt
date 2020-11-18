package com.tugas.www.finder.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.tugas.www.finder.database.model.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: User)

    @Update
    suspend fun update(user: User)

    @Delete
    suspend fun delete(user: User)

    @Query("SELECT * FROM user")
    fun getUser(): LiveData<User>
}