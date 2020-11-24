package com.tugas.www.finder.database.repository

import androidx.lifecycle.LiveData
import com.tugas.www.finder.database.dao.NoteDao
import com.tugas.www.finder.database.model.Note

class NoteRepository(private val noteDao: NoteDao) {

    fun getAllNotes(): LiveData<List<Note>> {
        return noteDao.getAllNote()
    }

    suspend fun insertNote(note: Note) {
        noteDao.insert(note)
    }

    suspend fun updateNote(note: Note) {
        noteDao.update(note)
    }

    suspend fun deleteNote(note: Note) {
        noteDao.update(note)
    }

    fun getListDate(): LiveData<List<String>> {
        return noteDao.getListDate()
    }

    fun getListMonth(): LiveData<List<String>> {
        return noteDao.getListMonth()
    }

    fun getNoteByDateAndType(date: String, type: String): Note {
        return noteDao.getNoteByDateAndType(date, type)
    }

    fun getSumIncome(): LiveData<Int> {
        return noteDao.getSumIncome()
    }

    fun getSumExpense(): LiveData<Int> {
        return noteDao.getSumExpense()
    }
}