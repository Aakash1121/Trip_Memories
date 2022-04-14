package com.example.tripmemories.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.tripmemories.R
import com.example.tripmemories.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        val navHostHomeFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerViewHome) as NavHostFragment
        navController = navHostHomeFragment.navController
        appBarConfiguration = AppBarConfiguration(
            topLevelDestinationIds = setOf(R.id.userTripsFragment, R.id.usersSettingsFragment)
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.bottomBarView.setupWithNavController(navController)
        // binding.toolBarHome.setupWithNavController(navController, appBarConfiguration)

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
    }
}