package com.mpfcoding.testhilt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.mpfcoding.testhilt.glide.GlideImageLoaderImpl
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var testeString: String

    private lateinit var buttonTest: Button

    private lateinit var imageGlide: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mapComponents()
        setListeners()
    }

    private fun mapComponents() {
        buttonTest = findViewById(R.id.buttonTest)
        imageGlide = findViewById(R.id.imageFromGlide)
    }

    private fun setListeners() {
        buttonTest.setOnClickListener {
            Toast.makeText(this, testeString, Toast.LENGTH_LONG).show()
        }

        GlideImageLoaderImpl()
            .load(imageGlide, "https://miro.medium.com/max/1400/1*o8Q_O-C6yGZQqW_2cdafoQ.png")
    }
}