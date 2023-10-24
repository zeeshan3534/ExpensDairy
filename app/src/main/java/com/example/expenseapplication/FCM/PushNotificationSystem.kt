package com.example.expenseapplication.FCM

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.example.expenseapplication.R
import com.example.expenseapplication.Splash.SplashScreen
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


const val channelid ="notificationid"
const val channelname = "ExpenseApp"
class PushNotificationSystem:FirebaseMessagingService() {



    override fun onMessageReceived(message: RemoteMessage) {
        if (message.getNotification() != null){
            generateNotification(message.notification!!.title!!,message.notification!!.body!!)

        }
    }


    fun getRemoteView(title: String, description: String): RemoteViews {
        val remoteViews = RemoteViews("com.example.expenseapplication", R.layout.notification)
        remoteViews.setTextViewText(R.id.notifcationtitle, title) // Set the actual title.
        remoteViews.setTextViewText(R.id.notifcation_desc, description) // Set the actual description.
        remoteViews.setImageViewResource(R.id.notifcation_logo, R.drawable.app_icon)
        return remoteViews
    }



    fun generateNotification(title:String,message:String){
        val intent = Intent(this,SplashScreen::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        val pendingIntent = PendingIntent.getActivity(this,0,intent,
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE)


        var builder:NotificationCompat.Builder = NotificationCompat.Builder(applicationContext,
            channelid)
            .setSmallIcon(R.drawable.app_icon)
            .setAutoCancel(true)
            .setVibrate(longArrayOf(1000,1000,1000))
            .setOnlyAlertOnce(true)
            .setContentIntent(pendingIntent)



        builder = builder.setContent(getRemoteView(title, message))
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager




        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val notificationChannel = NotificationChannel(channelid,channelname,NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(notificationChannel)
        }

        notificationManager.notify(0,builder.build())

    }


}