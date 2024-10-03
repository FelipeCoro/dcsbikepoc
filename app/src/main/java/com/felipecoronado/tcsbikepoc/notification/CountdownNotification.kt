package com.felipecoronado.tcsbikepoc.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import com.felipecoronado.tcsbikepoc.R
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

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


    private var countdownJob: Job? = null


    fun startCountdown() {
        countdownJob?.cancel()
        countdownJob = countdownFlow().onEach {
            showOrUpdateNotification()
        }.launchIn(MainScope())
    }

    private fun countdownFlow(): Flow<Unit> {
        return flow {
            delay(5000L)
            emit(Unit)
        }
    }


    private fun showOrUpdateNotification() {
        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentText("Tienes un nuevo mensaje del taller!")
            .setContentTitle("TCS Bike")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .build()
        notificationManager.notify(NOTIFICATION_ID, notification)
    }

    companion object {
        const val CHANNEL_ID = "tcsbike"
        const val CHANNEL_NAME = "TCSBike"
        const val NOTIFICATION_ID = 1
    }
}
