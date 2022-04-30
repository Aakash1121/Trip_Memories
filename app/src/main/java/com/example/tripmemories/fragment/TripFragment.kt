package com.example.tripmemories.fragment

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tripmemories.R
import com.example.tripmemories.adapter.UserTripPhotosListAdapter
import com.example.tripmemories.databinding.FragmentTripBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
        val tripDate: String? = requireArguments().getString("trip_data")
        val imageList: ArrayList<String>? = requireArguments().getStringArrayList("trip_images")

        binding.txtTripFragTitle.text = tripTitle
        binding.txtTripFragDescription.text = tripDesc
        binding.txtTripFragPeople.text = tripPeople
        binding.txtTripFragDate.text = tripDate

        val tripImageList = ArrayList<Uri>()

        val count = imageList!!.size - 1
        for (i in 0..count) {
            val imageUri = imageList[i].toUri()
            tripImageList.add(imageUri)
            Log.i("data", "createtrip->${tripImageList[i].toString()}")
        }

        binding.rvTripPhotosList.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        binding.rvTripPhotosList.adapter =
            UserTripPhotosListAdapter(requireActivity(), tripImageList)

        return binding.root
    }


}