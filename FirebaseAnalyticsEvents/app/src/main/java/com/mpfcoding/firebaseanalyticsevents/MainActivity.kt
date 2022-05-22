package com.mpfcoding.firebaseanalyticsevents

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.analytics.FirebaseAnalytics

class MainActivity : AppCompatActivity() {

    private lateinit var firebaseAnalytics: FirebaseAnalytics

    private lateinit var buttonLogin: Button
    private lateinit var buttonCadastro: Button
    private lateinit var buttonCrash: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firebaseAnalytics = FirebaseAnalytics.getInstance(this)


        setComponents()
        setListeners()
    }

    private fun setComponents(){
        buttonLogin = findViewById(R.id.buttonLogin)
        buttonCadastro = findViewById(R.id.buttonCadastro)
        buttonCrash = findViewById(R.id.buttonCrash)
    }

    private fun setListeners(){
        buttonLogin.setOnClickListener {
            val bundle = Bundle()
            bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "evento_dimensao")
            firebaseAnalytics.logEvent("evento_dimensao", bundle)
            toast("Login")
        }

        buttonCadastro.setOnClickListener {
            val params = Bundle()
            params.putInt("param_teste", 123456)
            firebaseAnalytics.logEvent("evento_teste", params)
            toast("Cadastro")
        }

        buttonCrash.setOnClickListener {
            throw RuntimeException("Test Crash")
        }
    }

    private fun Context.toast(msg: String){
        return Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }
}