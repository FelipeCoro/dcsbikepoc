package com.felipecoronado.tcsbikepoc.ui.bikeinfo

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
    var showDialog by remember { mutableStateOf(false) }
    var checkBox by remember { mutableStateOf(false) }

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

            Image(
                painter = painterResource(id = R.drawable.ic_logo_white),
                contentDescription = "Logo",
                modifier = Modifier
                    .padding(top = 16.dp)
                    .size(100.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.padding(4.dp))
            BikeCard(
                name = "Scott Aspect 950",
                status = "En Mantenimiento",
                statusColor = Color.Red,
                imageRes = R.drawable.img_bike_placeholder
            )
            BikeCard(
                name = "Colnago C68",
                status = "Lista para recogida",
                statusColor = GreenTCS,
                imageRes = R.drawable.img_bike_placeholder
            )

            Row(Modifier.padding(top = 24.dp)) {
                Text(text = "Total:", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.weight(1f))
                Text(text = if (checkBox) "$160.000" else "$80.000", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            }

            HorizontalDivider(thickness = 1.dp)

            NotificationsSection(
                state = state,
                checkBox = checkBox,
                onCheckBoxChange = { checkBox = it },
                onShowDialog = { showDialog = true }
            )
        }

        if (showDialog) {
            DefaultDialog(
                onConfirm = {
                    checkBox = true
                    showDialog = false
                },
                onDismiss = {
                    showDialog = false
                },
                title = "Cambio de pastillas",
                text = "Se visualiza desgaste en pastillas de frenado delantero."
            )
        }

        FloatingActionButton(
            onClick = { notification.showNotificationWithDelay() },
            shape = RoundedCornerShape(16.dp),
            containerColor = GreenTCS,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 124.dp, end = 20.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.telephone_handle_silhouette),
                modifier = Modifier.size(24.dp),
                contentDescription = null
            )
        }
    }
}

@Composable
fun BikeCard(name: String, status: String, statusColor: Color, imageRes: Int) {
    val borderColor = if (status == "En Mantenimiento") Color.Red else Color.Transparent

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .border(2.dp, borderColor, shape = RoundedCornerShape(12.dp)),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                modifier = Modifier.size(80.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Text(text = "Estado", fontSize = 16.sp)
                Text(text = status, fontSize = 16.sp, color = statusColor)
            }
        }
    }
}

@Composable
fun NotificationsSection(
    state: BikeInfoScreenState,
    checkBox: Boolean,
    onCheckBoxChange: (Boolean) -> Unit,
    onShowDialog: () -> Unit
) {
    Column(
        Modifier.padding(top = 24.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text("Notificaciones de tu taller:", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        if (state.hasNotifications) {
            Row(
                modifier = Modifier
                    .padding(top = 12.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Se requiere cammbio de pastillas: $80.000",
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(start = 6.dp)
                        .weight(1f)
                        .clickable { onShowDialog() }
                )
                androidx.compose.material3.Switch(
                    checked = checkBox,
                    onCheckedChange = onCheckBoxChange,
                    colors = androidx.compose.material3.SwitchDefaults.colors(
                        checkedThumbColor = GreenTCS,
                        uncheckedThumbColor = Color.Red,
                        checkedTrackColor = Color.LightGray,
                        uncheckedTrackColor = Color.Transparent
                    )
                )
            }
            Row(
                modifier = Modifier
                    .padding(top = 12.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Se requiere cambio de guayas: $50.000",
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(start = 6.dp)
                        .weight(1f)
                )

                androidx.compose.material3.Switch(
                    checked = true,
                    onCheckedChange = { },
                    colors = androidx.compose.material3.SwitchDefaults.colors(
                        checkedThumbColor = GreenTCS,
                        uncheckedThumbColor = Color.Red,
                        checkedTrackColor = Color.LightGray,
                        uncheckedTrackColor = Color.Transparent
                    )
                )
            }
        } else {
            Row(
                modifier = Modifier
                    .padding(top = 12.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Se requiere cambio de guayas: $50.000",
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(start = 6.dp)
                        .weight(1f)
                )

                androidx.compose.material3.Switch(
                    checked = true,
                    onCheckedChange = { },
                    colors = androidx.compose.material3.SwitchDefaults.colors(
                        checkedThumbColor = GreenTCS,
                        uncheckedThumbColor = Color.Red,
                        checkedTrackColor = Color.LightGray,
                        uncheckedTrackColor = Color.Transparent
                    )
                )
            }
        }
    }
}


@Composable
fun DefaultDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    title: String,
    text: String,
    confirmText: String = "Agregar",
    dismissText: String = "Cancelar"
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        icon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_repair),
                contentDescription = "Alerta",
                tint = Color.Red,
                modifier = Modifier.size(40.dp)
            )
        },
        title = {
            Text(
                title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        },
        text = {
            Text(
                text = text,
                fontSize = 16.sp,
                color = Color.Gray,
                modifier = Modifier.padding(top = 8.dp)
            )
        },
        confirmButton = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = onConfirm,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = GreenTCS,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
                ) {
                    Text(confirmText, fontSize = 18.sp)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = onDismiss,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Gray,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
                ) {
                    Text(dismissText, fontSize = 18.sp)
                }
            }
        },
        shape = RoundedCornerShape(16.dp),
        containerColor = Color(0xFFEAEAEA)
    )
}


@Preview
@Composable
fun BikeInfoScreenPreview() {
    BikeInfoScreen(hasNotifications = true)
}