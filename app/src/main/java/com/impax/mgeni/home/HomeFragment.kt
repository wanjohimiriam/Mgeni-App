package com.impax.mgeni.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.impax.mgeni.MainActivity
import com.impax.mgeni.R
import com.impax.mgeni.auth.OTPActivity
import com.impax.mgeni.commons.SharedPrefs
import com.impax.mgeni.models.visitorView
import com.impax.mgeni.profile.ProfileActivity
import com.impax.mgeni.retrofit.RetrofitInstance
import com.impax.mgeni.retrofit.ServiceInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment()
{
    private lateinit var checkIns: TextView
    private lateinit var checkOuts: TextView
    private lateinit var textViewName:TextView
    private lateinit var listBtn: ImageButton
    private lateinit var profileBtn: ImageButton
    private lateinit var recyclerview: RecyclerView
    private var sharedPrefs: SharedPrefs? = null
    private lateinit var visitorsList: ArrayList<visitorView>
    private lateinit var adapter:VisitorsAdapter
    private var todaysCheckIns = 0
    private var todaysCheckOuts = 0
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
    {
        sharedPrefs = SharedPrefs(requireActivity())
        visitorsList= ArrayList()
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)

        Log.d("shared",sharedPrefs!!.getItem("loggedIn") + sharedPrefs!!.getItem("email"))

        // getting the views by its id
//         recyclerview = view.findViewById(R.id.listData)
         checkIns = view.findViewById(R.id.totalCheckins)
         checkOuts = view.findViewById(R.id.todayCheckouts)
         listBtn = view.findViewById(R.id.backB)
         profileBtn = view.findViewById(R.id.profileB)
        textViewName =view.findViewById(R.id.textView)

        textViewName.text = "JJ"

        //on profile button click
        profileBtn.setOnClickListener{
            val i = Intent (requireContext(), ProfileActivity::class.java)
            startActivity(i)
        }

        //get Todays visits
        getTodayCheckIns()
        getTodayCheckOuts()

        loadAdapter()

        return view
    }

    private fun loadAdapter()
    {
        val api = RetrofitInstance.getRetrofitInstance().create(ServiceInterface::class.java)
        api.getCheckInData(sharedPrefs!!.getItem("tenantId")).enqueue(object : Callback<List<visitorView>> {
            override fun onResponse(call: Call<List<visitorView>>, response: Response<List<visitorView>>) {
                if (response.isSuccessful)
                {
                    val dataList = response.body()
                    if (dataList != null)
                    {
                        for (i in dataList.indices) {
                            Log.d("name", dataList.size.toString() + "")
                            adapter = VisitorsAdapter(dataList)
                            val manager = LinearLayoutManager(requireContext())
                            recyclerview.layoutManager = manager
                            recyclerview.adapter = adapter
                        }
                    }
                } else {
                    // Handle error
                    Toast.makeText(context, "An error", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<List<visitorView>>, t: Throwable) {
                // Handle failure
                Toast.makeText(context, "An error has occurred", Toast.LENGTH_LONG).show()
                Log.d("TAG", "Response = $t")
            }
        })
    }

    private fun getTodayCheckOuts()
    {
        val retIn = RetrofitInstance.getRetrofitInstance().create(ServiceInterface::class.java)
        retIn.getCheckOutData(sharedPrefs!!.getItem("tenantId")).enqueue(object : Callback<List<visitorView>> {
            override fun onResponse(call: Call<List<visitorView>>, response: Response<List<visitorView>>) {
                if (response.isSuccessful)
                {
                    val dataList = response.body()
                    if (dataList != null)
                    {
                        for (i in dataList.indices)
                        {
                            Log.d("checkOut", dataList.size.toString() + "")
                            todaysCheckOuts=dataList.size

                            checkOuts.text = todaysCheckOuts.toString()

                        }
                    }
                } else {
                    // Handle error
                    Toast.makeText(context, "An error", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<List<visitorView>>, t: Throwable) {
                // Handle failure
                Toast.makeText(context, "An error has occurred", Toast.LENGTH_LONG).show()
                Log.d("TAG", "Response = $t")
            }
        })
    }

    private fun getTodayCheckIns()
    {
        val retIn = RetrofitInstance.getRetrofitInstance().create(ServiceInterface::class.java)
        retIn.getCheckInData(sharedPrefs!!.getItem("tenantId")).enqueue(object : Callback<List<visitorView>> {
            override fun onResponse(call: Call<List<visitorView>>, response: Response<List<visitorView>>) {
                if (response.isSuccessful)
                {
                    val dataList = response.body()
                    if (dataList != null)
                    {
                        for (i in dataList.indices)
                        {
                            Log.d("checkIn", dataList.size.toString() + "")
                            todaysCheckIns=dataList.size

                            checkIns.text = todaysCheckIns.toString()

                        }
                    }
                } else {
                    // Handle error
                    Toast.makeText(context, "An error", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<List<visitorView>>, t: Throwable) {
                // Handle failure
                Toast.makeText(requireActivity(), "An error has occurred", Toast.LENGTH_LONG).show()
                Log.d("TAG", "Response = $t")
            }
        })
    }


}