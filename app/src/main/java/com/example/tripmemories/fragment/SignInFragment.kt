package com.example.tripmemories.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.tripmemories.R
import com.example.tripmemories.databinding.FragmentSignInBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SignInFragment : Fragment() {
    lateinit var binding: FragmentSignInBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_in, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSignIn.setOnClickListener {
            signInUser(
                binding.signInEmailId.text.toString(),
                binding.signInPassword.text.toString(),
                view
            )

        }
    }

    private fun signInUser(emailId: String, password: String, view: View) {

        FirebaseAuth.getInstance().signInWithEmailAndPassword(emailId, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.i("USER", "signInWithEmail:success")
                   view.findNavController().navigate(R.id.action_credentialsFragment_to_userTripsFragment)

                } else {
                    Log.i("TAG", "signIn:Failed")
                }
            }
    }


}