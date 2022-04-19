package com.example.fcmex1.view.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fcmex1.data.repository.Repository
import com.example.fcmex1.data.response.LoginResponse

class LoginViewModel(val repository: Repository): ViewModel() {

    val loginRes:LiveData<LoginResponse> = repository.loginResponse
    val error: LiveData<String> = repository.error

    fun login(mobileNo:String, password:String, fcmToken:String){
        repository.login(mobileNo,password,fcmToken)
    }



}