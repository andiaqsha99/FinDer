package com.tugas.www.finder.database.repository

import androidx.lifecycle.LiveData
import com.tugas.www.finder.database.dao.PlanDao
import com.tugas.www.finder.database.model.Plan

class PlanRepository(private val planDao: PlanDao) {

    fun getAllPlans(): LiveData<List<Plan>> {
        return planDao.getAllPlan()
    }

    suspend fun insertPlan(plan: Plan) {
        planDao.insert(plan)
    }

    suspend fun updatePlan(plan: Plan) {
        planDao.update(plan)
    }

    suspend fun deletePlan(plan: Plan) {
        planDao.delete(plan)
    }

    fun getPlanByDateAndType(date:String, type :String): Plan {
        return planDao.getPlanByDateAndType(date, type)
    }

    fun getListPlanDate(): LiveData<List<String>> {
        return planDao.getListPlanDate()
    }

    fun getListNextPlan(date: String): LiveData<List<Plan>> {
        return planDao.getAllNextPLan(date)
    }
}