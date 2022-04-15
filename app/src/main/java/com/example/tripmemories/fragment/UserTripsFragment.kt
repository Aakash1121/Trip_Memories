package com.example.tripmemories.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tripmemories.R
import com.example.tripmemories.adapter.UserTripsAdapter
import com.example.tripmemories.controller.UserController
import com.example.tripmemories.databinding.FragmentUserTripsBinding
import com.example.tripmemories.model.TripData
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class UserTripsFragment : BaseFragment() {
    override var bottomNavigationViewVisibility=View.VISIBLE

    lateinit var binding: FragmentUserTripsBinding
    private val userController = UserController()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_trips, container, false)



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val signedInUserId = Firebase.auth.currentUser?.uid
        Log.i("USER", "USER ID->${signedInUserId}")
        if (signedInUserId != null) {
            userController.userTripsDocument(this, signedInUserId)
        }

        binding.fabAddTrip.setOnClickListener {
            it.findNavController().navigate(R.id.action_userTripsFragment_to_addTripFragment)
            Toast.makeText(context, "Add Trip", Toast.LENGTH_SHORT).show()
        }
    }

    fun tripList(tripList:ArrayList<TripData>){
        binding.rvUserTrips.layoutManager=LinearLayoutManager(activity)
        binding.rvUserTrips.adapter=UserTripsAdapter(requireActivity(),tripList)
    }

}