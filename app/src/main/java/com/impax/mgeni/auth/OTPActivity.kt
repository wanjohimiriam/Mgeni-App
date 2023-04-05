package com.impax.mgeni.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.PhoneAuthProvider.ForceResendingToken
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks
import com.impax.mgeni.R
import com.impax.mgeni.commons.SharedPrefs
import java.util.concurrent.TimeUnit

class OTPActivity : AppCompatActivity()
{
    //It is the verification id that will be sent to the user
    private var mVerificationId: String? = null
    var userEmail : String?=null
    //The edittext to input the code
    private val editTextCode: EditText
    get() = findViewById(R.id.editTextCode)
    private val btnContinue: Button
    get() = findViewById(R.id.btnContinue)
    private var mAuth: FirebaseAuth? = null
    var sharedPrefs: SharedPrefs? = null
    var context: Context? = null
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otpactivity)

        context = this
        sharedPrefs = SharedPrefs(this)

        //initializing objects
        mAuth = FirebaseAuth.getInstance()

        //get phone number
        //getting mobile number from the previous activity  and sending the verification code to the number
        val intent = intent
        val mobile = intent.getStringExtra("Phone")
        userEmail= intent.getStringExtra("Email")
        Log.d("Phone", mobile!!)
        sendVerificationCode(mobile)

        //manually
        btnContinue.setOnClickListener{
            if (editTextCode.text.toString().isEmpty() || editTextCode.text.toString().length < 6)
            {
                editTextCode.error = "Enter valid code"
                editTextCode.requestFocus()
            }

            //verifying the code entered manually
            verifyVerificationCode(editTextCode.text.toString())
        }
    }

    private fun sendVerificationCode(mobile: String)
    {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            mobile,
            60,
            TimeUnit.SECONDS,
            this@OTPActivity,
            mCallbacks
        )

    }

    //the callback to detect the verification status
    private val mCallbacks: OnVerificationStateChangedCallbacks =
        object : OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {

                //Getting the code sent by SMS
                val code = phoneAuthCredential.smsCode

                //sometime the code is not detected automatically
                //in this case the code will be null
                //so user has to manually enter the code
                if (code != null) {
                    editTextCode.setText(code)
                    //verifying the code
                    verifyVerificationCode(code)
                }
            }

            override fun onVerificationFailed(e: FirebaseException) {
                Toast.makeText(this@OTPActivity, e.message, Toast.LENGTH_LONG).show()
            }

            override fun onCodeSent(s: String, forceResendingToken: ForceResendingToken) {
                super.onCodeSent(s, forceResendingToken)

                //storing the verification id that is sent to the user
                mVerificationId = s
            }
        }

    private fun verifyVerificationCode(code: String)
    {

        //creating the credential
        val credential = PhoneAuthProvider.getCredential(mVerificationId!!, code)

        //signing the user
        signInWithPhoneAuthCredential(credential)
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential)
    {
        mAuth!!.signInWithCredential(credential)
            .addOnCompleteListener(
                this@OTPActivity
            ) { task ->
                if (task.isSuccessful) {
                    //verification successful we will start the profile activity
                    val intent = Intent(this, PasswordActivity::class.java)
                    intent.putExtra("otp",editTextCode.text.toString().trim())
                    intent.putExtra("Email",userEmail)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                } else {

                    //verification unsuccessful.. display an error message
                    var message = "Something is wrong, we will fix it soon..."
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        message = "Invalid code entered..."
                    }
                    val snackbar = Snackbar.make(
                        findViewById(R.id.editTextCode),
                        message!!, Snackbar.LENGTH_LONG
                    )
                    snackbar.setAction("Dismiss") { }
                    snackbar.show()
                }
            }
    }

}