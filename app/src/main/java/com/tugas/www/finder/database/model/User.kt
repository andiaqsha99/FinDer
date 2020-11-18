package com.tugas.www.finder.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) val id_user: Int = 0,
    var name: String? = null,
    var pass_code: Int = 0,
    var daily_limit: Int = 0,
    var weekly_limit: Int = 0,
    var monthly_limit: Int = 0
)