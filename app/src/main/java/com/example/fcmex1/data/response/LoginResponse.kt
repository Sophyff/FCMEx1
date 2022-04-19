package com.example.fcmex1.data.response

import com.example.fcmex1.data.User

data class LoginResponse(
    val message: String,
    val status: Int,
    val user: User
)