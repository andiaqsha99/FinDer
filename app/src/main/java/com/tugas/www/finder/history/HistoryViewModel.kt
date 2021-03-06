package com.tugas.www.finder.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.tugas.www.finder.database.model.Note
import com.tugas.www.finder.database.repository.NoteRepository

class HistoryViewModel(private val noteRepository: NoteRepository): ViewModel() {

    private lateinit var listNote: LiveData<List<Note>>
    private lateinit var listDate: LiveData<List<String>>
    private lateinit var listMonth:LiveData<List<String>>
    private lateinit var sumIncome: LiveData<Int>
    private lateinit var sumExpense: LiveData<Int>

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

    fun setListMonth() {
        listMonth = noteRepository.getListMonth()
    }

    fun getListMonth(): LiveData<List<String>> {
        return listMonth
    }

    fun setSumIncome(){
        sumIncome = noteRepository.getSumIncome()
    }

    fun setSumExpense() {
        sumExpense = noteRepository.getSumExpense()
    }

    fun getSumExpense(): LiveData<Int> {
        return sumExpense
    }

    fun getSumIncome(): LiveData<Int> {
        return sumIncome
    }
}