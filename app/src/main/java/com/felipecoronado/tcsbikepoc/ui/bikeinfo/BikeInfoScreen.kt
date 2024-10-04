package com.felipecoronado.tcsbikepoc.ui.bikeinfo

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.felipecoronado.tcsbikepoc.R
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
        Image(
            painter = painterResource(id = R.drawable.img_bike_placeholder),
            contentDescription = "Bike Image",
            Modifier.size(400.dp)
        )

        Button(
            onClick = { notification.showNotificationWithDelay() },
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Send Notification with 5 second delay")
        }
    }
}
