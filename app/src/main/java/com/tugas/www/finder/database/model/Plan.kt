package com.tugas.www.finder.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Plan(
    @PrimaryKey (autoGenerate = true) val id_plan: Long = 0,
    val text: String? = null,
    val type: String? = null,
    val date: String? = null
)