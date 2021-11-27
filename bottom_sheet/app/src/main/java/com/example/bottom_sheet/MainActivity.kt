package com.example.bottom_sheet

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetDialog

class MainActivity : AppCompatActivity() {

    private var mBottton: Button? = null
    private var btnYes: Button? = null
    private var btnNo: Button? = null
    lateinit var bottomSheetDialog: BottomSheetDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mapperComponents()
        mapperActionComponents()
    }

    private fun mapperComponents(){
        bottomSheetDialog = BottomSheetDialog(this)
        bottomSheetDialog.setContentView(R.layout.bottom_sheet)
        btnYes = bottomSheetDialog.findViewById(R.id.btnYes)
        btnNo = bottomSheetDialog.findViewById(R.id.btnNo)
        mBottton = findViewById(R.id.button)
    }

    private fun mapperActionComponents(){
        mBottton?.setOnClickListener {
            showBottomSheetDialog()
        }
    }

    private fun showBottomSheetDialog() {

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