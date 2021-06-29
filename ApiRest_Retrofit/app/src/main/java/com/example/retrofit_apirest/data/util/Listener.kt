package com.example.retrofit_apirest.data.util

interface Listener {

    fun deleteOnClick(position:Int,userId:Int)

    fun updateOnClick(position: Int,userId: Int,name:String,phoneNo:Long)
}