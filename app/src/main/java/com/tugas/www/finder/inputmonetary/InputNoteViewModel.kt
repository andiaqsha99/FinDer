package com.tugas.www.finder.inputmonetary

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tugas.www.finder.database.dao.NoteDao
import com.tugas.www.finder.database.model.Note
import com.tugas.www.finder.database.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class InputNoteViewModel(private val noteRepository: NoteRepository): ViewModel() {

    fun inputNote(note: Note) {
       viewModelScope.launch(Dispatchers.IO) {
           noteRepository.insertNote(note);
       }
    }
}