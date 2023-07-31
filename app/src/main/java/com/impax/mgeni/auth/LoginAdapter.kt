package com.impax.mgeni.auth

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class LoginAdapter(fm: FragmentManager, context: Context, private val totaltabs: Int) :
    FragmentPagerAdapter(fm) {

    override fun getCount(): Int {
        return totaltabs // Return the total number of tabs
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> LoginTabFragment()
            1 -> SignUpTabFragment()
            else -> throw IllegalArgumentException("Invalid position: $position")
        }
    }
}



