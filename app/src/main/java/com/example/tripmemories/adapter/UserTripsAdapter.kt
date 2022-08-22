package com.example.tripmemories.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.tripmemories.R
import com.example.tripmemories.controller.UserControllerImpl
import com.example.tripmemories.model.TripData
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.item_user_trips_adapter.view.*

class UserTripsAdapter(
    val context: Context,
    val tripsList: ArrayList<TripData>,
    val view: View,
) :
    RecyclerView.Adapter<UserTripsAdapter.ViewHolder>() {

     var userController= UserControllerImpl()

    class ViewHolder(val item: View) : RecyclerView.ViewHolder(item)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_user_trips_adapter, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return tripsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.item.txtTripTitle.text = tripsList[position].tripTitle
        holder.item.txtTripDesc.text = tripsList[position].tripDescription


        holder.item.adapterLLTop.setOnClickListener {

            val bundle: Bundle = bundleOf(
                "trip_images" to tripsList[position].tripPhotosURI,
                "trip_title" to tripsList[position].tripTitle,
                "trip_desc" to tripsList[position].tripDescription,
                "trip_people" to tripsList[position].tripPeople,
                "trip_data" to tripsList[position].tripDate
            )

            holder.item.findNavController().navigate(
                R.id.action_userTripsFragment_to_tripFragment,
                bundle
            )
        }

        holder.item.imgDeleteTrip.setOnClickListener {
            val signedInUserId = Firebase.auth.currentUser?.uid
            if (signedInUserId != null) {
                userController.deleteTrip(signedInUserId,tripsList[position].tripTitle, view)
            }
        }
    }

}