package com.example.javascriptbridge

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private lateinit var buttonJavascriptBridge: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonJavascriptBridge = findViewById(R.id.bt_javascript_bridge)

        buttonJavascriptBridge.setOnClickListener {
            val intent = Intent(this, JavascriptBridgeActivity::class.java)
            startActivity(intent)
        }
    }
}