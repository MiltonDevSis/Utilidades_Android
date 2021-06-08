package com.example.ktor_http_request.data.util

import com.example.ktor_http_request.data.Post

sealed class ApiState{
    object Loading : ApiState()
    object Empty : ApiState()
    class Success(val response: List<Post>) : ApiState()
    class Failure(val error:Throwable) : ApiState()
}