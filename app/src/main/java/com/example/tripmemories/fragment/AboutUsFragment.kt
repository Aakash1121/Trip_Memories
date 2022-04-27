package com.example.tripmemories.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.tripmemories.R
import com.example.tripmemories.databinding.FragmentAboutUsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AboutUsFragment : BaseFragment() {

    lateinit var binding: FragmentAboutUsBinding

    override var bottomNavigationViewVisibility=View.GONE
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_about_us, container, false)

        binding.btnNext.setOnClickListener {
            it.findNavController().navigate(R.id.action_aboutUsFragment_to_credentialsFragment)
        }
        return binding.root
    }



}