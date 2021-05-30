package com.example.logoutuserforinactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ProximaActivity : AppCompatActivity(), LogoutListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_proxima)

        (application as MyApp).registerSessionListener(this)
        (application as MyApp).startUserSession()

    }

    override fun onSessionLogout() {
        finish()
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    override fun onUserInteraction() {
        super.onUserInteraction()
        (application as MyApp).onUserInterected()
    }
}