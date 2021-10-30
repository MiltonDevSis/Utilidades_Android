package com.mpfcoding.differents_flows

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    private val _liveData = MutableLiveData("Test LiveData")
    var liveData: LiveData<String> = _liveData


    fun triggerLiveData(){
        _liveData.value = "liveData click"
    }
}