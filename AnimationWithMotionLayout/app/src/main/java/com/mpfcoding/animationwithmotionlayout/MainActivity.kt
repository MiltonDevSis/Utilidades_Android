package com.mpfcoding.animationwithmotionlayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.mpfcoding.animationwithmotionlayout.ui.theme.AnimationWithMotionLayoutTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimationWithMotionLayoutTheme {

            }
        }
    }
}