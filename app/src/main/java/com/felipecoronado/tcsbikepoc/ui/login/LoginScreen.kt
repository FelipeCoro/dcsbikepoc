package com.felipecoronado.tcsbikepoc.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.felipecoronado.tcsbikepoc.R
import com.felipecoronado.tcsbikepoc.ui.theme.GreenTCS
import kotlinx.coroutines.delay


@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit
) {
    var emailState by remember { mutableStateOf("") }
    var passwordState by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }


    LaunchedEffect(isLoading) {
        if (isLoading) {
            delay(3000)
            isLoading = false
            onLoginSuccess()
        }
    }

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
                .size(200.dp)
                .padding(15.dp)
        )

        OutlinedTextField(
            value = emailState,
            onValueChange = { emailState = it },
            label = { Text("Email") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = passwordState,
            onValueChange = { passwordState = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.padding(bottom = 10.dp))

        Button(
            onClick = {
                isLoading = true
            },
            shape = RoundedCornerShape(16.dp),

            modifier = Modifier
                .padding(top = 16.dp)
                .widthIn(min = 250.dp, max = 350.dp)
                .height(50.dp),
            enabled = !isLoading
        ) {
            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.size(24.dp))
            } else {
                Text("Ingresar", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
        }

        Text(
            text = "No tienes cuenta? Registrate ",
            color = GreenTCS,
            modifier = Modifier
                .padding(top = 10.dp)
                .clickable { }
        )

        Spacer(modifier = Modifier.padding(bottom = 80.dp))

    }
}