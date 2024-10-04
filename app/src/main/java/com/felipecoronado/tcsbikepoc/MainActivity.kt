package com.felipecoronado.tcsbikepoc

import NavGraph
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.felipecoronado.tcsbikepoc.ui.theme.DCSBikePOCTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DCSBikePOCTheme() {
                NavGraph()
            }
        }
    }
}

