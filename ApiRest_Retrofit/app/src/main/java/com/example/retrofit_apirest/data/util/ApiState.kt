package com.example.retrofit_apirest.data.util

import com.example.retrofit_apirest.data.Phone

sealed class ApiState {

    class Success(val data: List<Phone>): ApiState()
    class Failure(val msg : Throwable): ApiState()
    object loading: ApiState()
    object Empty : ApiState()
}