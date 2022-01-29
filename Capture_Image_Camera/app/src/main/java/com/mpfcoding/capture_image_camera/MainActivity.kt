package com.mpfcoding.capture_image_camera

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.mpfcoding.capture_image_camera.util.ImageUtil

class MainActivity : AppCompatActivity() {

    private var image1: ImageView? = null
    private var image2: ImageView? = null
    private var imageButton1: ImageView? = null
    private var imageButton2: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setComponents()
        setListeners()
    }

    private fun setComponents(){
        image1 = findViewById(R.id.capture_image_1)
        image2 = findViewById(R.id.capture_image_2)
        imageButton1 = findViewById(R.id.imageButton1)
        imageButton2 = findViewById(R.id.imageButton2)
    }

    private fun setListeners(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 100)
        }

        image1!!.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, 100)
        }

        image2!!.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, 101)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode==RESULT_OK){
            val captureImage1 = data!!.extras!!.get("data") as Bitmap

            val teste = ImageUtil.bitmapToBase64(captureImage1)

            image1!!.setImageBitmap(captureImage1)
            imageButton1!!.visibility = View.GONE

        }else if (requestCode == 101 && resultCode==RESULT_OK){
            val captureImage2 = data!!.extras!!.get("data") as Bitmap

            val teste = ImageUtil.bitmapToBase64(captureImage2)

            image2!!.setImageBitmap(captureImage2)
            imageButton1!!.visibility = View.GONE
        }
    }
}