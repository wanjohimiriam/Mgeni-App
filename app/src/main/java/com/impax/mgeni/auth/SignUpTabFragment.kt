package com.impax.mgeni.auth

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.impax.mgeni.MainActivity
import com.impax.mgeni.R
import com.impax.mgeni.commons.SharedPrefs
import com.impax.mgeni.home.HomePage
import com.impax.mgeni.models.User_Model
import com.impax.mgeni.models.users
import com.impax.mgeni.retrofit.RetrofitInstance
import com.impax.mgeni.retrofit.ServiceInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpTabFragment : Fragment() {


    private var btnSignup: Button? = null
//    private val btnSignup: Button by lazy {
//        requireView().findViewById(R.id.signBtn)
//    }


    private lateinit var textEmail: TextInputLayout
    private lateinit var textEmailAddress: TextInputEditText
    private lateinit var textPassword: TextInputLayout


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.signup_tab, container, false)

        textEmail = rootView.findViewById(R.id.usernameTextField)
        textEmailAddress = rootView.findViewById(R.id.emailAddress)
        textPassword = rootView.findViewById(R.id.PasswordTextField)
        btnSignup = rootView.findViewById(R.id.signBtn)

//        //On Sigup button Click
        btnSignup?.setOnClickListener{
            if (textEmail.editText?.text.toString().isEmpty() || textPassword.editText?.text.toString().isEmpty()
            ) {
                Toast.makeText(
                    requireContext(),
                    "Email or Password missing,Please try again",
                    Toast.LENGTH_SHORT
                ).show()
            }else {
                checkAuth()
            }
        }

        return rootView
    }


    private fun checkAuth() {
        val i = Intent(requireContext(), HomePage::class.java)
        startActivity(i)
    }
}



