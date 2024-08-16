package com.cyberiyke.fitfuel.data.model

import android.net.Uri

data class User(
    val name: String = "",
    val gender: Gender = Gender.FEMALE,
    val imageUri: Uri? = null,
    val weightInKG: Float = 0.0f,
    val weeklyGoadInKm: Float = 0.0f
    )