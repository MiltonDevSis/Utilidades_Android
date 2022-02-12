package com.mpfcoding.fab_explosion_animation

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import render.animations.Render
import render.animations.Rotate

class NextActivity : AppCompatActivity() {

    private lateinit var button: Button
    private lateinit var view: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_next)

        button = findViewById(R.id.button)
        view = findViewById(R.id.view)

        val render = Render(this@NextActivity)

        button.setOnClickListener {
            render.setAnimation(Rotate.Out(view))
            render.start()
        }
    }
}