package com.example.tripmemories.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.tripmemories.R
import com.example.tripmemories.databinding.ActivityTripMemoriesBinding

class TripMemoriesActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    lateinit var binding: ActivityTripMemoriesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_trip_memories)
        supportActionBar?.hide()

        val navHostHomeFragment =
            supportFragmentManager.findFragmentById(R.id.navHost) as NavHostFragment
        navController = navHostHomeFragment.navController
        appBarConfiguration = AppBarConfiguration(
            topLevelDestinationIds = setOf(
                R.id.splashFragment,
                R.id.aboutUsFragment,
                R.id.credentialsFragment,
                R.id.userTripsFragment,
                R.id.usersSettingsFragment
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.toolBar.setupWithNavController(navController, appBarConfiguration)
        binding.bottomBarView.setupWithNavController(navController)
    }

    fun setBottomNavigationVisibility(visibility: Int) {
        // get the reference of the bottomNavigationView and set the visibility.
        binding.bottomBarView.visibility = visibility
    }

}

