package com.example.javascriptbridge

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class JavascriptBridgeActivity : AppCompatActivity() {

    private lateinit var webview: WebView

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_javascript_bridge)

        webview = findViewById(R.id.my_webview)

        webview.webViewClient = WebViewClient()
        webview.settings.javaScriptEnabled = true
        webview.addJavascriptInterface(MyJavascriptInterface(), "Android")
        webview.loadUrl("file:///android_asset/webpage.html")
    }
}