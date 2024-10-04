package com.felipecoronado.tcsbikepoc.ui.bikeinfo

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.felipecoronado.tcsbikepoc.R
import com.felipecoronado.tcsbikepoc.notification.CountdownNotification
import com.felipecoronado.tcsbikepoc.ui.theme.GreenTCS


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


    Box() {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
        ) {

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(6.dp)
            ) {
                Row(
                    modifier = Modifier.padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.img_bike_placeholder),
                        contentDescription = null,
                        modifier = Modifier.size(80.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(
                            text = "Scott Aspect 950",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                        Text(text = "Estado", fontSize = 16.sp)
                        Text(text = "En Mantenimiento", fontSize = 16.sp, color = Color.Red)
                    }
                }
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(6.dp)
            ) {
                Row(
                    modifier = Modifier.padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.img_bike_placeholder),
                        contentDescription = null,
                        modifier = Modifier.size(80.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(
                            text = "Colnago C68",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                        Text(text = "Estado", fontSize = 16.sp)
                        Text(text = "Lista para recogida", fontSize = 16.sp, color = GreenTCS)
                    }
                }
            }
            Row(Modifier.padding(top = 24.dp)) {
                Text(text = "Total:", fontSize = 24.sp)
                Spacer(modifier = Modifier.weight(1f))
                Text("$80.000", fontSize = 24.sp)
            }

            HorizontalDivider(thickness = 1.dp)

            Column(
                Modifier.padding(top = 24.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text("Notificaciones de tu taller:", fontSize = 24.sp)
                if (
                    state.hasNotifications
                ) {
                    Row(
                        modifier = Modifier
                            .padding(top = 12.dp)
                            .border(1.dp, Color.Red),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            "Se te daño x de tu bicicleta: $80.000",
                            fontSize = 18.sp,
                            modifier = Modifier.padding(start = 6.dp).weight(1f)
                        )
                        Checkbox(
                            checked = false,
                            onCheckedChange = {  }
                        )
                    }
                    Row(
                        modifier = Modifier
                            .padding(top = 12.dp)
                            .border(1.dp, GreenTCS),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            "Se te daño y de tu bicicleta: $50.000",
                            fontSize = 18.sp,
                            modifier = Modifier.padding(start = 6.dp).weight(1f)
                        )
                        Checkbox(
                            checked = true,
                            onCheckedChange = {  }
                        )
                    }
                } else {
                    Text(
                        "No tienes notificaciones",
                        fontSize = 18.sp,
                        modifier = Modifier.padding(top = 12.dp)
                    )
                }
            }
        }



        FloatingActionButton(
            onClick = { notification.showNotificationWithDelay() },
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(top = 16.dp, bottom = 48.dp, end = 16.dp)
        ) {
            Text("")
        }

    }
}


@Preview
@Composable
fun BikeInfoScreenPreview() {
    BikeInfoScreen(hasNotifications = true)
}