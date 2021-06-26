package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        title = "Activity 2"

        val intent = intent
        val numero1 = intent.getIntExtra("numero1", 0)
        val numero2 = intent.getIntExtra("numero2", 0)

        val textView = findViewById<TextView>(R.id.txtSegundaActivity)
        val text = "Os Números são $numero1 , $numero2"
        textView.text = text

        val buttonAdd = findViewById<Button>(R.id.btnAdd)
        val buttonSubtract = findViewById<Button>(R.id.btnSubtract)

        buttonAdd.setOnClickListener {
            val resultado = numero1 + numero2
            val resultIntent = Intent()
            resultIntent.putExtra("resultado", resultado)

            setResult(RESULT_OK, resultIntent)
            finish()
        }

        buttonSubtract.setOnClickListener {
            val resultado = numero1 - numero2
            val resultIntent = Intent()
            resultIntent.putExtra("resultado", resultado)

            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}