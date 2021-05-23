package com.example.webview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val openWeb = findViewById<Button>(R.id.openWeb)
        openWeb.setOnClickListener {
            val intent = Intent(this@MainActivity, WebView::class.java)
            startActivity(intent)
        }
    }
}