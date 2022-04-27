package com.example.tripmemories.controller

import androidx.fragment.app.Fragment
import com.example.tripmemories.fragment.UserTripsFragment
import com.example.tripmemories.model.TripData


interface UserController {

    fun userDocument(fragment: Fragment, userId: String)
    fun createTrip(fragment: Fragment, userId: String, tripTitle: String, tripData: TripData)
    fun userTripsDocument(fragment: UserTripsFragment, userId: String)

}