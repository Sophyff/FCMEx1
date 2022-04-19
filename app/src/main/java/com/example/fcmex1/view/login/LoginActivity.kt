package com.example.fcmex1.view.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.fcmex1.data.User
import com.example.fcmex1.data.api.ApiService
import com.example.fcmex1.data.repository.Repository
import com.example.fcmex1.databinding.ActivityLoginBinding
import com.example.fcmex1.view.dashboard.DashboardActivity
import com.google.android.gms.tasks.Task
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.ktx.messaging

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    var fcmToken = " "
    lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModel()
        initObservers()
        initEvents()

        val task: Task<String> = Firebase.messaging.token
        task.addOnCompleteListener {
            fcmToken=it.result
            Log.d(FCM_TOKEN, "onCreate in Login Activity, FCM token is $fcmToken")
        }
    }

    private  fun initEvents(){
        binding.btnLogin.setOnClickListener {
            val mobileNo=binding.etMobileNo.text.toString()
            val password=binding.etPassword.text.toString()
            viewModel.login(mobileNo, password, fcmToken)
        }
    }

    fun initViewModel(){
        val repository=Repository(ApiService.getInstance())
        val factory=LoginViewModelFactory(repository)
        viewModel=ViewModelProvider(this,factory)[LoginViewModel::class.java]
    }

    fun initObservers(){
        viewModel.error.observe(this){
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }

        viewModel.loginRes.observe(this){
            saveUseInfo(it.user)
            startActivity(Intent(baseContext,DashboardActivity::class.java))
            finish()
        }
    }

    private fun saveUseInfo(user: User){
        val pref=getSharedPreferences(USER_INFO, MODE_PRIVATE)
        val editor=pref.edit()
        editor.putString("user_id",user.user_id)
        editor.putString("name",user.name)
        editor.putString("mobile_no",user.mobile_no)
        editor.putBoolean("loggedIn",true)
        editor.apply()
    }

    companion object{
        const val FCM_TOKEN="FCM_TOKEN"
        const val USER_INFO="USER_INFO"
    }
}