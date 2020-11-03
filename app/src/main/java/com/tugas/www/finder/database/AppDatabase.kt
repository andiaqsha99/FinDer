package com.tugas.www.finder.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tugas.www.finder.database.dao.NoteDao
import com.tugas.www.finder.database.dao.PlanDao
import com.tugas.www.finder.database.dao.UserDao
import com.tugas.www.finder.database.model.Note
import com.tugas.www.finder.database.model.Plan
import com.tugas.www.finder.database.model.User

@Database(entities = [Note::class, Plan::class, User::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun noteDao(): NoteDao
    abstract fun planDao(): PlanDao
    abstract fun userDao(): UserDao
}