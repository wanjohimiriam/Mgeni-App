package com.impax.mgeni.auth

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import android.window.OnBackInvokedDispatcher
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.impax.mgeni.MainActivity
import com.impax.mgeni.R
import com.impax.mgeni.R.*
import com.impax.mgeni.commons.SharedPrefs
import com.impax.mgeni.home.HomePage
import com.impax.mgeni.models.User_Model
import com.impax.mgeni.models.users
import com.impax.mgeni.retrofit.RetrofitInstance
import com.impax.mgeni.retrofit.ServiceInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Array.get

class LoginActivity : AppCompatActivity() {
    var sharedPrefs: SharedPrefs? = null
    var context: Context? = null
    var userPhone: String? = null
    var userEmail: String? = null
    var userPassword: String? = null

    private val textPhone: TextView
        get() = findViewById(id.phoneLabel)

    private val tablayout: TabLayout
        get() = findViewById(id.tabs_layout)
    private val viewpager: ViewPager
        get() = findViewById(id.viewpager)

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_login)


        context = this
        sharedPrefs = SharedPrefs(this)

        //checkAuth()

        val textEmail = findViewById<TextInputLayout>(id.usernameTextField)
        val textEmailAddress = findViewById<TextInputEditText>(id.emailAddress)
        val textPassword = findViewById<TextInputLayout>(id.PasswordTextField)
//        val viewPager = findViewById<TextInputEditText>(id.viewpager)
//        val tabLayout = findViewById<TextInputEditText>(id.tabs_layout)
        //val textPhone = findViewById<TextInputLayout>(R.id.PhoneTextField)
        // val resetPassword = findViewById<TextView>(R.id.resetPass)

        tablayout.addTab(tablayout.newTab().setText("Login"));
        tablayout.addTab(tablayout.newTab().setText("SignUp"));
        tablayout.tabGravity = TabLayout.GRAVITY_FILL;


        // Assuming you have already initialized 'viewpager' and 'tablayout' objects.

        val adapter = LoginAdapter(supportFragmentManager, this, tablayout.tabCount)
        viewpager.adapter = adapter;

        viewpager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tablayout))
        fun onBackPressed() {
            super.onBackPressed()
        }

    }
}


















//        viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
//
//            override fun onPageScrollStateChanged(state: Int) {
//            }
//
//            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
//
//            }
//
//            override fun onPageSelected(position: Int) {
//            }
//        })


//        btnOTP.isVisible=false


//        textEmailAddress.addTextChangedListener(object :
//            TextWatcher
//        {
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int)
//            {
//            }
//
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//            }
//
//            override fun afterTextChanged(s: Editable?)
//            {
//                //get the check In Details
//                if (s != null)
//                {
//                    if (s.isNotEmpty())
//                    {
//
//                        //loadUserDetails(s.toString())
//                    }
//                }
//            }
//
//        })
        // On OTP button Click
//        btnOTP.setOnClickListener {
//            val i = Intent(this, OTPActivity::class.java)
//            i.putExtra("Phone",userPhone)
//            i.putExtra("Email",userEmail)
//            startActivity(i)
//        }

//         On Login button Click
//        btnLogin.setOnClickListener{
//            if (textEmail.editText?.text.toString().isEmpty() || textPassword.editText?.text.toString().isEmpty()
//            ) {
//                Toast.makeText(
//                    this,
//                    "Email or Password missing,Please try again",
//                    Toast.LENGTH_SHORT
//                ).show()
//            }else {
//////                 login(
////                    textEmail.editText?.text.toString(),
////                    textPassword.editText?.text.toString()
////                )
//            }
//        }
//
//
//    }

//    private fun checkAuth()
//    {
//        if ((!sharedPrefs!!.getItem("loggedIn").isNullOrBlank()) && (!sharedPrefs!!.getItem("email") .isNullOrBlank()) )
//        {
//          //  val i = Intent(this, MainActivity::class.java)
//            val i =Intent(this, HomePage::class.java)
//            startActivity(i)
//        }/*else
////        {
////            val i = Intent(this, MainActivity::class.java)
////            startActivity(i)
////        }*/
//    }

//    private fun loadUserDetails(email: String)
//    {
//        val retIn = RetrofitInstance.getRetrofitInstance().create(ServiceInterface::class.java)
//        retIn.getUsersByEmail(email)?.enqueue(object : Callback<users>
//        {
//            override fun onResponse(call: Call<users>, response: Response<users>)
//            {
//                Log.d("resp",response.message())
//                Log.d("otp",""+response.body()?.otp)
//                Log.d("number",""+response.body()?.phone_number)
//                 userPhone = response.body()?.phone_number
//                userEmail=response.body()?.email
//                if (response.isSuccessful)
//                {
//                    if(response.body()?.otp.isNullOrEmpty())
//                    {
//                        btnLogin.isVisible=false
//                        btnOTP.isVisible=true
//                        textPhone.isVisible=true
//                        cardPhone.isVisible=true
//                        cardPassword.isVisible=false
//                        textPass.isVisible=false
//                        resetPassword.isVisible=false
//
//                        textPhoneText.setText(userPhone)
//
//
//                    }else
//                    {
//
//                        cardPassword.isVisible=true
//                        textPass.isVisible=true
//                        textPhone.isVisible=false
//                        cardPhone.isVisible=false
//                        btnOTP.isVisible=false
//                        btnLogin.isVisible=true
//                        resetPassword.isVisible=true
//                    }
//
//                }
//                else {
//                    Toast.makeText(context, "Incorrect Email Address, please contact your Admin", Toast.LENGTH_SHORT)
//                        .show()
//
//                }
//            }
//
//            override fun onFailure(call: Call<users>, t: Throwable)
//            {
//                Toast.makeText(
//                    context,
//                    t.message,
//                    Toast.LENGTH_SHORT
//                ).show()
//            }
//
//        })
//    }
//
//    private fun login(email: String, password: String)
//    {
//        val retIn = RetrofitInstance.getRetrofitInstance().create(ServiceInterface::class.java)
//        val call: Call<User_Model>? = retIn.login(email, password)
//        call!!.enqueue(object : Callback<User_Model> {
//            override fun onResponse(call: Call<User_Model>, response: Response<User_Model>) {
//                Log.d("response456", response.message())
//                if (response.body() != null)
//                {
//                    val intent = Intent(context, HomePage::class.java)
//                    sharedPrefs?.putItem("userId", response.body()!!.userId)
//                    sharedPrefs?.putItem("tenantId", response.body()!!.tenantId)
//                    sharedPrefs?.putItem("tenantName", response.body()!!.tenantName)
//                    sharedPrefs?.putItem("email", email)
//                    sharedPrefs?.putItem("loggedIn", "loggedIn")
//                    startActivity(intent)
//                } else {
//                    Toast.makeText(context,"Login credentials not correct,Please try again",Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            override fun onFailure(call: Call<User_Model>, t: Throwable)
//            {
//                Log.d("LoginRes", t.localizedMessage)
//                Toast.makeText(context,"Login credentials not correct,Please try again",Toast.LENGTH_SHORT).show();
//            }
//        })
//
//    }



