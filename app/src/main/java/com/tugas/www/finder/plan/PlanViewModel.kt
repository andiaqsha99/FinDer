package com.tugas.www.finder.plan

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tugas.www.finder.database.model.Plan
import com.tugas.www.finder.database.repository.PlanRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlanViewModel(private val planRepository: PlanRepository): ViewModel() {

    private lateinit var listOnGoingPlan: LiveData<List<Plan>>

    fun inputPlan(plan: Plan) {
        viewModelScope.launch(Dispatchers.IO) {
            planRepository.insertPlan(plan)
        }
    }

    fun setOngoingPlan(date: String) {
        listOnGoingPlan = planRepository.getListNextPlan(date)
    }

    fun getOnGoingPlan(): LiveData<List<Plan>> {
        return listOnGoingPlan
    }
}