package com.example.tripmemories.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.tripmemories.R
import com.example.tripmemories.databinding.FragmentTripBinding

class TripFragment : Fragment() {

    private lateinit var binding: FragmentTripBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_trip, container, false)
        val tripTitle: String? = requireArguments().getString("trip_title")
        val tripDesc: String? = requireArguments().getString("trip_desc")
        val tripPeople: String? = requireArguments().getString("trip_people")

        binding.txtTripFragTitle.text=tripTitle
        binding.txtTripFragDescription.text=tripDesc
        binding.txtTripFragPeople.text=tripPeople

        return binding.root
    }


}