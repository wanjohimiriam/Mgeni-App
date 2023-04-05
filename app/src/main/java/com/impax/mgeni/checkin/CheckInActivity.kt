package com.impax.mgeni.checkin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.impax.mgeni.MainActivity
import com.impax.mgeni.R
import com.impax.mgeni.commons.SharedPrefs
import com.impax.mgeni.home.HomePage
import com.impax.mgeni.models.CheckinDetails
import com.impax.mgeni.models.Visitor_details
import com.impax.mgeni.models.visitorView
import com.impax.mgeni.ocr.OCRActivity
import com.impax.mgeni.retrofit.RetrofitInstance
import com.impax.mgeni.retrofit.ServiceInterface
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CheckInActivity : AppCompatActivity()
{
    var visitor_name: String? = null
    var visitor_phoneNumber : String?=null
    var visitor_company: String? = null
    var idNumber: String? = null
    var phone : String?=null
    var visitorType: String? = null
    var checkInType: String? = null
    var assetName : String?=null
    var name: String? = null
    var assetBarcode: String? = null
    var assetSerial: String?=null
    var company: String? = null
    var hostName: String?=null
    var hostDepart: String? = null
    var visitReason: String?=null
    var tenantId: String?=null
    var userId: String?=null
    var idNo: String?=null
    var names: String?=null

    lateinit var nameTextValue: TextInputEditText
    lateinit var phoneTextValue: TextInputEditText
    lateinit var companyTextValue: TextInputEditText
    lateinit var idTextValue: TextInputEditText
    lateinit var assetNameTextValue: TextInputEditText
    lateinit var visitorTextValue: AutoCompleteTextView
    lateinit var checkModeTextValue: AutoCompleteTextView
    lateinit var assetBarcodeTextValue: TextInputEditText
    lateinit var assetSerialTextValue: TextInputEditText
    lateinit var hostDepartmentTextValue: TextInputEditText
    lateinit var hostNameTextValue: TextInputEditText
    lateinit var reasonTextValue: AutoCompleteTextView

    lateinit var idTextView: TextInputLayout
    lateinit var nameTextView: TextInputLayout
    lateinit var phoneTextView: TextInputLayout
    lateinit var visitorTextView: TextInputLayout
    lateinit var checkModeTextView: TextInputLayout
    lateinit var assetNameTextView: TextInputLayout
    lateinit var assetBarcodeTextView: TextInputLayout
    lateinit var assetSerialTextView: TextInputLayout
    lateinit var companyTextView: TextInputLayout
    lateinit var hostDepartmentTextView: TextInputLayout
    lateinit var hostNameTextView: TextInputLayout
    lateinit var reasonTextView: TextInputLayout

    var sharedPrefs: SharedPrefs? = null
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_in)

        sharedPrefs = SharedPrefs(this)
        tenantId = sharedPrefs!!.getItem("tenantId")
        userId = sharedPrefs!!.getItem("userId")

        //check if names and id are not null
        idNo= sharedPrefs!!.getItem("IdNo")
        names= sharedPrefs!!.getItem("Names")

        /*val bundle = arguments
        idNo = bundle!!.getString("IdNo")
        names = bundle!!.getString("Names")*/

        Log.d("test",idNo+""+names);

        supportActionBar?.hide()


        // Get the views
        idTextView = findViewById(R.id.IdTextField)
        idTextValue = findViewById(R.id.Id_text)

        nameTextView = findViewById(R.id.NameTextField)
        nameTextValue = findViewById(R.id.name_text)


        phoneTextView = findViewById(R.id.PhoneTextField)
        phoneTextValue = findViewById(R.id.phone_text)

        visitorTextView = findViewById(R.id.VisitorTypeTextField)
        visitorTextValue = findViewById(R.id.VisitorType_text)

        checkModeTextView = findViewById(R.id.CheckInTypeTextField)
        checkModeTextValue = findViewById(R.id.CheckInType_text)

        assetNameTextView = findViewById(R.id.AssetNameTextField)
        assetNameTextValue = findViewById(R.id.AssetName_text)

        assetBarcodeTextView = findViewById(R.id.assetBarcodeTextField)
        assetBarcodeTextValue = findViewById(R.id.assetBarcode_text)

        assetSerialTextView = findViewById(R.id.SerialNoTextField)
        assetSerialTextValue = findViewById(R.id.serialNo_text)

        companyTextView = findViewById(R.id.companyTextField)
        companyTextValue = findViewById(R.id.company_text)

        hostDepartmentTextView = findViewById(R.id.hostDepartmentTextField)
        hostDepartmentTextValue = findViewById(R.id.hostDepartment_text)

        hostNameTextView = findViewById(R.id.hostNameTextField)
        hostNameTextValue = findViewById(R.id.hostName_text)

        reasonTextView = findViewById(R.id.reasonTextField)
        reasonTextValue = findViewById(R.id.reason_text)

        val visitorTypes = resources.getStringArray(R.array.visitor_types)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, visitorTypes)
        visitorTextValue.setAdapter(adapter)


        val checkInMode = resources.getStringArray(R.array.visit_reason)
        val checkInAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, checkInMode)
        checkModeTextValue.setAdapter(checkInAdapter)


        val reason = resources.getStringArray(R.array.checkin_types)
        val reasonAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, reason)
        reasonTextValue.setAdapter(reasonAdapter)


        //check if names && idNo are null
        if(idNo.isNullOrEmpty() || names.isNullOrEmpty())
        {
            idTextValue.setText("")
            nameTextValue.setText("")
        }else
        {
            idTextValue.setText(idNo)
            nameTextValue.setText(names)

            //
            loadCheckInDetails(idNo.toString())
        }



        //on Check In
        findViewById<Button>(R.id.checkInBtn).setOnClickListener {
            // Getting the user input
            idNumber = idTextView.editText?.text?.toString() ?: ""
            name     = nameTextView.editText?.text?.toString() ?: ""
            phone = phoneTextView.editText?.text?.toString() ?: ""
            visitorType = visitorTextValue.text?.toString() ?: ""
            checkInType = checkModeTextValue.text?.toString() ?: ""
            assetName=assetNameTextView.editText?.text?.toString() ?: ""
            assetBarcode=assetBarcodeTextView.editText?.text?.toString() ?: ""
            assetSerial=assetSerialTextView.editText?.text?.toString() ?: ""
            company=companyTextView.editText?.text?.toString() ?: ""
            hostDepart=hostDepartmentTextView.editText?.text?.toString() ?: ""
            hostName=hostNameTextView.editText?.text?.toString() ?: ""
            visitReason = reasonTextValue.text?.toString() ?: ""

            if (idNumber!!.isEmpty() || idNumber == "")
            {
                idTextValue.requestFocus()
                idTextValue.error = "ID Number can not be empty"
            }else
            {
                if(name!!.isEmpty() || name=="")
                {
                    nameTextValue.requestFocus()
                    nameTextValue.error="Name can not be empty"
                }else
                {
                    checkInVisitor(idNumber!!,
                        name!!,
                        phone!!,
                        visitorType!!,
                        checkInType!!,
                        assetName!!,
                        assetBarcode!!,
                        assetSerial!!,
                        company!!,
                        hostDepart!!,
                        hostName!!,
                        visitReason!!
                    )
                }
            }


        }


        //On Print Card
        findViewById<Button>(R.id.printBtn).setOnClickListener {
            printCard()
        }


        //On Cancel
        findViewById<Button>(R.id.cancelBtn).setOnClickListener {
            cancelBtn()
        }

        //on capture click
        idTextView.setEndIconOnClickListener {
            val intent = Intent (this, OCRActivity::class.java)
            sharedPrefs?.putItem("state", "checkin")
            startActivity(intent)
        }

        //on id text change
        findViewById<TextInputEditText>(R.id.Id_text).addTextChangedListener(object :
            TextWatcher
        {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int)
            {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(s: Editable?)
            {
                //get the check In Details
                if (s != null)
                {
                    loadCheckInDetails(s.toString())

                }
            }

        })


    }

    private fun checkInVisitor(
        idNumber: String,
        name: String,
        phone: String,
        visitorType: String,
        checkInType: String,
        assetName: String,
        assetBarcode: String,
        assetSerial: String,
        company: String,
        hostDepart: String,
        hostName: String,
        visitReason: String
    )
    {

        val retIn = RetrofitInstance.getRetrofitInstance().create(ServiceInterface::class.java)
        val registerInfo = CheckinDetails(idNumber,visitorType,checkInType,assetName,assetBarcode,assetSerial,hostDepart,hostName,visitReason,tenantId.toString(),userId.toString())

        retIn.CheckinUser(registerInfo).enqueue(object :
            Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(
                    this@CheckInActivity,
                    t.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>)
            {
                Log.d("resp",response.message())
                if (response.isSuccessful)
                {
                         //save visitor details


                    if (sharedPrefs?.getItem("state").equals("insert"))
                    {

                        val visitorInfo = Visitor_details(idNumber, phone, company, name, userId.toString(),
                            tenantId.toString()
                        )
                        retIn.saveVisitor(visitorInfo).enqueue(object :
                            Callback<ResponseBody> {
                            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                                Toast.makeText(
                                    this@CheckInActivity,
                                    t.message,
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

                            override fun onResponse(
                                call: Call<ResponseBody>,
                                response: Response<ResponseBody>
                            ) {
                                Log.d("resp", response.message())
                                if (response.isSuccessful)
                                {
                                    Toast.makeText(this@CheckInActivity,"Checkin success!", Toast.LENGTH_SHORT).show()
                                    val intent = Intent (this@CheckInActivity, HomePage::class.java)
                                    //remove shared Prefs
                                    sharedPrefs!!.removeItem("IdNo")
                                    sharedPrefs!!.removeItem("Names")
                                    startActivity(intent)
                                }
                                else {
                                    Toast.makeText(
                                        this@CheckInActivity,
                                        "Checkin failed!",
                                        Toast.LENGTH_SHORT
                                    )
                                        .show()
                                }
                            }
                        })

                    }else if(sharedPrefs?.getItem("state").equals("update"))
                    {
                        Toast.makeText(this@CheckInActivity,"Checkin success!", Toast.LENGTH_SHORT).show()
                        val intent = Intent (this@CheckInActivity, HomePage::class.java)
                        //remove shared Prefs
                        sharedPrefs!!.removeItem("IdNo")
                        sharedPrefs!!.removeItem("Names")
                        startActivity(intent)
                    }

                }
                else{
                    Toast.makeText(this@CheckInActivity, "Checkin failed!", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })

    }

    private fun cancelBtn()
    {
        val intent = Intent (this, HomePage::class.java)
        startActivity(intent)

    }

    private fun printCard()
    {

    }


    private fun loadCheckInDetails(idNumber: String)
    {
        val retIn = RetrofitInstance.getRetrofitInstance().create(ServiceInterface::class.java)
        retIn.getVisitorsById(idNumber).enqueue(object : Callback<visitorView>
        {
            override fun onResponse(call: Call<visitorView>, response: Response<visitorView>)
            {
                Log.d("resp",response.message())
                if (response.isSuccessful)
                {
                    sharedPrefs?.putItem("state","update")

                    visitor_name= response.body()?.name
                    visitor_company=response.body()?.company
                    visitor_phoneNumber=response.body()?.phoneNumber

                    //set Values to textviews
                    nameTextValue.setText(visitor_name)
                    phoneTextValue.setText(visitor_phoneNumber)
                    companyTextValue.setText(visitor_company)



                }else
                {
                    //Save Visitor Details
                    sharedPrefs?.putItem("state","insert")

                }

            }

            override fun onFailure(call: Call<visitorView>, t: Throwable)
            {
                Toast.makeText(
                    this@CheckInActivity,
                    t.message,
                    Toast.LENGTH_SHORT
                ).show()
            }

        })
    }




}