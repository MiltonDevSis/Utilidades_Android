package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var textResult: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edtPrimeiro = findViewById<EditText>(R.id.edtNumero1)
        val edtSegundo = findViewById<EditText>(R.id.edtNumero2)
        val buttonFirst = findViewById<Button>(R.id.btnOne)
        textResult = findViewById(R.id.txtResultado)

        buttonFirst.setOnClickListener {

            val numeroUm = edtPrimeiro.text.toString()
            val numeroDois = edtSegundo.text.toString()

            if (numeroUm == "" || numeroDois == ""){
                Toast.makeText(applicationContext, "Os campos são obrigatórios!", Toast.LENGTH_LONG).show()
            }else{
                val numeroUm = Integer.parseInt(edtPrimeiro.text.toString())
                val numeroDois = Integer.parseInt(edtSegundo.text.toString())

                val intent = Intent(this@MainActivity, SecondActivity::class.java)
                intent.putExtra("numero1", numeroUm)
                intent.putExtra("numero2", numeroDois)
                startActivityForResult(intent, 1)
            }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1){
            if (resultCode == RESULT_OK){
                val result = data?.getIntExtra("resultado", 0)
                textResult!!.text = " $result"
            }

            if (resultCode == RESULT_CANCELED){
                textResult!!.text = getString(R.string.nada_selecionado)
            }
        }
    }
}