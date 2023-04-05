package com.impax.mgeni.checkout

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.net.ParseException
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.impax.mgeni.MainActivity
import com.impax.mgeni.R
import com.impax.mgeni.models.CheckinDetails
import com.impax.mgeni.models.checkOut
import com.impax.mgeni.models.visitorView
import com.impax.mgeni.retrofit.RetrofitInstance
import com.impax.mgeni.retrofit.ServiceInterface
import okhttp3.MediaType
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class CheckoutFragment : Fragment()
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle? ): View?
    {
        // Inflate the layout for this fragment
        var view: View = inflater.inflate(R.layout.fragment_checkout, container, false)


        //
        idTextView = view.findViewById<TextInputLayout>(R.id.IdTextField)
        idTextValue = view.findViewById(R.id.Id_text)

        nameTextView = view.findViewById<TextInputLayout>(R.id.NameTextField)
        nameTextValue = view.findViewById<TextInputEditText>(R.id.name_text)

        hostDepartmentTextView = view.findViewById<TextInputLayout>(R.id.hostDepartmentTextField)
        hostDepartmentTextValue = view.findViewById<TextInputEditText>(R.id.hostDepartment_text)

        hostNameTextView = view.findViewById<TextInputLayout>(R.id.hostNameTextField)
        hostNameTextValue = view.findViewById<TextInputEditText>(R.id.hostName_text)

        checkInTimeTextView = view.findViewById<TextInputLayout>(R.id.checkInTimeTextField)
        checkInTimeTextValue = view.findViewById<TextInputEditText>(R.id.checkInTime_text)


        //On Check Out
        view.findViewById<Button>(R.id.checkOutBtn).setOnClickListener {

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
        view.findViewById<Button>(R.id.cancelBtn).setOnClickListener{

            val intent = Intent (requireContext(), MainActivity::class.java)
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

        return view
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
                        requireContext(),
                        "Checkout Successfull",
                        Toast.LENGTH_SHORT
                    ).show()

                    val intent = Intent (requireContext(), MainActivity::class.java)
                    startActivity(intent)
                }
            }

            override fun onFailure(call: Call<CheckinDetails>, t: Throwable) {
                Toast.makeText(
                    requireContext(),
                    t.message,
                    Toast.LENGTH_SHORT
                ).show()
            }

        })
    }


    fun convertPlainString(data: String?): RequestBody?
    {
        return RequestBody.create(MediaType.parse("text/plain"), data)
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
                    requireContext(),
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