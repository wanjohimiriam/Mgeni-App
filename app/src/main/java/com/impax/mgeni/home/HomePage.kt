package com.impax.mgeni.home

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.impax.mgeni.MainActivity
import com.impax.mgeni.R
import com.impax.mgeni.checkin.CheckInActivity
import com.impax.mgeni.checkout.CheckOutActivity
import com.impax.mgeni.commons.SharedPrefs
import com.impax.mgeni.ocr.MainMrzActivity
import com.impax.mgeni.profile.ProfileActivity
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

class HomePage : AppCompatActivity()
{
    private var scanhere: TextView ?= null
    private var profile_image: ImageButton ?= null
    private var sharedPrefs: SharedPrefs? = null

    override fun onCreate(savedInstanceState: Bundle?)
    {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_content)

        supportActionBar?.hide()

        sharedPrefs = SharedPrefs(this)
        scanhere= findViewById(R.id.scanHere)
        profile_image = findViewById(R.id.Profile_Image)



        findViewById<CardView>(R.id.cardView).setOnClickListener {
            val intent = Intent (this, CheckInActivity::class.java)
            startActivity(intent)
        }

        findViewById<CardView>(R.id.cardView1).setOnClickListener {
            val intent = Intent (this, CheckOutActivity::class.java)
            startActivity(intent)
        }
        scanhere?.setOnClickListener{
            val intent = Intent (this, MainMrzActivity::class.java)
            startActivity(intent)

        }
        profile_image?.setOnClickListener{
            val intent = Intent (this, ProfileActivity::class.java)
            startActivity(intent)

        }
        val time = Calendar.getInstance().time
        val formatter = SimpleDateFormat("HH:mm")
        val current = formatter.format(time)
        findViewById<TextView>(R.id.time).text = current
        findViewById<TextView>(R.id.tenantName)
//        findViewById<TextView>(R.id.tenantName).text = sharedPrefs!!.getItem("tenantName")
    }
}