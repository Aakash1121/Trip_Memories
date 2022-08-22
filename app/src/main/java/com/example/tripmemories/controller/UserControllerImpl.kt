package com.example.tripmemories.controller

import android.net.Uri
import android.util.Log
import android.view.View
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.tripmemories.R
import com.example.tripmemories.fragment.UserTripsFragment
import com.example.tripmemories.fragment.UsersSettingsFragment
import com.example.tripmemories.model.TripData
import com.example.tripmemories.model.UserData
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserControllerImpl @Inject constructor():UserController {
    private val db = Firebase.firestore

    private val storageRef = Firebase.storage.reference



    override fun userDocument(fragment: Fragment, userId: String) {

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

    override suspend fun createTrip(
        view: View,
        userId: String,
        tripTitle: String,
        tripData: TripData
    ) {
        val count = tripData.tripPhotosURI.size - 1

        val job = CoroutineScope(IO).launch {
            for (i in 0..count) {
                val uri = getUrl(tripData.tripPhotosURI[i].toUri())
                Log.i("URI", uri)
                tripData.tripPhotosURI[i] = uri
            }
        }
        job.join()

        db.collection("USERS").document(userId)
            .collection("TRIPS").document(tripTitle).set(tripData, SetOptions.merge())
            .addOnCompleteListener {
                Log.i("TRIP", "Trip Added Successfully for user->${userId}")
                Snackbar.make(view, "$tripTitle Added", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            }

    }

    private suspend fun getUrl(path: Uri): String {
        var imageRef = storageRef.child("Images" + System.currentTimeMillis())
        return withContext(Dispatchers.IO) {
            imageRef
                .putFile(path)
                .await() // await() instead of snapshot
                .storage
                .downloadUrl
                .await() // await the url
                .toString()
        }
    }

    override fun userTripsDocument(fragment: UserTripsFragment, userId: String) {

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

    override fun deleteTrip(userId: String, tripTitle: String, view: View) {

        db.collection("USERS").document(userId)
            .collection("TRIPS").document(tripTitle).delete()
            .addOnSuccessListener {
                Log.i("Delete", "DocumentSnapshot successfully deleted!")
                view.findNavController().navigate(R.id.userTripsFragment)
                Snackbar.make(view, "Removed $tripTitle ", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()


            }
            .addOnFailureListener { e -> Log.i("Delete", "Error deleting document", e) }

    }

}