package com.tugas.www.finder.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note (
    @PrimaryKey(autoGenerate = true) val id_note: Long = 0,
    val text: String? = null,
    val type: String? = null,
    val date: String? = null
)