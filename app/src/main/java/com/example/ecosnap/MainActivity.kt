package com.example.ecosnap


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.camerakit.CameraKitView

class MainActivity : AppCompatActivity() {
    private lateinit var cameraKitView: CameraKitView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        cameraKitView = findViewById(R.id.camera)
        actionBar?.hide()
        supportActionBar?.hide()

    }

    override fun onStart() {
        super.onStart()
        cameraKitView.onStart()
    }

    override fun onResume() {
        super.onResume()
        cameraKitView.onResume()
    }

    override fun onPause() {
        cameraKitView.onPause()
        super.onPause()
    }

    override fun onStop(){
        cameraKitView.onStop()
        super.onStop()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions : Array<String>, grantResults : IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        cameraKitView.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}
