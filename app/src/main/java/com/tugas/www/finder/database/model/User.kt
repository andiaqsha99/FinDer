package com.tugas.www.finder.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) val id_user: Int = 0,
    val name: String? = null,
    val pass_code: Int = 0,
    val daily_limit: Int = 0,
    val weekly_limit: Int = 0,
    val monthly_limit: Int = 0
)