package com.example.flashlight

import android.content.Context
import android.content.pm.PackageManager
import android.hardware.camera2.CameraManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var button: ImageButton
    private lateinit var camereManager: CameraManager
    private lateinit var cameraId: String
    private lateinit var textView: TextView
    private var flashlightState: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.textView)
        textView.visibility = View.GONE
        button = findViewById(R.id.button)
        button.setOnClickListener {
            try {
                camereManager.setTorchMode(cameraId,!flashlightState)
                flashlightState = !flashlightState
                if(!flashlightState){
                    button.setImageResource(R.drawable.button_off)
                }else{
                    button.setImageResource(R.drawable.button_on)
                }
            }catch (e: java.lang.Exception){
                Log.i("ERROR", e.toString())
            }
        }

        val isFlashAvailable = applicationContext.packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)

        if(!isFlashAvailable){
            Toast.makeText(this, "Camera flash error",Toast.LENGTH_LONG).show()
            button.visibility = View.GONE
            textView.visibility = View.VISIBLE
        }

        camereManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager

        try {
            cameraId = camereManager.cameraIdList[0]
        }catch (e: Exception){

        }




    }

}