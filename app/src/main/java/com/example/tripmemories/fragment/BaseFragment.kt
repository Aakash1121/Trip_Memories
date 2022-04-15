package com.example.tripmemories.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.tripmemories.activity.TripMemoriesActivity

abstract class BaseFragment : Fragment() {

    protected open var bottomNavigationViewVisibility = View.VISIBLE

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // get the reference of the parent activity and call the setBottomNavigationVisibility method.
        if (activity is TripMemoriesActivity) {
            var  tripMemoriesActivity = activity as TripMemoriesActivity
            tripMemoriesActivity.setBottomNavigationVisibility(bottomNavigationViewVisibility)
        }
    }
    override fun onResume() {
        super.onResume()
        if (activity is TripMemoriesActivity) {
            var  tripMemoriesActivity = activity as TripMemoriesActivity
            tripMemoriesActivity.setBottomNavigationVisibility(bottomNavigationViewVisibility)
        }
    }
}