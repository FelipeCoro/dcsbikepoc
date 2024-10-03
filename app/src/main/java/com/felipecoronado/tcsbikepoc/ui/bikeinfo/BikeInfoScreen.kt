package com.felipecoronado.tcsbikepoc.ui.bikeinfo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.felipecoronado.tcsbikepoc.notification.CountdownNotification

@Composable
fun BikeInfoScreen() {

    val context = LocalContext.current
    val notification = CountdownNotification(context)


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Button(onClick = { notification.showNotificationWithDelay() }) {
            Text("Send Notification with 5 second delay")
        }
    }
}
