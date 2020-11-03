package com.tugas.www.finder.database.repository

import androidx.lifecycle.LiveData
import com.tugas.www.finder.database.dao.UserDao
import com.tugas.www.finder.database.model.User

class UserRepository(private val userDao: UserDao) {

    fun getUserData(): LiveData<User> {
        return userDao.getUser()
    }

    suspend fun updateUser(user: User) {
        userDao.update(user)
    }

    suspend fun insertUser(user: User) {
        userDao.insert(user)
    }

    suspend fun deleteUser(user: User) {
        userDao.delete(user)
    }
}