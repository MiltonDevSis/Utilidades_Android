package com.example.bottom_sheet

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetDialog

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mBottton = findViewById<Button>(R.id.button)
        mBottton.setOnClickListener {
            showBottomSheetDialog()
        }
    }

    private fun showBottomSheetDialog() {
        val bottomSheetDialog = BottomSheetDialog(this)
        bottomSheetDialog.setContentView(R.layout.bottom_sheet)
        val btnYes = bottomSheetDialog.findViewById<Button>(R.id.btnYes)
        val btnNo = bottomSheetDialog.findViewById<Button>(R.id.btnNo)

        btnYes?.setOnClickListener {
            Toast.makeText(applicationContext, "Yes is Clicked ", Toast.LENGTH_LONG).show()
            bottomSheetDialog.dismiss()
        }

        btnNo?.setOnClickListener {
            Toast.makeText(applicationContext, "No is Clicked ", Toast.LENGTH_LONG).show()
            bottomSheetDialog.dismiss()
        }

        bottomSheetDialog.show()
    }
}