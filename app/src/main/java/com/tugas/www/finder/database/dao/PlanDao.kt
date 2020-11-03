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
}