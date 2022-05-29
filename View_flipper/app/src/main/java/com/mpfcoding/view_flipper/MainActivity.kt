package com.mpfcoding.view_flipper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
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
        binding.buttonLoading.setOnClickListener { viewModel.triggerLiveData(FLIPPER_LOADING) }

        binding.buttonSucess.setOnClickListener { viewModel.triggerLiveData(FLIPPER_SUCESS) }

        binding.buttonError.setOnClickListener { viewModel.triggerLiveData(FLIPPER_ERROR) }

        binding.buttonAny.setOnClickListener { viewModel.triggerLiveData(FLIPPER_ANY) }
    }

    private fun subscriberObservers(){
        viewModel.liveData.observe(this) { viewFlipper ->
            binding.layoutViewFlipper.displayedChild = when (viewFlipper) {
                0 -> {
                    binding.layoutViewFlipper.inAnimation =
                        AnimationUtils.loadAnimation(this@MainActivity, R.anim.in_from_left)

                    FLIPPER_LOADING
                }
                1 -> {
                    binding.layoutViewFlipper.inAnimation =
                        AnimationUtils.loadAnimation(this@MainActivity, R.anim.in_from_right)

                    FLIPPER_SUCESS
                }
                2 -> {
                    binding.layoutViewFlipper.inAnimation =
                        AnimationUtils.loadAnimation(this@MainActivity, R.anim.out_from_left)

                    FLIPPER_ERROR
                }
                else -> {
                    binding.layoutViewFlipper.inAnimation =
                        AnimationUtils.loadAnimation(this@MainActivity, R.anim.out_from_right)

                    FLIPPER_ANY
                }
            }
        }
    }

    companion object{
        private const val FLIPPER_LOADING = 0
        private const val FLIPPER_SUCESS = 1
        private const val FLIPPER_ERROR = 2
        private const val FLIPPER_ANY = 3
    }
}