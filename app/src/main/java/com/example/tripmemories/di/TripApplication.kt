package com.example.tripmemories.di

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TripApplication :Application(){

    override fun onCreate() {
        super.onCreate()
    }

}