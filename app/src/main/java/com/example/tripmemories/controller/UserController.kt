package com.example.tripmemories.controller

import androidx.fragment.app.Fragment
import com.example.tripmemories.fragment.UserTripsFragment
import com.example.tripmemories.model.TripData
import kotlinx.coroutines.CoroutineScope


interface UserController {

    fun userDocument(fragment: Fragment, userId: String)
    suspend fun createTrip( userId: String, tripTitle: String, tripData: TripData)
    fun userTripsDocument(fragment: UserTripsFragment, userId: String)

}