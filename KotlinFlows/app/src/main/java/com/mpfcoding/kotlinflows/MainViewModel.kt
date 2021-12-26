package com.mpfcoding.kotlinflows

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel(
    private val dispatchers: DispatcherProvider
) : ViewModel() {

    val countDownFlow = flow {
        val startingValue = 5
        var currentValue = startingValue
        emit(startingValue)
        while (currentValue > 0) {
            delay(500L)
            currentValue--
            emit(currentValue)
        }
    }.flowOn(dispatchers.main)

    private val _stateFlow = MutableStateFlow(0)
    val stateFlow = _stateFlow.asStateFlow()

    private val _sharedFlow = MutableSharedFlow<Int>(replay = 5)
    val sharedFlow = _sharedFlow.asSharedFlow()

    private fun squareNumber(number: Int) {
        viewModelScope.launch(dispatchers.main) {
            _sharedFlow.emit(number * number)
        }
    }

    fun incrementalCounter(){
        _stateFlow.value += 1
    }

    init {
        squareNumber(3)
        viewModelScope.launch(dispatchers.main) {
            sharedFlow.collect {
                delay(2000L)
                println("FIRST FLOW: The received number is $it")
            }
        }
        viewModelScope.launch(dispatchers.main) {
            sharedFlow.collect {
                delay(3000L)
                println("SECOND FLOW: The received number is $it")
            }
        }
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