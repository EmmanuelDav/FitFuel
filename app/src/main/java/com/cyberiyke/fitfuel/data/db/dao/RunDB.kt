package com.cyberiyke.fitfuel.data.db.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.cyberiyke.fitfuel.data.db.RunDoa
import com.cyberiyke.fitfuel.data.db.mapper.DBConverters
import com.cyberiyke.fitfuel.data.model.Run


@Database(
    entities = [Run::class],
    version = 1,

    )

@TypeConverters(DBConverters::class)
abstract class RunDB : RoomDatabase() {
    companion object {
        const val RUN_DB_NAME = "run_db"
    }

     abstract fun getRunDao() : RunDoa

}