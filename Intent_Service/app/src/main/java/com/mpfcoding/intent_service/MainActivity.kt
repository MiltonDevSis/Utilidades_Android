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
            Intent(this, MyService::class.java).also {
                startService(it)
                binding.textService.text = "Service running..."
            }
        }

        binding.buttonStop.setOnClickListener {
            Intent(this, MyService::class.java).also {
                stopService(it)
                binding.textService.text = "Service stopped"
            }
        }

        binding.buttonSendData.setOnClickListener {
            Intent(this, MyService::class.java).also {
                val dataString = binding.editData.text.toString()
                it.putExtra("EXTRA_DATA", dataString)
                startService(it)
            }
        }

        createIntentService()
    }

    /**
     * codigo para estudar sobre Intent Service
     */
    private fun createIntentService() {
//        binding.buttonStart.setOnClickListener {
//            Intent(this, MyIntentService::class.java).also {
//                startService(it)
//                binding.textService.text = "Service running"
//            }
//        }
//
//        binding.buttonStop.setOnClickListener {
//            MyIntentService.stopService()
//            binding.textService.text = "Service stopped"
//        }
    }
}