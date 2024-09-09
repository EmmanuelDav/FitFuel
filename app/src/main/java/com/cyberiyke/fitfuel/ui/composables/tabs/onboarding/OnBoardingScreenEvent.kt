package com.cyberiyke.fitfuel.ui.composables.tabs.onboarding

import com.cyberiyke.fitfuel.data.model.Gender

interface OnBoardingScreenEvent {

    fun updateName(name :String)

    fun updateGender(gender: Gender)

    fun updateWeight(weightInKg: Float)

    fun updateWeeklyGoal(weeklyGoalInKm: Float)
}