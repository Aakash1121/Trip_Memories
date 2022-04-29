package com.example.tripmemories.fragment

import android.app.DatePickerDialog
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.tripmemories.R
import com.example.tripmemories.controller.UserController
import com.example.tripmemories.databinding.FragmentAddTripBinding
import com.example.tripmemories.model.TripData
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class AddTripFragment : Fragment() {
    lateinit var binding: FragmentAddTripBinding

    @Inject
    lateinit var userController: UserController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_trip, container, false)
        datePickerForTrip()
        createTrip()
        addPhotos()
        return binding.root
    }

    private fun addPhotos() {
        binding.btnAddTripPhotos.setOnClickListener {
            it.findNavController().navigate(R.id.action_addTripFragment_to_addTripListPhotosFragment)
        }
    }

    private fun createTrip() {
        binding.btnAddTrip.setOnClickListener {
            val tripData = TripData()
//            tripData.tripPhotosURI=binding.imgTripPhotos.text.toString()
            tripData.tripTitle = binding.edtTripTitle.text.toString()
            tripData.tripDescription = binding.edtTripDesc.text.toString()
            tripData.tripDate = binding.edtTripDate.text.toString()
            tripData.tripPeople = binding.edtAddPeople.text.toString()

            val signedInUserId = Firebase.auth.currentUser?.uid
            if (signedInUserId != null) {
                userController.createTrip(this, signedInUserId, tripData.tripTitle, tripData)
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
        val myFormat = "dd-mm-yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.UK)
        binding.edtTripDate.setText(sdf.format(myCalender.time))
    }

    fun tripCreatedSuccessFully(tripTitle: String) {
        Snackbar.make(requireView(), "$tripTitle Added", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show()
    }

}