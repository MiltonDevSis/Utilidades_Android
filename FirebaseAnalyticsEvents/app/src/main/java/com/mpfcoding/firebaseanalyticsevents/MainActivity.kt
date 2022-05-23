package com.mpfcoding.firebaseanalyticsevents

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.google.firebase.analytics.FirebaseAnalytics

class MainActivity : AppCompatActivity() {

    private lateinit var firebaseAnalytics: FirebaseAnalytics

    private lateinit var buttonLogin: Button
    private lateinit var buttonCadastro: Button
    private lateinit var buttonCrash: Button
    private lateinit var editLiveData: EditText
    private lateinit var textLiveData: TextView

    private val viewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firebaseAnalytics = FirebaseAnalytics.getInstance(this)


        setComponents()
        setListeners()
        setObserver()
    }

    private fun setComponents(){
        buttonLogin = findViewById(R.id.buttonLogin)
        buttonCadastro = findViewById(R.id.buttonCadastro)
        buttonCrash = findViewById(R.id.buttonCrash)
        editLiveData = findViewById(R.id.editLiveData)
        textLiveData = findViewById(R.id.textLiveData)
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

        editLiveData.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) { viewModel.triggerLiveData(s.toString()) }
        })
    }

    private fun setObserver(){
        viewModel.liveData.observe(this){ msg ->
            textLiveData.text = msg
        }
    }

    private fun Context.toast(msg: String){
        return Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }
}