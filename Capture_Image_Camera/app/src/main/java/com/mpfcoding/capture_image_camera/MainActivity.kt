package com.mpfcoding.capture_image_camera

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Matrix
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

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

    private fun setComponents() {
        image1 = findViewById(R.id.capture_image_1)
        image2 = findViewById(R.id.capture_image_2)
        imageButton1 = findViewById(R.id.imageButton1)
        imageButton2 = findViewById(R.id.imageButton2)
    }

    private fun setListeners() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
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

    private fun fixOrientation(mBitmap: Bitmap): Bitmap? {
        if (mBitmap.width > mBitmap.height) {
            val matrix = Matrix()
            matrix.postRotate(90F)
            return Bitmap.createBitmap(mBitmap, 0, 0, mBitmap.width, mBitmap.height, matrix, true)
        }
        return null
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == RESULT_OK) {
            val mBitmap = data!!.extras!!.get("data") as Bitmap

            rotateImage(mBitmap, image1)

            imageButton1!!.visibility = View.GONE

        } else if (requestCode == 101 && resultCode == RESULT_OK) {
            val mBitmap = data!!.extras!!.get("data") as Bitmap

            rotateImage(mBitmap, image2)

            imageButton2!!.visibility = View.GONE
        }
    }

    private fun rotateImage(mBitmap: Bitmap, imageView: ImageView?) {
        val resultImage = fixOrientation(mBitmap)

        if (resultImage != null) {
            imageView!!.setImageBitmap(resultImage)
        } else {
            imageView!!.setImageBitmap(mBitmap)
        }
    }
}