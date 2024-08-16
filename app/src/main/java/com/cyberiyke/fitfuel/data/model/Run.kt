package com.cyberiyke.fitfuel.data.model

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity( tableName = "running_table")
data class Run(
    var img:Bitmap,
    var averageSpeedInKm: Float = 0.0f,
    var disInMeters:Int = 0,
    var durationInMills: Long = 0L,
    var caloriesBurnt: Int = 0,
    var timestamp: java.util.Date = java.util.Date(),

    @PrimaryKey(autoGenerate = true)
    val id:Int = 0

    )