package com.mpfcoding.differents_flows

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _liveData = MutableLiveData("Test LiveData")
    var liveData: LiveData<String> = _liveData

    private val _stateFlow = MutableStateFlow("Test StateFlow")
    var stateFlow = _stateFlow.asStateFlow()

    private val _sharedFlow = MutableSharedFlow<String>()
    var sharedFlow = _sharedFlow.asSharedFlow()


    fun triggerLiveData() {
        _liveData.value = "liveData click"
    }

    fun triggerStateFlow() {
        _stateFlow.value = "State flow click"
    }

    fun triggerFlow(): Flow<String> {
        return flow {
            repeat(5) {
                emit("Item $it")
                delay(1000L)
            }
        }
    }

    fun triggerSharedFlow(){
        viewModelScope.launch {
            _sharedFlow.emit("SharedFlow click")
        }
    }
}