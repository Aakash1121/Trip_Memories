package com.example.tripmemories.fragment

import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.tripmemories.R
import com.example.tripmemories.activity.TripMemoriesActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.dialog_progress.*
@AndroidEntryPoint
abstract class BaseFragment : Fragment() {

    private var doubleBackToExitPressedOnce = false
    private lateinit var mProgressdialog: Dialog
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

    fun showProgressDialog(text: String) {
        mProgressdialog = Dialog(requireContext())
        mProgressdialog.setContentView(R.layout.dialog_progress)
        mProgressdialog.tv_progress_text.text = text
        mProgressdialog.show()
    }

    fun hideProgressDialog() {
        mProgressdialog.dismiss()
    }
}