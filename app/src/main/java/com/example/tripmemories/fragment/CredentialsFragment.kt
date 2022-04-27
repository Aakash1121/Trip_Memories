package com.example.tripmemories.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.tripmemories.R
import com.example.tripmemories.databinding.FragmentCredentialsBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_credentials.view.*

@AndroidEntryPoint
class CredentialsFragment : BaseFragment() {
    private lateinit var binding:FragmentCredentialsBinding
    override var bottomNavigationViewVisibility=View.GONE

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_credentials, container, false)

        tabConfig()
        return binding.root
    }



    private fun tabConfig() {
        binding.viewPagerFrame.adapter =
            CredentialsPagerAdapter(activity) //Attach the adapter with our ViewPagerAdapter passing the host activity
        TabLayoutMediator(
            binding.tabs, binding.viewPagerFrame
        ) { tab, position ->
            tab.text =
                (binding.viewPagerFrame.adapter as CredentialsPagerAdapter).mFragmentNames[position] //Sets tabs names as mentioned in ViewPagerAdapter fragmentNames array, this can be implemented in many different ways.
        }.attach()
    }

    class CredentialsPagerAdapter(fa: FragmentActivity?) : FragmentStateAdapter(fa!!) {
        private val mFragments = arrayOf<Fragment>( //Initialize fragments views
            //Fragment views are initialized like any other fragment (Extending Fragment)
            SignInFragment(),  //First fragment to be displayed within the pager tab number 1
            SignUpFragment()
        )
        val mFragmentNames = arrayOf( //Tabs names array
            "SignIn",
            "SignUp"
        )

        override fun getItemCount(): Int {
            return mFragments.size //Number of fragments displayed
        }

        override fun getItemId(position: Int): Long {
            return super.getItemId(position)
        }

        override fun createFragment(position: Int): Fragment {
            return mFragments[position]
        }
    }
}
