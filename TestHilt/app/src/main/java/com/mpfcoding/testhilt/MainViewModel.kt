package com.mpfcoding.testhilt

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val _liveData = MutableLiveData(0)
    var liveData: LiveData<Int> = _liveData

    fun operation(um: Int, dois: Int, tipo: Int) {
        _liveData.value = if(tipo == 1){
            um + dois
        } else{
            um * dois
        }
    }
}