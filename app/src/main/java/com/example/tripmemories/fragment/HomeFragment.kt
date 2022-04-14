package com.example.tripmemories.fragment

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.tripmemories.R
import com.example.tripmemories.databinding.FragmentHomeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class HomeFragment : Fragment(){
    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

//        val fragmentManager: FragmentManager = childFragmentManager
//        fragmentManager.beginTransaction().replace(R.id.homeContainer, UserTripsFragment())
//            .commit()
//
//        binding.bottomBarView.setOnItemSelectedListener(this)



        return binding.root
    }

//    override fun onNavigationItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            R.id.navigation_loggedIn_home -> {
//                val fragmentManager: FragmentManager = childFragmentManager
//                fragmentManager.beginTransaction().replace(R.id.homeContainer, UserTripsFragment())
//                    .commit()
//                Toast.makeText(context, "Home", Toast.LENGTH_SHORT).show()
//                return true
//            }
//
//            R.id.navigation_loggedIn_settings -> {
//                val fragmentManager: FragmentManager = childFragmentManager
//                fragmentManager.beginTransaction()
//                    .replace(R.id.homeContainer, UsersSettingsFragment())
//                    .commit()
//                Toast.makeText(context, "notifications", Toast.LENGTH_SHORT).show()
//                return true
//            }
//        }
//        return false
//    }
}