package com.impax.mgeni.ocr

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import com.impax.mgeni.R
import com.impax.mgeni.databinding.ActivityMainMrzBinding
import com.impax.mgeni.ocr.camera.IdData

class MainMrzActivity : AppCompatActivity(), CardResult
{

    private lateinit var viewBinding: ActivityMainMrzBinding
    private lateinit var cameraManager: MrzCameraManager

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr_side)
        viewBinding = ActivityMainMrzBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        initializeCamera()
        startCamera()


        onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed()
            {
                println("Back button pressed")
                // Code that you need to execute on back press i.e. finish()
                //get the
            }
        })
    }

    private fun initializeCamera()
    {
        cameraManager =
            MrzCameraManager(
                this,
                this,
                viewBinding,
                this
            )
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
                    this,
                    "Permissions not granted by the user.",
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

    override fun cardDetails(card: IdData) {
        val intent = Intent("MRZ_ACTION")
        intent.putExtra("card", card)
        setResult(505, intent)
        finish()
    }

    override fun onBackPressed()
    {
        onBackPressedDispatcher.onBackPressed()
    }
}