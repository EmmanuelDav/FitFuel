package com.cyberiyke.fitfuel.data.db.dao

import androidx.paging.PagingSource
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cyberiyke.fitfuel.data.model.Run
import kotlinx.coroutines.flow.Flow
import java.util.Date

interface RunDoa {


    @Insert(onConflict = OnConflictStrategy.REPLACE )
    suspend fun insertRun(run : Run)

    @Delete
    suspend fun deleteRun(run: Run)

    @Query("SELECT * FROM running_table ORDER BY timestamp DESC")
    suspend fun getAllRunSortedByDat() :PagingSource<Int,  Run>

    @Query("SELECT * From running_table order by durationInMills DESC")
    suspend fun  getAllRunSortedByDuration() : PagingSource<Int, Run>

    @Query("SELECT * FROM running_table ORDER BY caloriesBurnt DESC")
    fun getAllRunSortByCaloriesBurned(): PagingSource<Int, Run>

    @Query("SELECT * FROM running_table ORDER BY averageSpeedInKm DESC")
    fun getAllRunSortByAvgSpeed(): PagingSource<Int, Run>

    @Query("SELECT * FROM running_table ORDER BY disInMeters DESC")
    fun getAllRunSortByDistance(): PagingSource<Int, Run>

    @Query("SELECT * FROM running_table ORDER BY timestamp DESC LIMIT :limit")
    fun getRunByDescDateWithLimit(limit: Int): Flow<List<Run>>

    @Query("Select * from running_table where " +
            "(:fromDate is Null or timestamp >= :fromDate) and " +
            "(:toDate is Null or timestamp <= :toDate) " +
            "ORDER by timestamp DESC")

    suspend fun getRunStatsInDateRange(fromDate: Date?, toDate: Date?): List<Run>

    @Query(
        "SELECT TOTAL(disInMeters) FROM running_table WHERE " +
                "(:fromDate IS NULL OR timestamp >= :fromDate) AND " +
                "(:toDate IS NULL OR timestamp <= :toDate) " +
                "ORDER BY timestamp DESC"
    )
    fun getTotalRunningDuration(fromDate: Date?, toDate: Date?): Flow<Long>

    @Query(
        "SELECT TOTAL(caloriesBurnt) FROM running_table WHERE " +
                "(:fromDate IS NULL OR timestamp >= :fromDate) AND " +
                "(:toDate IS NULL OR timestamp <= :toDate) " +
                "ORDER BY timestamp DESC"
    )
    fun getTotalCaloriesBurned(fromDate: Date?, toDate: Date?): Flow<Long>

    @Query(
        "SELECT TOTAL(disInMeters) FROM running_table WHERE " +
                "(:fromDate IS NULL OR timestamp >= :fromDate) AND " +
                "(:toDate IS NULL OR timestamp <= :toDate) " +
                "ORDER BY timestamp DESC"
    )
    fun getTotalDistance(fromDate: Date?, toDate: Date?): Flow<Long>

    @Query(
        "SELECT AVG(averageSpeedInKm) FROM running_table WHERE " +
                "(:fromDate IS NULL OR timestamp >= :fromDate) AND " +
                "(:toDate IS NULL OR timestamp <= :toDate) " +
                "ORDER BY timestamp DESC"
    )
    fun getTotalAvgSpeed(fromDate: Date?, toDate: Date?): Flow<Float>



}