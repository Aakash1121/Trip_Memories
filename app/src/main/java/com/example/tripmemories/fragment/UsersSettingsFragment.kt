package com.example.tripmemories.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.tripmemories.R
import com.example.tripmemories.controller.UserControllerImpl
import com.example.tripmemories.databinding.FragmentUserSettingsBinding
import com.example.tripmemories.model.UserData
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UsersSettingsFragment : BaseFragment() {
    override var bottomNavigationViewVisibility=View.VISIBLE
    lateinit var binding: FragmentUserSettingsBinding

    private val userController = UserControllerImpl()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_user_settings, container, false)
        val signedInUserId = Firebase.auth.currentUser?.uid
        Log.i("USER", "USER ID->${signedInUserId}")
        if (signedInUserId != null) {
            userController.userDocument(this, signedInUserId)
        }
        binding.btnSettingsAppearance.setOnClickListener {
            it.findNavController().navigate(R.id.action_usersSettingsFragment_to_appearanceFragment)
            Toast.makeText(context, "Appearance", Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }

    fun updateUserData(userData: UserData) {
        binding.txtUserSettingsFirstName.text = userData.firstName.toString()
//        binding.txtUserLastName.text = userData.lastName.toString()
//        binding.txtUserEmailId.text = userData.email.toString()
    }

}