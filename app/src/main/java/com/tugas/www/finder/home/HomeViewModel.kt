package com.tugas.www.finder.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.tugas.www.finder.database.model.Note
import com.tugas.www.finder.database.repository.NoteRepository

class HomeViewModel(private val noteRepository: NoteRepository): ViewModel() {

    private lateinit var listNote: LiveData<List<Note>>
    private lateinit var listDate: LiveData<List<String>>

    fun setListNote() {
        listNote = noteRepository.getAllNotes()
    }

    fun getListNote(): LiveData<List<Note>> {
        return listNote
    }

    fun setListDate() {
        listDate = noteRepository.getListDate()
    }

    fun  getListDate(): LiveData<List<String>> {
        return listDate
    }
}