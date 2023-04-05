package com.impax.mgeni.checkin

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.impax.mgeni.MainActivity
import com.impax.mgeni.R
import com.impax.mgeni.auth.OTPActivity
import com.impax.mgeni.commons.SharedPrefs
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

class CheckinFragment : Fragment()
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
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View?
    {
        sharedPrefs = SharedPrefs(requireActivity())
        tenantId = sharedPrefs!!.getItem("tenantId")
        userId = sharedPrefs!!.getItem("userId")

        //check if names and id are not null
        idNo= sharedPrefs!!.getItem("IdNo")
        names= sharedPrefs!!.getItem("Names")

        /*val bundle = arguments
        idNo = bundle!!.getString("IdNo")
        names = bundle!!.getString("Names")*/

        //check if names && idNo are null
        if(idNo.isNullOrBlank() || names.isNullOrBlank())
        {
            idNumber=""
            name=""
        }

        // Inflate the layout for this fragment
        var view: View = inflater.inflate(R.layout.fragment_checkin, container, false)


        // Get the views
         idTextView = view.findViewById(R.id.IdTextField)
         idTextValue = view.findViewById(R.id.Id_text)

        nameTextView = view.findViewById(R.id.NameTextField)
        nameTextValue = view.findViewById(R.id.name_text)


         phoneTextView = view.findViewById(R.id.PhoneTextField)
        phoneTextValue = view.findViewById(R.id.phone_text)

         visitorTextView = view.findViewById(R.id.VisitorTypeTextField)
         visitorTextValue = view.findViewById(R.id.VisitorType_text)

         checkModeTextView = view.findViewById(R.id.CheckInTypeTextField)
         checkModeTextValue = view.findViewById(R.id.CheckInType_text)

         assetNameTextView = view.findViewById<TextInputLayout>(R.id.AssetNameTextField)
         assetNameTextValue = view.findViewById<TextInputEditText>(R.id.AssetName_text)

         assetBarcodeTextView = view.findViewById<TextInputLayout>(R.id.assetBarcodeTextField)
         assetBarcodeTextValue = view.findViewById<TextInputEditText>(R.id.assetBarcode_text)

         assetSerialTextView = view.findViewById(R.id.SerialNoTextField)
         assetSerialTextValue = view.findViewById(R.id.serialNo_text)

         companyTextView = view.findViewById<TextInputLayout>(R.id.companyTextField)
        companyTextValue = view.findViewById<TextInputEditText>(R.id.company_text)

         hostDepartmentTextView = view.findViewById<TextInputLayout>(R.id.hostDepartmentTextField)
         hostDepartmentTextValue = view.findViewById<TextInputEditText>(R.id.hostDepartment_text)

         hostNameTextView = view.findViewById<TextInputLayout>(R.id.hostNameTextField)
         hostNameTextValue = view.findViewById<TextInputEditText>(R.id.hostName_text)

         reasonTextView = view.findViewById<TextInputLayout>(R.id.reasonTextField)
         reasonTextValue = view.findViewById<AutoCompleteTextView>(R.id.reason_text)

        val visitorTypes = resources.getStringArray(R.array.visitor_types)
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, visitorTypes)
        visitorTextValue.setAdapter(adapter)


        val checkInMode = resources.getStringArray(R.array.visit_reason)
        val checkInAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, checkInMode)
        checkModeTextValue.setAdapter(checkInAdapter)


        val reason = resources.getStringArray(R.array.checkin_types)
        val reasonAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, reason)
        reasonTextValue.setAdapter(reasonAdapter)


        //on Check In
        view.findViewById<AppCompatButton>(R.id.checkInBtn).setOnClickListener {
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
        view.findViewById<AppCompatButton>(R.id.printBtn).setOnClickListener {
            printCard(view)
        }


        //On Cancel
        view.findViewById<AppCompatButton>(R.id.cancelBtn).setOnClickListener {
            cancelBtn(view)
        }

     //on capture click
        idTextView.setEndIconOnClickListener {
            val intent = Intent (requireContext(), OCRActivity::class.java)
            startActivity(intent)
        }

        //on id text change
        view.findViewById<TextInputEditText>(R.id.Id_text).addTextChangedListener(object :
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

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

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
                    requireContext(),
                    t.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>)
            {
                Log.d("resp",response.message())
                if (response.isSuccessful)
                {
                    Toast.makeText(requireContext(), "Checkin success!", Toast.LENGTH_SHORT)
                        .show()

                    //save visitor details


                    if (sharedPrefs?.getItem("state").equals("insert")) {

                        val visitorInfo =Visitor_details(idNumber, phone, company, name, userId.toString(),
                            tenantId.toString()
                        )
                        retIn.saveVisitor(visitorInfo).enqueue(object :
                            Callback<ResponseBody> {
                            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                                Toast.makeText(
                                    requireContext(),
                                    t.message,
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

                            override fun onResponse(
                                call: Call<ResponseBody>,
                                response: Response<ResponseBody>
                            ) {
                                Log.d("resp", response.message())
                                if (response.isSuccessful) {
                                    Toast.makeText(
                                        requireContext(),
                                        "Checkin success!",
                                        Toast.LENGTH_SHORT

                                    )
                                        .show()
                                    val intent = Intent(requireContext(), MainActivity::class.java)
                                    startActivity(intent)
                                } else {
                                    Toast.makeText(
                                        requireContext(),
                                        "Checkin failed!",
                                        Toast.LENGTH_SHORT
                                    )
                                        .show()
                                }
                            }
                        })

                    }

                }
                else{
                    Toast.makeText(requireContext(), "Checkin failed!", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })

    }

    private fun cancelBtn(cancelView: View)
    {
        val intent = Intent (requireContext(), MainActivity::class.java)
        startActivity(intent)

    }

    private fun printCard(printView: View)
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
                    requireContext(),
                    t.message,
                    Toast.LENGTH_SHORT
                ).show()
            }

        })
    }




}