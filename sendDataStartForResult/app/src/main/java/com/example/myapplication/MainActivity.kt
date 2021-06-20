package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    private var textResult: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edtPrimeiro = findViewById<EditText>(R.id.edtNumero1)
        val edtSegundo = findViewById<EditText>(R.id.edtNumero2)
        val buttonFirst = findViewById<Button>(R.id.btnOne)
        textResult = findViewById(R.id.txtResultado)

        val resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == RESULT_OK) {
                    val result = it.data?.getIntExtra("resultado", 0)
                    val text = " $result"
                    textResult!!.text = text
                }
                if (it.resultCode == RESULT_CANCELED) {
                    textResult!!.text = getString(R.string.nada_selecionado)
                }
            }

        buttonFirst.setOnClickListener {

            val numeroUm = edtPrimeiro.text.toString()
            val numeroDois = edtSegundo.text.toString()

            when {
                numeroUm.isEmpty() -> {
                    edtPrimeiro.error = "Campo número vazio!"
                }
                numeroDois.isEmpty() -> {
                    edtSegundo.error = "Campo número vazio!"
                }
                else -> {
                    val numberUm = Integer.parseInt(edtPrimeiro.text.toString())
                    val numberDois = Integer.parseInt(edtSegundo.text.toString())

                    val intent = Intent(this@MainActivity, SecondActivity::class.java)
                    intent.putExtra("numero1", numberUm)
                    intent.putExtra("numero2", numberDois)
                    resultLauncher.launch(intent)
                }
            }
        }
    }
}