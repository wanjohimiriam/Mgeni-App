package com.impax.mgeni.home

import android.content.Context
import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.impax.mgeni.MainActivity
import com.impax.mgeni.R
import com.impax.mgeni.checkin.CheckInActivity
import com.impax.mgeni.checkout.CheckOutActivity
import com.impax.mgeni.commons.SharedPrefs
import com.impax.mgeni.models.visitorView
import com.impax.mgeni.ocr.MainMrzActivity
import com.impax.mgeni.profile.ProfileActivity
import com.impax.mgeni.retrofit.RetrofitInstance
import com.impax.mgeni.retrofit.ServiceInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*
//import kotlin.coroutines.jvm.internal.CompletedContinuation.context

//import kotlin.coroutines.jvm.internal.CompletedContinuation.context

class HomePage : AppCompatActivity()
{
    private var scanhere: CardView ?= null
    private var profile_image: ImageButton ?= null
    private var sharedPrefs: SharedPrefs? = null
    private lateinit var recyclerview: RecyclerView
    private lateinit var checkIns: TextView
    private lateinit var checkOuts: TextView
    private lateinit var textViewName:TextView
    private lateinit var visitorsList: ArrayList<visitorView>
    private lateinit var adapter:VisitorsAdapter
    private var todaysCheckIns = 0
    private var todaysCheckOuts = 0


    override fun onCreate(savedInstanceState: Bundle?)
    {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_content)
        visitorsList= ArrayList()


        supportActionBar?.hide()

        sharedPrefs = SharedPrefs(this)
        scanhere= findViewById(R.id.cardView3)
        profile_image = findViewById(R.id.Profile_Image)
        recyclerview = findViewById(R.id.listData)
//        checkIns = findViewById(R.id.totalCheckins)
//        checkOuts = findViewById(R.id.todayCheckouts)
        textViewName =findViewById(R.id.textView)

        //textViewName.text = "JJ"

        //on profile button click

        //get Todays visits
//        getTodayCheckIns()
//        getTodayCheckOuts()
//
        loadAdapter()

        findViewById<CardView>(R.id.cardView).setOnClickListener {
            val intent = Intent (this, CheckInActivity::class.java)
            startActivity(intent)
        }

        findViewById<CardView>(R.id.cardView2).setOnClickListener {
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

    private fun loadAdapter()
    {
        val api = RetrofitInstance.getRetrofitInstance().create(ServiceInterface::class.java)
        api.getCheckInData(sharedPrefs!!.getItem("tenantId")).enqueue(object :
            Callback<List<visitorView>> {
            override fun onResponse(call: Call<List<visitorView>>, response: Response<List<visitorView>>) {
                if (response.isSuccessful)
                {
                    val dataList = response.body()
                    if (dataList != null)
                    {
                        for (i in dataList.indices) {
                            Log.d("name", dataList.size.toString() + "")
                            adapter = VisitorsAdapter(dataList)
                            val manager = LinearLayoutManager(this@HomePage)
                            recyclerview.layoutManager = manager
                            recyclerview.adapter = adapter
                        }
                    }
                } else {
                    // Handle error
                    Toast.makeText(this@HomePage, "An error", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<List<visitorView>>, t: Throwable) {
                // Handle failure
                Toast.makeText(this@HomePage, "An error has occurred", Toast.LENGTH_LONG).show()
                Log.d("TAG", "Response = $t")
            }
        })
    }
//
//    private fun getTodayCheckOuts()
//    {
//        val retIn = RetrofitInstance.getRetrofitInstance().create(ServiceInterface::class.java)
//        retIn.getCheckOutData(sharedPrefs!!.getItem("tenantId")).enqueue(object :
//            Callback<List<visitorView>> {
//            override fun onResponse(call: Call<List<visitorView>>, response: Response<List<visitorView>>) {
//                if (response.isSuccessful)
//                {
//                    val dataList = response.body()
//                    if (dataList != null)
//                    {
//                        for (i in dataList.indices)
//                        {
//                            Log.d("checkOut", dataList.size.toString() + "")
//                            todaysCheckOuts=dataList.size
//
//                            checkOuts.text = todaysCheckOuts.toString()
//
//                        }
//                    }
//                } else {
//                    // Handle error
//                    Toast.makeText(context, "An error", Toast.LENGTH_LONG).show()
//                }
//            }
//
//            override fun onFailure(call: Call<List<visitorView>>, t: Throwable) {
//                // Handle failure
//                Toast.makeText(context, "An error has occurred", Toast.LENGTH_LONG).show()
//                Log.d("TAG", "Response = $t")
//            }
//        })
//    }
//
//    private fun getTodayCheckIns()
//    {
//        val retIn = RetrofitInstance.getRetrofitInstance().create(ServiceInterface::class.java)
//        retIn.getCheckInData(sharedPrefs!!.getItem("tenantId")).enqueue(object :
//            Callback<List<visitorView>> {
//            override fun onResponse(call: Call<List<visitorView>>, response: Response<List<visitorView>>) {
//                if (response.isSuccessful)
//                {
//                    val dataList = response.body()
//                    if (dataList != null)
//                    {
//                        for (i in dataList.indices)
//                        {
//                            Log.d("checkIn", dataList.size.toString() + "")
//                            todaysCheckIns=dataList.size
//
//                            checkIns.text = todaysCheckIns.toString()
//
//                        }
//                    }
//                } else {
//                    // Handle error
//                    Toast.makeText(context, "An error", Toast.LENGTH_LONG).show()
//                }
//            }
//
//            override fun onFailure(call: Call<List<visitorView>>, t: Throwable) {
//                // Handle failure
//                Toast.makeText(context, "An error has occurred", Toast.LENGTH_LONG).show()
//                Log.d("TAG", "Response = $t")
//            }
//        })
//    }
}