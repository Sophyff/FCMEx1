package com.example.fcmex1.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_UPDATE_CURRENT
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.fcmex1.R
import com.example.fcmex1.view.coursedetail.CourseDetailActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlin.random.Random

class MyFcmService: FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        Log.d("FCM_Message", "onMessageReceived: ${message.data}")
        for((key,value) in message.data){
            Log.d("FCM_Message", "onMessageReceived: $key =$value")
        }
        val alert_type=message.data["alert_type"]

        if(alert_type == "NewCourse"){
            val title =message.data["title"]?:"NA"
            val msg =message.data["message"]?:"NA"
            val course_id =message.data["course_id"]?:"NA"
            val desp =message.data["description"]?:"NA"
            val fees =message.data["fees"]?:"0"

            showCourseInfoNotification(title, msg, course_id,desp,fees)
        }else if(alert_type =="news"){
            val newsTitle = message.data["newsTitle"]?:"NA"
            val newsText = message.data["newsText"]?:"NA"
            showNewsNotification(newsTitle,newsText)
        }
    }

    fun showCourseInfoNotification(title:String, msg:String, course_id:String, desp:String, fees:String){
        val id= Random.nextInt(Int.MAX_VALUE)

        val cIntent= Intent(baseContext,CourseDetailActivity::class.java)
        cIntent.putExtra("courseId", course_id)
        cIntent.putExtra("fees", fees)
        cIntent.putExtra("desp",desp)

        val pcIntent=PendingIntent.getActivity(baseContext, id,cIntent,FLAG_UPDATE_CURRENT)

        val channelId="CourseInfo"
        val notification=NotificationCompat.Builder(baseContext, channelId).apply {
            setContentTitle(title)
            setContentText(msg)
            setSmallIcon(R.drawable.ic_message)
            setContentIntent(pcIntent)
            setAutoCancel(true)
        }.build()

        val notificationManager=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel=NotificationChannel(channelId, "course detail", NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(channel)
        }
        notificationManager.notify(id,notification)

    }

    fun showNewsNotification(title:String, msg:String){
        val id= Random.nextInt(Int.MAX_VALUE)

        val channelId="News"
        val notification=NotificationCompat.Builder(baseContext, channelId).apply {
            setContentTitle(title)
            setStyle(NotificationCompat.BigTextStyle().bigText(msg))
            setSmallIcon(R.drawable.ic_message)
            setAutoCancel(true)
        }.build()

        val notificationManager=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel=NotificationChannel(channelId, "News", NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(channel)
        }
        notificationManager.notify(id,notification)

    }

}