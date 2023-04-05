package com.impax.mgeni

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.impax.mgeni.auth.LoginActivity
import com.impax.mgeni.checkin.CheckinFragment
import com.impax.mgeni.checkout.CheckoutFragment
import com.impax.mgeni.commons.SharedPrefs
import com.impax.mgeni.home.HomeFragment
import com.ismaeldivita.chipnavigation.ChipNavigationBar

class MainActivity : AppCompatActivity()
{
    private val fragment = HomeFragment()
    var sharedPrefs: SharedPrefs? = null
    private val menu by lazy { findViewById<ChipNavigationBar>(R.id.bottomNav) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPrefs = SharedPrefs(this)


        openMainFragment()
        supportActionBar?.hide()

        menu.setItemSelected(R.id.home)
        menu.setOnItemSelectedListener {
            when (it) {

                R.id.home -> {
                    openMainFragment()
                }
                R.id.checkIn -> {
                    val checkinFragment = CheckinFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, checkinFragment).commit()


                }
                R.id.checkOut -> {
                    val checkoutFragment = CheckoutFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, checkoutFragment).commit()
                    }
            }
        }
    }



    private fun openMainFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout, fragment)
        transaction.commit()
    }
}