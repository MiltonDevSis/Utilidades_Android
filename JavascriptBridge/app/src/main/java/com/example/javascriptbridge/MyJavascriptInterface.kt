package com.example.javascriptbridge

import android.util.Log
import android.webkit.JavascriptInterface

class MyJavascriptInterface {

    @JavascriptInterface
    fun showToast(){
        Log.i("TESTE", "Deu certo")
    }
}

