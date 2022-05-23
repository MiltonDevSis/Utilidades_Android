package com.mpfcoding.firebaseanalyticsevents

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    private val _liveData = MutableLiveData("")
    var liveData: LiveData<String> = _liveData

    fun triggerLiveData(msg: String): String {
        _liveData.value = msg
        return _liveData.value!!
    }
}