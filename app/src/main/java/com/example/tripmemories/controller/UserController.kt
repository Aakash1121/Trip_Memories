package com.example.tripmemories.controller

import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.example.tripmemories.fragment.AddTripFragment
import com.example.tripmemories.fragment.UserTripsFragment
import com.example.tripmemories.fragment.UsersSettingsFragment
import com.example.tripmemories.model.TripData
import com.example.tripmemories.model.UserData
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class UserController {
    private val db = Firebase.firestore

    fun userDocument(fragment: Fragment, userId: String) {

        db.collection("USERS")
            .document(userId)
            .get()
            .addOnSuccessListener { documentSnapshot ->
                val userData = documentSnapshot.toObject(UserData::class.java)!!

                when (fragment) {
                    is UserTripsFragment -> {

                    }
                    is UsersSettingsFragment -> {
                        fragment.updateUserData(userData)
                    }
                }

            }.addOnFailureListener {
                Log.i("USER", "Couldn't fetch document of ID->${userId}")
            }
    }

    fun createTrip(fragment: Fragment, userId: String, tripTitle: String, tripData: TripData) {
        db.collection("USERS").document(userId)
            .collection("TRIPS").document(tripTitle).set(tripData, SetOptions.merge())
            .addOnCompleteListener {
                Log.i("TRIP", "Trip Added Successfully for user->${userId}")
            }
        when (fragment) {
            is AddTripFragment -> {
                fragment.tripCreatedSuccessFully(tripData.tripTitle)
            }
        }
    }

    fun userTripsDocument(fragment: UserTripsFragment, userId: String) {

        db.collection("USERS").document(userId)
            .collection("TRIPS")
            .get()
            .addOnSuccessListener { result ->
                val tripsList = ArrayList<TripData>()
                for (document in result) {
                    val tripData = document.toObject(TripData::class.java)
                    tripsList.add(tripData)
                    Log.i("TRIP", "TRIP->")
                }
                fragment.tripList(tripsList)
            }.addOnFailureListener {
                Log.i("TRIP", "Couldn't get documents of TRIPS>${userId}")
            }
    }

}