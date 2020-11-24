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

    @Query("SELECT DISTINCT strftime('%Y-%m', date) AS listMonth FROM note ORDER BY listMonth ASC")
    fun getListMonth(): LiveData<List<String>>

    @Query("SELECT * FROM note WHERE date = :dates AND type = :types LIMIT 1")
    fun getNoteByDateAndType(dates: String, types: String): Note

    @Query("SELECT SUM(amount) FROM note WHERE type = 'income'")
    fun getSumIncome(): LiveData<Int>

    @Query("SELECT SUM(amount) FROM note WHERE type = 'expense'")
    fun getSumExpense(): LiveData<Int>
}