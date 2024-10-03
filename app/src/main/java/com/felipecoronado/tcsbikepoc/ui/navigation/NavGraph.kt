import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.felipecoronado.tcsbikepoc.ui.bikeinfo.BikeInfoScreen
import com.felipecoronado.tcsbikepoc.ui.calendar.CalendarScreen
import com.felipecoronado.tcsbikepoc.ui.login.LoginScreen
import com.felipecoronado.tcsbikepoc.ui.navigation.Screens

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screens.Login.route) {

        composable(Screens.Login.route) {
            LoginScreen() {
                navController.navigate(Screens.Calendar.route)
            }
        }

        composable(Screens.Calendar.route) {
            CalendarScreen {
                navController.navigate(Screens.BikeInfo.route)
            }
        }

        composable(Screens.BikeInfo.route) {
            BikeInfoScreen()
        }
    }
}