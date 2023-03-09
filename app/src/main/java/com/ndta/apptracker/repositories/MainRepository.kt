package com.ndta.apptracker.repositories

import com.ndta.apptracker.db.Run
import com.ndta.apptracker.db.RunDAO
import javax.inject.Inject

class MainRepository @Inject constructor(
    val runDAO: RunDAO
) {
    suspend fun insertRun(run: Run) = runDAO.insertRun(run)

    suspend fun deleteRun(run: Run) = runDAO.deleteRun(run)

    fun getAllRunsSortedByDate() = runDAO.getAllRunsSortedByDate()
    fun getAllRunsSortedByDistance() = runDAO.getAllRunsSortedByDistance()
    fun getAllRunsSortedByTimeInMillis() = runDAO.getAllRunsSortedByTimeInMillis()
    fun getAllRunsSortedByCaloriesBurned() = runDAO.getAllRunsSortedByCaloriesBurned()
    fun getAllRunsSortedByAvgSpeed() = runDAO.getAllRunsSortedByAvgSpeed()

    fun getTotalAvgSpeed() = runDAO.getTotalAvgSpeed()
    fun getTotalDistanceInMeters() = runDAO.getTotalDistanceInMeters()
    fun getTotalCaloriesBurned() = runDAO.getTotalCaloriesBurned()
    fun getTotalTimeInMillis() = runDAO.getTotalTimeInMillis()
}