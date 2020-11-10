package com.tugas.www.finder.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.tugas.www.finder.database.model.Note

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: Note)

    @Update
    suspend fun update(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("SELECT * FROM note")
    fun getAllNote(): LiveData<List<Note>>

    @Query("SELECT DISTINCT date FROM note ORDER BY date ASC")
    fun getListDate(): LiveData<List<String>>
}