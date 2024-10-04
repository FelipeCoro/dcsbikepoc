package com.felipecoronado.tcsbikepoc.ui.calendar

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.felipecoronado.tcsbikepoc.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarScreen(
    onChosenDate: () -> Unit
) {
    val datePickerState = rememberDatePickerState()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_logo_white),
            contentDescription = "Logo",
            modifier = Modifier
                .size(120.dp)
        )
        DatePicker(
            state = datePickerState,
            showModeToggle = false,
            modifier = Modifier
                .padding(bottom = 8.dp),
            title = {
                Text("Ricardo perezoso")
            },
            headline = {
                Text("Lea la documentacion de los objetos")
            }
        )

        Button(
            onClick = {
                Toast.makeText(
                    context,
                    "Fecha guardada: ${datePickerState.selectedDateMillis?.let { java.util.Date(it) }}",
                    Toast.LENGTH_SHORT
                ).show()
                onChosenDate()
            },
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Text(
                "Guardar Fecha",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}