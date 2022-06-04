package com.mpfcoding.testhilt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var testeString: String

    private lateinit var buttonTest: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonTest = findViewById(R.id.buttonTest)

        buttonTest.setOnClickListener {
            Toast.makeText(this, testeString, Toast.LENGTH_LONG).show()
        }
    }
}