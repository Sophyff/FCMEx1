package com.example.fcmex1.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    val myRetrofit: Retrofit by lazy{
        val logInterceptor=HttpLoggingInterceptor().apply {
            level=HttpLoggingInterceptor.Level.BODY
        }
        val client:OkHttpClient=OkHttpClient.Builder()
            .addInterceptor(logInterceptor)
            .build()

        val r=Retrofit.Builder().apply {
            baseUrl("https://psmobitech.com/fcmdemo/api/index.php/")
            addConverterFactory(GsonConverterFactory.create())
            client(client)
        }.build()
        r
    }
}