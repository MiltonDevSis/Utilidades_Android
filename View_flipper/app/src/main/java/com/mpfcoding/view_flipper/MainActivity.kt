package com.mpfcoding.view_flipper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.mpfcoding.view_flipper.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mapActionComponents()
        subscriberObservers()
    }

    private fun mapActionComponents(){
        binding.buttonLoading.setOnClickListener {
            viewModel.triggerLiveData(0)
        }

        binding.buttonSucess.setOnClickListener {
            viewModel.triggerLiveData(1)
        }

        binding.buttonError.setOnClickListener {
            viewModel.triggerLiveData(2)
        }
    }

    private fun subscriberObservers(){
        viewModel.liveData.observe(this) { viewFlipper ->
            binding.layoutViewFlipper.displayedChild = when (viewFlipper) {
                0 -> FLIPPER_LOADING
                1 -> FLIPPER_SUCESS
                else -> {
                    FLIPPER_ERROR
                }
            }
        }
    }

    companion object{
        private const val FLIPPER_LOADING = 0
        private const val FLIPPER_SUCESS = 1
        private const val FLIPPER_ERROR = 2
    }
}