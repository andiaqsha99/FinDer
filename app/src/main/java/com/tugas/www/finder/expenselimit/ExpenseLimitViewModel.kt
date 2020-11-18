package com.tugas.www.finder.expenselimit

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tugas.www.finder.database.model.User
import com.tugas.www.finder.database.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExpenseLimitViewModel(private val userRepository: UserRepository): ViewModel() {

    private fun createNewUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.insertUser(user)
        }
    }

    fun updateUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.updateUser(user)
        }
    }

    fun setUserData() {
        val user: User
        if (isUserEmpty()) {
            user = User(
                name = "user"
            )
            createNewUser(user)
        }
    }

    fun getUserData(): LiveData<User> {
        return userRepository.getUserData()
    }

    private fun isUserEmpty(): Boolean {

        val data = userRepository.getUserData()
        if (data.value == null) {
            return true
        }
        return false
    }
}