package com.felipecoronado.tcsbikepoc.ui.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.felipecoronado.tcsbikepoc.R

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        Screens.Rodadas,
        Screens.BikeInfo,
        Screens.Calendar
    )

    NavigationBar(
        containerColor = Color.White
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { screen ->
            NavigationBarItem(
                icon = {
                    when (screen) {
                        Screens.Rodadas -> Icon(painter = painterResource(id = R.drawable.ic_bike), contentDescription = "Rodadas")
                        Screens.BikeInfo -> Icon(painter = painterResource(id = R.drawable.ic_repair), contentDescription = "Bike Info")
                        Screens.Calendar -> Icon(painter = painterResource(id = R.drawable.ic_calendar), contentDescription = "Calendar")
                        Screens.Login -> TODO()
                        Screens.RodadasDetails -> TODO()
                    }
                },
                label = { Text(screen.route) },
                selected = currentRoute == screen.route,
                onClick = {
                    when (screen) {
                        Screens.BikeInfo -> {
                            // Navega a BikeInfo con el argumento `hasBike`
                            navController.navigate("${Screens.BikeInfo.route}/false")
                        }
                        else -> {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.startDestinationId)
                                launchSingleTop = true
                            }
                        }
                    }
                }
            )
        }
    }
}