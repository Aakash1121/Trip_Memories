package com.example.tripmemories.fragment

import android.app.DatePickerDialog
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tripmemories.R
import com.example.tripmemories.adapter.AddTripsAddPhotosAdapter
import com.example.tripmemories.contracts.AddTripAddPhotosResultContract
import com.example.tripmemories.controller.UserController
import com.example.tripmemories.databinding.FragmentAddTripBinding
import com.example.tripmemories.model.TripData
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class AddTripFragment : Fragment() {
    lateinit var binding: FragmentAddTripBinding

    @Inject
    lateinit var userController: UserController

    var imagesList = ArrayList<Uri>()
    var contract = registerForActivityResult(AddTripAddPhotosResultContract()) {

        val count = it.size - 1
        for (i in 0..count) {
            imagesList.add(it[i])
        }
        binding.rvAddPhotosList.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        binding.rvAddPhotosList.adapter = AddTripsAddPhotosAdapter(requireActivity(), imagesList)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_trip, container, false)
        datePickerForTrip()

        createTrip()

        addPhotos()
        Log.i("DATA", "in onCreate view")
        return binding.root
    }

    private fun addPhotos() {
        binding.btnAddTripPhotos.setOnClickListener {
            contract.launch("Choose")
        }
    }

    private fun createTrip() {

        binding.btnAddTrip.setOnClickListener {
            val tripData = TripData()
            val count = imagesList.size - 1

            for (i in 0..count) {
                tripData.tripPhotosURI.add(imagesList[i].toString())
            }

            Log.i("data", "TripImages->${tripData.tripPhotosURI}")
            tripData.tripTitle = binding.edtTripTitle.text.toString()
            tripData.tripDescription = binding.edtTripDesc.text.toString()
            tripData.tripDate = binding.edtTripDate.text.toString()
            tripData.tripPeople = binding.edtAddPeople.text.toString()

            CoroutineScope(IO).launch {
                val signedInUserId = Firebase.auth.currentUser?.uid
                if (signedInUserId != null) {
                    userController.createTrip(requireView(),signedInUserId, tripData.tripTitle, tripData)
                }
            }
        }
    }


    private fun datePickerForTrip() {
        val myCalendar = Calendar.getInstance()

        val datePicker = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, month)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateTable(myCalendar)
        }

        binding.txtDateInputLayout.setEndIconOnClickListener {
            DatePickerDialog(
                requireContext(),
                datePicker,
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }

    private fun updateTable(myCalender: Calendar) {
        val myFormat = "dd-MM-yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.UK)
        binding.edtTripDate.setText(sdf.format(myCalender.time))
    }

}