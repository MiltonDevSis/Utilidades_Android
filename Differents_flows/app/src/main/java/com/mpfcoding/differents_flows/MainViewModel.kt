package com.mpfcoding.differents_flows

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel: ViewModel() {

    private val _liveData = MutableLiveData("Test LiveData")
    var liveData: LiveData<String> = _liveData

    private val _stateFlow = MutableStateFlow("Test StateFlow")
    var stateFlow = _stateFlow.asStateFlow()


    fun triggerLiveData(){
        _liveData.value = "liveData click"
    }

    fun triggerStateFlow(){
        _stateFlow.value = "State flow click"
    }
}