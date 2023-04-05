package com.impax.mgeni.ocr

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.impax.mgeni.MainActivity
import com.impax.mgeni.ocr.camera.IdData
import com.impax.mgeni.R
import com.impax.mgeni.checkin.CheckInActivity
import com.impax.mgeni.checkin.CheckinFragment
import com.impax.mgeni.checkout.CheckOutActivity
import com.impax.mgeni.commons.SharedPrefs

class OCRActivity : AppCompatActivity()
{
    var sharedPrefs: SharedPrefs? = null
    var context: Context? = null
    var idNumber: String? = null
    var name: String? = null
    private val btnScan:Button
    get() = findViewById(R.id.scanButton)
    private lateinit var cameraManager: MrzCameraManager

    val mFragmentManager = supportFragmentManager
    val mFragmentTransaction = mFragmentManager.beginTransaction()
    val mFragment = CheckinFragment()
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ocractivity)

        context=this
        sharedPrefs = SharedPrefs(this)



        btnScan.setOnClickListener {
            openScanner();
        }
    }

    private fun openScanner()
    {
        MrzBuilder(this, this.activityResultRegistry)
            .setOnCardDetailsResponse(object : CardDetailResponse {
                override fun onCardRead(card: IdData)
                {
                    if (sharedPrefs?.getItem("state").equals("checkin"))
                    {
                        val intent = Intent(context, CheckInActivity::class.java)
                        //get the id no and names and move to the checkin page
                        idNumber=card.idNo
                        if (idNumber!![0] == '0')
                        {
                            idNumber = idNumber!!.substring(1);
                        }
                        sharedPrefs?.putItem("IdNo", idNumber)
                        sharedPrefs?.putItem("Names", card.firstName +" "+ card.middleName +" " + card.lastName)
                        startActivity(intent)
                    }
                    else if(sharedPrefs?.getItem("state").equals("checkout"))
                    {
                        val intent = Intent(context, CheckOutActivity::class.java)
                        //get the id no and names and move to the checkin page
                        idNumber=card.idNo
                        if (idNumber!![0] == '0')
                        {
                            idNumber = idNumber!!.substring(1);
                        }
                        sharedPrefs?.putItem("IdNo", idNumber)
                        sharedPrefs?.putItem("Names", card.firstName +" "+ card.middleName +" " + card.lastName)
                        startActivity(intent)
                    }

                  //  Toast.makeText(this@OCRActivity, card.toString(), Toast.LENGTH_SHORT).show()


                }

                override fun onCardReadingCancelled() {
                    Toast.makeText(this@OCRActivity, "Cancelled", Toast.LENGTH_SHORT).show()
                }

                override fun onFailed(e: Exception) {
                    Toast.makeText(this@OCRActivity, e.toString(), Toast.LENGTH_SHORT).show()
                }

            })
            .start()
    }
    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>, grantResults:
        IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (allPermissionsGranted()) {
                startCamera()
            } else {
                Toast.makeText(
                    this, "Permissions not granted by the user.",
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            }
        }
    }


    private fun startCamera() {
        cameraManager.startCamera()
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(baseContext, it) == PackageManager.PERMISSION_GRANTED
    }

    companion object {
        private const val REQUEST_CODE_PERMISSIONS = 10
        private val REQUIRED_PERMISSIONS = arrayOf(android.Manifest.permission.CAMERA)

    }
}