package com.cyberiyke.fitfuel.utils

enum class RunSortedOrder {
    DATE,
    DURATION,
    CALORIES_BURNT,
    AVERAGE_SPEED,
    DISTANCE;


    override fun toString(): String {
        return super.toString()
            .lowercase().replace('_', ' ').replaceFirstChar { it.uppercase() }
    }
}