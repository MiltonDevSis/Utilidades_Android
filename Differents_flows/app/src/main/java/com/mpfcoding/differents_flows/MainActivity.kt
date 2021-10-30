package com.mpfcoding.differents_flows

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.mpfcoding.differents_flows.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mapearComponentes()
        subscribeToObservables()
    }

    private fun mapearComponentes() {
        binding.buttonLiveData.setOnClickListener {
            viewModel.triggerLiveData()
        }

        binding.buttonStateFlow.setOnClickListener {
            viewModel.triggerStateFlow()
        }
    }

    private fun subscribeToObservables() {
        viewModel.liveData.observe(this) { text ->
            binding.textLiveData.text = text
        }

        lifecycleScope.launchWhenStarted {
            viewModel.stateFlow.collectLatest { text ->
                binding.textStateFlow.text = text
            }
        }
    }
}