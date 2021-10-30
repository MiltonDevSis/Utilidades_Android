package com.mpfcoding.differents_flows.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.mpfcoding.differents_flows.databinding.ActivityMainBinding
import com.mpfcoding.differents_flows.utils.Constants
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

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

        binding.descriptionNormalFlow.text = Constants.descriptionNormalFlow

        binding.buttonNormalFlow.setOnClickListener {
            clickNormalFlow()
        }

        binding.buttonSharedFlow.setOnClickListener {
            viewModel.triggerSharedFlow()
        }

        binding.descriptionSharedFlow.text = Constants.descriptionSharedFlow
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

        lifecycleScope.launchWhenStarted {
            viewModel.sharedFlow.collectLatest {
                Snackbar.make(
                    binding.root,
                    it,
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun clickNormalFlow() {
        lifecycleScope.launch {
            viewModel.triggerFlow().collectLatest { text ->
                binding.textNormalFlow.text = text
            }
        }
    }
}