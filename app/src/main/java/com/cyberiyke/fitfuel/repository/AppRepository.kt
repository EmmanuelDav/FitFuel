package com.cyberiyke.fitfuel.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.cyberiyke.fitfuel.data.db.RunDoa
import com.cyberiyke.fitfuel.data.model.Run
import com.cyberiyke.fitfuel.utils.RunSortedOrder
import kotlinx.coroutines.flow.Flow
import java.util.Date
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepository @Inject constructor(private val runDoa: RunDoa) {

    suspend fun insertRun(run: Run) = runDoa.insertRun(run)

    suspend fun deleteRun(run: Run) = runDoa.deleteRun(run)

    fun getSortedAllRun(sortedOrder: RunSortedOrder) = Pager(
        config = PagingConfig(pageSize = 20),
    ) {
        when (sortedOrder) {
            RunSortedOrder.DATE -> runDoa.getAllRunSortedByDat()
            RunSortedOrder.DURATION -> runDoa.getAllRunSortedByDuration()
            RunSortedOrder.CALORIES_BURNT -> runDoa.getAllRunSortByCaloriesBurned()
            RunSortedOrder.AVERAGE_SPEED -> runDoa.getAllRunSortByAvgSpeed()
            RunSortedOrder.DISTANCE -> runDoa.getAllRunSortByDistance()
        }
    }


    suspend fun getRunStatsInDateRange(fromDate: Date?, toDate: Date?) =
        runDoa.getRunStatsInDateRange(fromDate, toDate)

    fun getRunByDescDateWithLimit(limit: Int) = runDoa.getRunByDescDateWithLimit(limit)

    fun getTotalRunningDuration(fromDate: Date? = null, toDate: Date? = null): Flow<Long> =
        runDoa.getTotalRunningDuration(fromDate, toDate)

    fun getTotalCaloriesBurned(fromDate: Date? = null, toDate: Date? = null): Flow<Long> =
        runDoa.getTotalCaloriesBurned(fromDate, toDate)

    fun getTotalDistance(fromDate: Date? = null, toDate: Date? = null): Flow<Long> =
        runDoa.getTotalDistance(fromDate, toDate)

    fun getTotalAvgSpeed(fromDate: Date? = null, toDate: Date? = null): Flow<Float> =
        runDoa.getTotalAvgSpeed(fromDate, toDate)


}