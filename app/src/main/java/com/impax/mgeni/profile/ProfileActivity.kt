package com.impax.mgeni.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import com.impax.mgeni.R
import com.impax.mgeni.auth.OTPActivity
import com.impax.mgeni.home.HomePage

class ProfileActivity : AppCompatActivity()
{
    private var backB: ImageButton ?= null

    private lateinit var resetPass: TextView

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        backB= findViewById(R.id.backB)
        resetPass= findViewById(R.id.resetPass)

        backB?.setOnClickListener{
            val intent = Intent (this, HomePage::class.java)
            startActivity(intent)
        }
        resetPass.setOnClickListener{
            val intent = Intent (this, OTPActivity::class.java)
            startActivity(intent)
        }


    }
}