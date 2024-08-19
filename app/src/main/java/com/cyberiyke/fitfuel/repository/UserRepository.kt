package com.cyberiyke.fitfuel.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import java.util.prefs.Preferences
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class UserRepository @Inject constructor(private val dataStore: DataStore<Preferences>) {

    companion object {
        val USER_NAME = stringPreferencesKey("user_name")
        val USER_GENDER = stringPreferencesKey("user_gender")
        val USER_WEIGHT_IN_KG = floatPreferencesKey("user_weight_in_kg")
        val USER_WEEKLY_GOAL_IN_KM = floatPreferencesKey("user_weekly_goal_in_km")
        val USER_IMG_URI = stringPreferencesKey("user_image_uri")

    }

    val user = 
}