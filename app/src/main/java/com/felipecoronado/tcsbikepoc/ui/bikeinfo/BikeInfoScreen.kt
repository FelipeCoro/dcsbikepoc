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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.felipecoronado.tcsbikepoc.R
import com.felipecoronado.tcsbikepoc.notification.CountdownNotification


data class BikeInfoScreenState(
    val hasNotifications: Boolean = false,
    val bikeData: BikeData? = null
)

data class BikeData(
    val name: String,
    val model: String,
    val attr1: String,
    val attr2: String,
    val attr3: String,
)

@Composable
fun BikeInfoScreen(
    hasNotifications: Boolean
) {

    val context = LocalContext.current
    val notification = CountdownNotification(context)
    val state by rememberSaveable(
        stateSaver = mapSaver(
            save = { mapOf("hasNotifications" to it.hasNotifications, "bikeData" to it.bikeData) },
            restore = {
                BikeInfoScreenState(
                    it["hasNotifications"] as Boolean,
                    it["bikeData"] as BikeData?
                )
            }
        )
    ) {
        mutableStateOf(BikeInfoScreenState(hasNotifications = hasNotifications))
    }


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

        if (
            state.hasNotifications
        ) {
            Text("Notificaciones de tu taller:")
            Text("Se te daño x de tu bicicleta")
            Text("Se te daño y de tu bicicleta")

        }
    }
}
