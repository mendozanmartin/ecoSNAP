package com.example.ecosnap


import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.camerakit.CameraKitView
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.security.AccessController.getContext


class MainActivity : AppCompatActivity() {
    private lateinit var cameraKitView: CameraKitView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        cameraKitView = findViewById(R.id.camera)
        actionBar?.hide()
        supportActionBar?.hide()
        captureButton.setOnClickListener(photoOnClickListener)

        facingButton.setOnClickListener{
            Log.d("Press", "Hello ${facingButton.id}")
        }
        flashButton.setOnClickListener{
            Log.d("Press", "Hello ${flashButton.id}")
        }

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


    // From button OnClickListener
    private val photoOnClickListener: View.OnClickListener = View.OnClickListener {
        cameraKitView.captureImage { _, capturedImage ->
            val savedPhoto =
                File(this.getExternalFilesDir(null)?.absolutePath, "photo.jpg")
                Log.d("dir", Environment.getExternalStorageState())
            try {
                val outputStream = FileOutputStream(savedPhoto.path)
                outputStream.write(capturedImage)
                outputStream.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
}
