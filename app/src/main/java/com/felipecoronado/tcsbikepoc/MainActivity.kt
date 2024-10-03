package com.felipecoronado.tcsbikepoc

import NavGraph
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.felipecoronado.tcsbikepoc.notification.CountdownNotification
import com.felipecoronado.tcsbikepoc.ui.login.LoginScreen
import com.felipecoronado.tcsbikepoc.ui.theme.TCSBikePOCTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TCSBikePOCTheme {
                NavGraph()
            }
        }
    }
}

