package com.example.fcmex1.view.dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.fcmex1.R
import com.example.fcmex1.databinding.ActivityDashboardBinding
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.ktx.messaging

class DashboardActivity : AppCompatActivity() {
    lateinit var binding:ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initEvents()
    }

    private fun initEvents(){
        binding.swSports.setOnCheckedChangeListener { switch, isSelected ->
            if (isSelected){
                subscribe("sports")
            }else{
                    unsubscribe("sports")
            }
        }

        binding.swTech.setOnCheckedChangeListener { switch, isSelected ->
            if (isSelected){
                subscribe("technology")
            }else{
                unsubscribe("technology")
            }
        }

        binding.swPolitic.setOnCheckedChangeListener { switch, isSelected ->
            if (isSelected){
                subscribe("politics")
            }else{
                unsubscribe("politics")
            }
        }
    }

    private fun subscribe(topic:String){
        val task=Firebase.messaging.subscribeToTopic(topic)
        task.addOnCompleteListener {
            if(it.isSuccessful){
                Toast.makeText(baseContext, "Now you will receive news from $topic", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(baseContext, "Failed to subscribe news from $topic, retry", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun  unsubscribe(topic:String){
        val task=Firebase.messaging.unsubscribeFromTopic(topic)
        task.addOnCompleteListener {
            if(it.isSuccessful){
                Toast.makeText(baseContext, "Now you will not receive news from $topic", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(baseContext, "Failed to unSubscribe news from $topic, retry", Toast.LENGTH_LONG).show()
            }
        }
    }
}