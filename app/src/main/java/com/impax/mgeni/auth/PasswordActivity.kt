package com.impax.mgeni.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import com.impax.mgeni.R
import com.impax.mgeni.models.users
import com.impax.mgeni.retrofit.RetrofitInstance
import com.impax.mgeni.retrofit.ServiceInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PasswordActivity : AppCompatActivity()
{
    private val password: TextInputLayout
    get() = findViewById(R.id.passwordTextField)
    private val confirmPassword: TextInputLayout
    get() = findViewById(R.id.confirmPassTextField)
    private val resetPassword: Button
    get() = findViewById(R.id.resetPassBtn)
    var context: Context? = null
    var email : String?=null
    var otp : String?=null
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password)

        context=this
        val intent = intent
        email = intent.getStringExtra("Email")
        otp = intent.getStringExtra("otp")
        //reset Password
        resetPassword.setOnClickListener {
            if (password.editText!!.text.toString() == confirmPassword.editText!!.text.toString()) {
                //reset password
                updatePassword(email, password.editText!!.text.toString(), otp)
            } else {
                Toast.makeText(
                    context,
                    "Passwords do not match,Please try again",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun updatePassword(email: String?, password: String, otp: String?)
    {
        val retIn = RetrofitInstance.getRetrofitInstance().create(ServiceInterface::class.java)
        retIn.updatePassword(email,password,otp)?.enqueue(object : Callback<users>
        {
            override fun onResponse(call: Call<users>, response: Response<users>)
            {

                if (response.isSuccessful)
                {
                    val intent = Intent(context, LoginActivity::class.java)
                    startActivity(intent)

                }
                else
                {
                    Toast.makeText(context, "An error occurred while updating Password", Toast.LENGTH_SHORT).show()

                }
            }

            override fun onFailure(call: Call<users>, t: Throwable)
            {
                Toast.makeText(
                    context,
                    t.message,
                    Toast.LENGTH_SHORT
                ).show()
            }

        })

    }
}