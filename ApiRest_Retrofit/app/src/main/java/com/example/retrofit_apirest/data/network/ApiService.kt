package com.example.retrofit_apirest.data.network

import com.example.retrofit_apirest.data.Phone
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    companion object{
        const val  BASE_URL = "https://phone-api-8826.herokuapp.com/"
    }

    @GET("/v1/phone")
    suspend fun getPhone(): List<Phone>

    @FormUrlEncoded
    @POST("v1/phone")
    suspend fun setPhone(
        @Field("name") name:String,
        @Field("phoneNo") phoneNo:Long
    ):Phone
}