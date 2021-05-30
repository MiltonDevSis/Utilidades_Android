package com.example.logoutuserforinactivity

import android.app.Application
import java.util.*

class MyApp : Application() {

    private var listener: LogoutListener? = null
    private var timer: Timer? = null

    fun startUserSession(): Timer {
        cancelTimer()
        timer = Timer()
        timer!!.schedule(object : TimerTask() {
            override fun run() {
                listener!!.onSessionLogout()
            }
        }, 5000)
        return timer as Timer
    }

    private fun cancelTimer() {
        if (timer != null) timer!!.cancel()
    }

    fun registerSessionListener(listener: LogoutListener) {
        this.listener = listener
    }

    fun onUserInterected(){
        startUserSession()
    }
}