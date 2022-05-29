package com.mpfcoding.view_flipper

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    private val _liveData = MutableLiveData(0)
    var liveData: LiveData<Int> = _liveData

    fun triggerLiveData(image: Int): Int {
        _liveData.value = image
        return _liveData.value!!
    }
}