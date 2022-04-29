package com.example.tripmemories.fragment

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tripmemories.R
import com.example.tripmemories.adapter.AddTripsAddPhotosAdapter
import com.example.tripmemories.contracts.AddTripAddPhotosResultContract
import com.example.tripmemories.databinding.FragmentAddTripListPhotosBinding

class AddTripListPhotosFragment : BaseFragment() {
    lateinit var binding: FragmentAddTripListPhotosBinding
    var imagesList = ArrayList<Uri>()
    var contract = registerForActivityResult(AddTripAddPhotosResultContract()) {

        val count = it.size - 1
        for (i in 0..count) {
            imagesList.add(it[i])
        }
        binding.rvAddPhotosList.layoutManager = LinearLayoutManager(activity)
        binding.rvAddPhotosList.adapter = AddTripsAddPhotosAdapter(requireActivity(), imagesList)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_add_trip_list_photos,
            container,
            false
        )

        binding.btnChooseFromGallery.setOnClickListener {
            contract.launch("Choose")
        }
        return binding.root
    }



}