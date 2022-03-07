package com.mpfcoding.six_design_patterns

import android.util.Log

object SingletonPattern {

    const val message = "More one test"

    fun showMessage(msg: String){
        Log.i("Singleton", msg)
    }

    fun showMessageTwo(msg: String){
        Log.i("SingletonTwo", msg)
    }
}