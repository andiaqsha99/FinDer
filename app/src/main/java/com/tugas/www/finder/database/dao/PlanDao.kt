package com.tugas.www.finder.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.tugas.www.finder.database.model.Plan

@Dao
interface PlanDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(plan: Plan)

    @Update
    suspend fun update(plan: Plan)

    @Delete
    suspend fun delete(plan: Plan)

    @Query("SELECT * FROM `plan`")
    fun getAllPlan(): LiveData<List<Plan>>

    @Query("SELECT * FROM `plan` WHERE date = :dates AND type = :types LIMIT 1")
    fun getPlanByDateAndType(dates: String, types: String): Plan

    @Query("SELECT DISTINCT date FROM `plan` ORDER BY date ASC")
    fun getListPlanDate(): LiveData<List<String>>

    @Query("SELECT * FROM `plan` WHERE date >= :dates")
    fun getAllNextPLan(dates: String): LiveData<List<Plan>>
}