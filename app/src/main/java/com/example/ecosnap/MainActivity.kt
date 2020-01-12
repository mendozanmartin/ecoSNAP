package com.example.ecosnap


import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment

import android.provider.MediaStore
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.example.ecosnap.utils.ImageClassifier
import kotlinx.android.synthetic.main.activity_main.*
import io.reactivex.rxkotlin.subscribeBy
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {


    private lateinit var classifier: ImageClassifier
    private lateinit var photoImage: Bitmap
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var i = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        var filename = Environment.getDataDirectory().getPath() + "/test/testfile.jpg"
        var imageUri = Uri.fromFile(File(filename))

            i.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
            startActivityForResult(i, 123)

            btn_cam.setOnClickListener {
                var i = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(i, 123)

            }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 123) {
            val bmp = data?.extras?.get("data") as Bitmap

            iv_camera.setImageBitmap(bmp)
            photoImage = bmp
            classifier = ImageClassifier(getAssets())
            classifier.recognizeImage(photoImage).subscribeBy(
                onSuccess = {
                    Log.d("Message", it.toString())
                }
            )

        }
    }



}
