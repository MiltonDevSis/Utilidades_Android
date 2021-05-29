package com.example.logoutuserforinactivity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextClock
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

open class HomeActivity : AppCompatActivity(), LogoutListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        (application as MyApp).registerSessionListener(this)
        (application as MyApp).startUserSession()

        val soma = findViewById<Button>(R.id.btnSoma)
        val diminui = findViewById<Button>(R.id.btnDiminui)
        val resultado = findViewById<TextView>(R.id.txtResultado)
        var numero = 0

        soma.setOnClickListener {
            numero += 1
            resultado.text = numero.toString()
        }

        diminui.setOnClickListener {
            if (numero >= 1){
                numero -= 1
                resultado.text = numero.toString()
            }
        }
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