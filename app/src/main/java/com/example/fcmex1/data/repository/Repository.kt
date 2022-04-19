package com.example.fcmex1.data.repository

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.fcmex1.data.api.ApiService
import com.example.fcmex1.data.response.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository(val apiService: ApiService) {
    val loginResponse=MutableLiveData<LoginResponse>()
    val error=MutableLiveData<String>()
    val isProcessing =MutableLiveData<Boolean>()

    fun login(mobileNo:String, password:String, fcm:String){
        val call=apiService.login(mobileNo ,password,fcm)

        call.enqueue(object: Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                isProcessing.postValue(false)
                if(response.isSuccessful){
                    val result=response.body()
                   loginResponse .postValue(result)
                }else{
                    error.postValue("error code is ${response.code()}")
                }
            }
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })
        isProcessing.postValue(true)
    }

}