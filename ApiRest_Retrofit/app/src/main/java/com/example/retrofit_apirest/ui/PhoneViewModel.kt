package com.example.retrofit_apirest.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit_apirest.data.Phone
import com.example.retrofit_apirest.data.repository.MainRepository
import com.example.retrofit_apirest.data.util.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhoneViewModel
@Inject
constructor(private val mainRepository: MainRepository) : ViewModel() {

    private val _apiStateFlow: MutableStateFlow<ApiState> = MutableStateFlow(ApiState.Empty)
    val apiState: StateFlow<ApiState> = _apiStateFlow

    fun getPhone() = viewModelScope.launch {
        mainRepository.getPhone()
            .onStart {
                _apiStateFlow.value = ApiState.loading
            }.catch { e ->
                _apiStateFlow.value = ApiState.Failure(e)
            }.collect { data ->
                _apiStateFlow.value = ApiState.Success(data)
            }
    }

    fun setPhone(
        name: String,
        phoneNo: Long
    ) = mainRepository.setPhone(name = name, phoneNo = phoneNo)
}