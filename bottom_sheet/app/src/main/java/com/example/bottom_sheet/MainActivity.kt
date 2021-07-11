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
        val save = bottomSheetDialog.findViewById<LinearLayout>(R.id.save)
        val edit = bottomSheetDialog.findViewById<LinearLayout>(R.id.edit)
        val delete = bottomSheetDialog.findViewById<LinearLayout>(R.id.delete)
        val cancel = bottomSheetDialog.findViewById<LinearLayout>(R.id.cancel)

        save?.setOnClickListener {
            Toast.makeText(applicationContext, "Save is Clicked ", Toast.LENGTH_LONG).show()
            bottomSheetDialog.dismiss()
        }

        edit?.setOnClickListener {
            Toast.makeText(applicationContext, "Edit is Clicked ", Toast.LENGTH_LONG).show()
            bottomSheetDialog.dismiss()
        }

        delete?.setOnClickListener {
            Toast.makeText(applicationContext, "Delete is Clicked ", Toast.LENGTH_LONG).show()
            bottomSheetDialog.dismiss()
        }

        cancel?.setOnClickListener {
            Toast.makeText(applicationContext, "Cancel is Clicked ", Toast.LENGTH_LONG).show()
            bottomSheetDialog.dismiss()
        }

        bottomSheetDialog.show()
    }
}