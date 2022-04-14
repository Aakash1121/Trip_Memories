package com.example.tripmemories.fragment

import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.tripmemories.R
import com.example.tripmemories.controller.UserController
import com.example.tripmemories.databinding.FragmentUserSettingsBinding
import com.example.tripmemories.model.UserData
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_user_settings.view.*

class UsersSettingsFragment : Fragment() {

    lateinit var binding: FragmentUserSettingsBinding

    private val userController = UserController()
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

        return binding.root
    }

    fun updateUserData(userData: UserData) {
        binding.txtUserSettingsFirstName.text = userData.firstName.toString()
//        binding.txtUserLastName.text = userData.lastName.toString()
//        binding.txtUserEmailId.text = userData.email.toString()
    }

}