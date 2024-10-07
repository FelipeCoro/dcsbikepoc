package com.felipecoronado.tcsbikepoc

import NavGraph
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.felipecoronado.tcsbikepoc.ui.navigation.BottomNavigationBar
import com.felipecoronado.tcsbikepoc.ui.navigation.Screens
import com.felipecoronado.tcsbikepoc.ui.theme.DCSBikePOCTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DCSBikePOCTheme {
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = {
                        BottomNavigationBar(navController)
                    }
                ) { padding ->

                    NavGraph(navController)
                    handleIntent(intent, navController)
                }
            }
        }
    }

    private fun handleIntent(intent: Intent, navController: NavHostController) {
        when (intent.action) {
            "WORKSHOP_NOTIFICATION" -> {
                val hasBike = intent.getBooleanExtra("show_workshop_notifications", false)
                navController.navigate("${Screens.BikeInfo.route}/$hasBike")
            }
        }
    }
}
