package com.example.fcmex1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fcmex1.data.response.LoginResponse
import com.example.fcmex1.databinding.ActivityMainBinding
import com.example.fcmex1.view.dashboard.DashboardActivity
import com.example.fcmex1.view.login.LoginActivity
import com.example.fcmex1.view.login.LoginActivity.Companion.USER_INFO

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pref=getSharedPreferences(USER_INFO, MODE_PRIVATE)
        if(pref.getBoolean("loggedIn",false)){
            startActivity(Intent(baseContext,DashboardActivity::class.java))
        }else{
            startActivity(Intent(baseContext,LoginActivity::class.java))
        }

    }
}