package com.tugas.www.finder.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.tugas.www.finder.database.model.Note
import com.tugas.www.finder.database.model.Plan
import com.tugas.www.finder.database.repository.NoteRepository
import com.tugas.www.finder.database.repository.PlanRepository

class HomeViewModel(private val noteRepository: NoteRepository, private val planRepository: PlanRepository): ViewModel() {

    private lateinit var listNote: LiveData<List<Note>>
    private lateinit var listDate: LiveData<List<String>>
    private lateinit var sumIncome: LiveData<Int>
    private lateinit var sumExpense: LiveData<Int>
    private lateinit var sumDailyExpense: LiveData<Int>
    private lateinit var sumWeeklyExpense: LiveData<Int>
    private lateinit var sumMonthlyExpense: LiveData<Int>
    private lateinit var listOnGoingPlan: LiveData<List<Plan>>

    fun setListNote() {
        listNote = noteRepository.getAllNotes()
    }

    fun getListNote(): LiveData<List<Note>> {
        return listNote
    }

    fun setListDate() {
        listDate = noteRepository.getListDate()
    }

    fun getListDate(): LiveData<List<String>> {
        return listDate
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

    fun setDailyExpenseSum(date: String) {
        sumDailyExpense = noteRepository.getSumExpenseOneDay(date)
    }

    fun setWeeklyExpenseSum(firstDate: String, lastDate: String) {
        sumWeeklyExpense = noteRepository.getSumExpenseWeekly(firstDate, lastDate)
    }

    fun setMonthlyExpenseSum(monthYear: String) {
        sumMonthlyExpense = noteRepository.getSumExpenseMonthly(monthYear)
    }

    fun getSumDailyExpense(): LiveData<Int> {
        return sumDailyExpense
    }

    fun getSumWeeklyExpense(): LiveData<Int> {
        return sumWeeklyExpense
    }

    fun getSumMonthlyExpense(): LiveData<Int> {
        return sumMonthlyExpense
    }

    fun setOngoingPlan(date: String) {
        listOnGoingPlan = planRepository.getListNextPlan(date)
    }

    fun getOnGoingPlan(): LiveData<List<Plan>> {
        return listOnGoingPlan
    }
}