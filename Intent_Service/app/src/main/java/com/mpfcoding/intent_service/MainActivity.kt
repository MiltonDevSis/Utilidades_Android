package com.mpfcoding.intent_service

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mpfcoding.intent_service.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonStart.setOnClickListener {
            Intent(this, MyIntentService::class.java).also {
                startService(it)
                binding.textService.text = "Service running"
            }
        }

        binding.buttonStop.setOnClickListener {
            MyIntentService.stopService()
            binding.textService.text = "Service stopped"
        }
    }
}