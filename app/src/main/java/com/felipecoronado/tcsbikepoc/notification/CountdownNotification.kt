package com.felipecoronado.tcsbikepoc.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.felipecoronado.tcsbikepoc.MainActivity
import com.felipecoronado.tcsbikepoc.R
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CountdownNotification(
    private val context: Context
) {
    private val notificationManager = context.getSystemService(
        Context.NOTIFICATION_SERVICE
    ) as NotificationManager

    init {
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            CHANNEL_ID,
            CHANNEL_NAME,
            NotificationManager.IMPORTANCE_HIGH
        ).apply {
            description = "Channel for countdown notifications"
        }
        notificationManager.createNotificationChannel(channel)
    }


    fun showNotificationWithDelay() {
        MainScope().launch {
            delay(5000L)
            showNotification()
        }
    }

    private fun showNotification() {
        val intent = Intent(context, MainActivity::class.java).apply {
            action = "WORKSHOP_NOTIFICATION"
            putExtra("show_workshop_notifications", true)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentText("Tienes un nuevo mensaje del taller!")
            .setContentTitle("DCS Bike")
            .setSmallIcon(R.drawable.ic_logo_clipped)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .build()
        notificationManager.notify(NOTIFICATION_ID, notification)
    }
    companion object {
        const val CHANNEL_ID = "tcsbike"
        const val CHANNEL_NAME = "TCSBike"
        const val NOTIFICATION_ID = 1
    }
}
