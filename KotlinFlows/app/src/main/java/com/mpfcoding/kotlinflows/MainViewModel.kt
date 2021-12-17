package com.mpfcoding.kotlinflows

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    val countDownFlow = flow {
        val startingValue = 10
        var currentValue = startingValue
        emit(startingValue)
        while (currentValue > 0){
            delay(500L)
            currentValue --
            emit(currentValue)
        }
    }

    init{
        collectFlow()
    }

    private fun collectFlow(){
        viewModelScope.launch {
            countDownFlow.collectLatest { time -> // com
//            countDownFlow.collect { time ->
                delay(1000L)
                println("The current time is $time")
            }
        }
    }
}