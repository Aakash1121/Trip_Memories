package com.example.tripmemories.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.tripmemories.R
import com.example.tripmemories.databinding.ActivityTripMemoriesBinding

class TripMemoriesActivity : AppCompatActivity() {

    lateinit var binding:ActivityTripMemoriesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_trip_memories)
        supportActionBar?.hide()
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHost) as NavHostFragment
        val navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(
            topLevelDestinationIds = setOf(R.id.splashFragment,R.id.aboutUsFragment),
            fallbackOnNavigateUpListener = ::onSupportNavigateUp
        )
        binding.toolBar.setupWithNavController(navController, appBarConfiguration)
    }
}
