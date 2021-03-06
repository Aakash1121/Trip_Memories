package com.example.tripmemories.fragment

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tripmemories.R

import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment() {

    override var bottomNavigationViewVisibility=View.GONE

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        Handler().postDelayed(
            { findNavController().navigate(R.id.action_splashFragment_to_aboutUsFragment) },
            1000
        )

        val view = inflater.inflate(R.layout.fragment_splash, container, false)

        return view
    }



}