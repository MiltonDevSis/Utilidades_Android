package com.mpfcoding.six_design_patterns

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val message = "Hello word"

        val messageTwo = Singleton.message

        Singleton.showMessage(message)

        Singleton.showMessageTwo(messageTwo)
    }
}