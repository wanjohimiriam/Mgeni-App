package com.impax.mgeni.checkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.core.net.ParseException
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.impax.mgeni.MainActivity
import com.impax.mgeni.R
import com.impax.mgeni.commons.SharedPrefs
import com.impax.mgeni.home.HomePage
import com.impax.mgeni.models.CheckinDetails
import com.impax.mgeni.models.checkOut
import com.impax.mgeni.models.visitorView
import com.impax.mgeni.ocr.OCRActivity
import com.impax.mgeni.retrofit.RetrofitInstance
import com.impax.mgeni.retrofit.ServiceInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class CheckOutActivity : AppCompatActivity()
{
    lateinit var idTextView: TextInputLayout
    lateinit var nameTextView: TextInputLayout
    lateinit var hostDepartmentTextView: TextInputLayout
    lateinit var hostNameTextView: TextInputLayout
    lateinit var checkInTimeTextView: TextInputLayout

    lateinit var idTextValue: TextInputEditText
    lateinit var nameTextValue: TextInputEditText
    lateinit var hostDepartmentTextValue: TextInputEditText
    lateinit var hostNameTextValue: TextInputEditText
    lateinit var checkInTimeTextValue: TextInputEditText

    var visitor_name: String? = null
    var visitor_hostDepart : String?=null
    var visitor_hostName: String? = null
    var visitor_checkIn : String?=null

    var idNo: String?=null
    var names: String?=null
    var sharedPrefs: SharedPrefs? = null
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_out)

        sharedPrefs = SharedPrefs(this)
        idNo= sharedPrefs!!.getItem("IdNo")
        names= sharedPrefs!!.getItem("Names")
        supportActionBar?.hide()

        //
        idTextView = findViewById(R.id.IdTextField)
        idTextValue = findViewById(R.id.Id_text)

        nameTextView = findViewById(R.id.NameTextField)
        nameTextValue = findViewById(R.id.name_text)

        hostDepartmentTextView = findViewById(R.id.hostDepartmentTextField)
        hostDepartmentTextValue = findViewById(R.id.hostDepartment_text)

        hostNameTextView = findViewById(R.id.hostNameTextField)
        hostNameTextValue = findViewById<TextInputEditText>(R.id.hostName_text)

        checkInTimeTextView = findViewById<TextInputLayout>(R.id.checkInTimeTextField)
        checkInTimeTextValue = findViewById<TextInputEditText>(R.id.checkInTime_text)


        //check if names && idNo are null
        if(idNo.isNullOrEmpty() || names.isNullOrEmpty())
        {
            idTextValue.setText("")
            nameTextValue.setText("")
        }else
        {
          //  idTextValue.setText(idNo)
         //   nameTextValue.setText(names)

            //
            loadCheckInDetails(idNo.toString())
        }

        //on capture click
        idTextView.setEndIconOnClickListener {
            val intent = Intent (this, OCRActivity::class.java)
            sharedPrefs?.putItem("state", "checkout")
            startActivity(intent)
        }

        //On Check Out
        findViewById<Button>(R.id.checkOutBtn).setOnClickListener {

            // Getting the user input
            val idNumber = idTextView.editText?.text?.toString() ?: ""
            val name     = nameTextView.editText?.text?.toString() ?: ""
            val hostDepart=hostDepartmentTextView.editText?.text?.toString() ?: ""
            val hostName=hostNameTextView.editText?.text?.toString() ?: ""
            val checkInTime=checkInTimeTextView.editText?.text?.toString() ?: ""

            //Get current Date time
            val formatter =  SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
            val date = Date()
            val checkOutTime = formatter.format(date)

            confirmCheckOut(idNumber,checkOutTime);

        }

        //On Cancel
        findViewById<Button>(R.id.cancelBtn).setOnClickListener{

            val intent = Intent (this, HomePage::class.java)
            startActivity(intent)
        }

        //on id text change
        idTextValue.addTextChangedListener(object : TextWatcher
        {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int)
            {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(s: Editable?)
            {
                // Toast.makeText(requireContext(),s.toString(),Toast.LENGTH_LONG).show()

                //get the check In Details
                if (s != null)
                {
                    loadCheckInDetails(s.toString())
                }
            }

        })
    }


    private fun confirmCheckOut(idNumber: String, checkOutTime: String)
    {

        val retIn = RetrofitInstance.getRetrofitInstance().create(ServiceInterface::class.java)

        val registerInfo = checkOut(idNumber,checkOutTime)
        retIn.checkout(idNumber,checkOutTime)?.enqueue(object : Callback<CheckinDetails>
        {
            override fun onResponse(call: Call<CheckinDetails>, response: Response<CheckinDetails>)
            {
                if (response.isSuccessful) {
                    Toast.makeText(
                        this@CheckOutActivity,
                        "Checkout Successfull",
                        Toast.LENGTH_SHORT
                    ).show()

                    val intent = Intent (this@CheckOutActivity, HomePage::class.java)
                    //remove shared Prefs
                    sharedPrefs!!.removeItem("IdNo")
                    sharedPrefs!!.removeItem("Names")
                    startActivity(intent)
                }
            }

            override fun onFailure(call: Call<CheckinDetails>, t: Throwable) {
                Toast.makeText(
                    this@CheckOutActivity,
                    t.message,
                    Toast.LENGTH_SHORT
                ).show()
            }

        })
    }

    private fun loadCheckInDetails(idNumber: String)
    {
        val retIn = RetrofitInstance.getRetrofitInstance().create(ServiceInterface::class.java)
        retIn.getCheckInsById(idNumber).enqueue(object : Callback<visitorView>
        {
            override fun onResponse(call: Call<visitorView>, response: Response<visitorView>)
            {
                Log.d("resp",response.message())
                if (response.isSuccessful)
                {
                    visitor_name = response.body()?.name
                    visitor_hostDepart= response.body()?.hostDepartment
                    visitor_hostName = response.body()?.hostName
                    visitor_checkIn= response.body()?.checkInTime.toString()

                    val date2 =getDateFromatedString(visitor_checkIn)

                    //set these values to the

                    nameTextValue.setText(visitor_name)
                    hostDepartmentTextValue.setText(visitor_hostDepart)
                    hostNameTextValue.setText(visitor_hostName)
                    checkInTimeTextValue.setText(date2)



                }
                else{
                    /*Toast.makeText(requireContext(), "Checkout failed!", Toast.LENGTH_SHORT)
                        .show()*/
                }
            }

            override fun onFailure(call: Call<visitorView>, t: Throwable)
            {
                Toast.makeText(
                    this@CheckOutActivity,
                    t.message,
                    Toast.LENGTH_SHORT
                ).show()
            }

        })
    }

    fun getDateFromatedString(inputDate: String?): String?
    {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
        var date: Date? = null
        try {
            date = simpleDateFormat.parse(inputDate)
        } catch (e: ParseException) {
            e.printStackTrace()
        } catch (e: java.text.ParseException) {
            e.printStackTrace()
        }
        if (date == null) {
            return ""
        }
        val convetDateFormat = SimpleDateFormat("dd-MM-yyyy hh:mm a")
        return convetDateFormat.format(date)
    }


}