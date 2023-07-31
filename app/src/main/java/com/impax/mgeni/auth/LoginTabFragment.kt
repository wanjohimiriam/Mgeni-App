package com.impax.mgeni.auth

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.impax.mgeni.R
import com.impax.mgeni.commons.SharedPrefs
import com.impax.mgeni.home.HomePage

class LoginTabFragment : Fragment() {

    private val btnOTP: Button by lazy {
        requireView().findViewById(R.id.OTPBtn)
    }

//    private val btnLogin: Button by lazy {
//        requireView().findViewById(R.id.loginBtn)
//    }

    private val cardPhone: CardView by lazy {
        requireView().findViewById(R.id.phoneLayout)
    }

    private val textPhoneLayout: TextInputLayout by lazy {
        requireView().findViewById(R.id.phoneLayout)
    }

    private val textPhoneText: TextInputEditText by lazy {
        requireView().findViewById(R.id.textPhone)
    }

    private val cardPassword: CardView by lazy {
        requireView().findViewById(R.id.passLayout)
    }

    private val textPass: TextView by lazy {
        requireView().findViewById(R.id.passLabel)
    }

    private val resetPassword: TextView by lazy {
        requireView().findViewById(R.id.resetPass)
    }

    private var btnLogin: Button? = null

    private lateinit var textEmail: TextInputLayout
    private lateinit var textEmailAddress: TextInputEditText
    private lateinit var textPassword: TextInputLayout
//    private lateinit var btnLogin: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.login_tab, container, false)

        textEmail = rootView.findViewById(R.id.usernameTextField)
        textEmailAddress = rootView.findViewById(R.id.emailAddress)
        textPassword = rootView.findViewById(R.id.PasswordTextField)
        btnLogin = rootView.findViewById(R.id.loginBtn)


        // Set the click listener for the btnLogin button


        btnLogin?.setOnClickListener {
            if (textEmail.editText?.text.toString()
                    .isEmpty() || textPassword.editText?.text.toString().isEmpty()
            ) {
                Toast.makeText(
                    requireContext(),
                    "Email or Password missing, Please try again",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                // Uncomment the login function once you implement the authentication logic
                // login(
                //     textEmail.editText?.text.toString(),
                //     textPassword.editText?.text.toString()
                // )
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
//    private fun checkAuth() {
//         // your authentication logic here
//
//        if (textEmailAddress. {
//            val i = Intent(requireContext(), HomePage::class.java)
//            startActivity(i)
//        } else {
//            val i = Intent(requireContext(), MainActivity::class.java)
//            startActivity(i)
//        }
//    }
//}




