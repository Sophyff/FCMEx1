package com.example.fcmex1.data.api

import com.example.fcmex1.data.response.LoginResponse
import com.example.fcmex1.data.response.RegisterResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("User/register")
    fun register(
        @Field("name") name:String,
        @Field("mobile_no") mobileNo:String,
        @Field("password") password:String,
    ): Call<RegisterResponse>

    @FormUrlEncoded
    @POST("User/login")
    fun login(
        @Field("mobile_no") mobileNo:String,
        @Field("password") password:String,
        @Field("fcm_token") fcm:String
    ): Call<LoginResponse>

    companion object{
        fun getInstance()=ApiClient.myRetrofit.create(ApiService::class.java)
    }
}