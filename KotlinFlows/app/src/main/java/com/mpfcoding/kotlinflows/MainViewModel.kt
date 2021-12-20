package com.mpfcoding.kotlinflows

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    val countDownFlow = flow {
        val startingValue = 5
        var currentValue = startingValue
        emit(startingValue)
        while (currentValue > 0) {
            delay(500L)
            currentValue--
            emit(currentValue)
        }
    }

    init {
        collectFlow()
    }

    private fun collectFlow() {

        val flow = flow {
            delay(250L)
            emit("Aperitivos")
            delay(1000L)
            emit("Prato principal")
            delay(100L)
            emit("Sobremessa")
        }
        viewModelScope.launch {
            flow.onEach {
                println("FLOW: $it foi entregue")
            }
                .collect {
                    println("FLOW: Acabei de comer a/o $it ")
                }
        }
    }
}