package com.example.retrofit_apirest.data.repository

import com.example.retrofit_apirest.data.Phone
import com.example.retrofit_apirest.data.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import retrofit2.Response

class PhoneRepository @Inject constructor(private val apiService: ApiService) {

    fun getPhone(): Flow<List<Phone>> = flow {
        emit(apiService.getPhone())
    }.flowOn(Dispatchers.IO)

    fun setPhone(name: String, phoneNo: Long): Flow<Phone> = flow {
        emit(apiService.setPhone(name, phoneNo))
    }.flowOn(Dispatchers.IO)

    fun deletePhone(userId:Int): Flow<Response<Unit>> = flow {
        emit(apiService.deletePhone(userId))
    }.flowOn(Dispatchers.IO)

    fun updatePhone(userId: Int, name: String, phoneNo: Long): Flow<Response<Unit>> = flow {
        emit(apiService.updatePhone(userId,name,phoneNo))
    }.flowOn(Dispatchers.IO)
}