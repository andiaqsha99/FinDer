package com.tugas.www.finder.calendar

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.tugas.www.finder.changeDateFormat
import com.tugas.www.finder.database.model.Note
import com.tugas.www.finder.database.model.Plan
import com.tugas.www.finder.database.repository.NoteRepository
import com.tugas.www.finder.database.repository.PlanRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CalendarViewModel(
    private val noteRepository: NoteRepository,
    private val planRepository: PlanRepository
) : ViewModel() {

    private val expenseNote = MutableLiveData<Note>()
    private val expensePlan = MutableLiveData<Plan>()
    private val incomeNote = MutableLiveData<Note>()
    private val incomePlan = MutableLiveData<Plan>()
    private lateinit var listDate :LiveData<List<String>>

    fun setPlanAndNote(date: String) {
        viewModelScope.launch(Dispatchers.IO) {
            incomePlan.postValue(planRepository.getPlanByDateAndType(date, "income"))
            expensePlan.postValue(planRepository.getPlanByDateAndType(date, "expense"))
            incomeNote.postValue(noteRepository.getNoteByDateAndType(date, "income"))
            expenseNote.postValue(noteRepository.getNoteByDateAndType(date, "expense"))
        }
    }

    fun getExpenseNote(): LiveData<Note> {
        return expenseNote
    }

    fun getExpensePlan(): LiveData<Plan> {
        return expensePlan
    }

    fun getIncomeNote(): LiveData<Note> {
        return incomeNote
    }

    fun getIncomePlan(): LiveData<Plan> {
        return incomePlan
    }

    fun setListDate() {
        listDate = planRepository.getListPlanDate()
    }

    fun getListDate(): LiveData<List<String>> {
        return listDate
    }
}