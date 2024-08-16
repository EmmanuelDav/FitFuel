package com.cyberiyke.fitfuel

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class FitFuelApp: Application() {
   override fun onCreate() {
      super.onCreate()
   }
}