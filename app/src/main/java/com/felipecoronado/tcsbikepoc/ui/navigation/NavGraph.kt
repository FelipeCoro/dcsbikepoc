import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.felipecoronado.tcsbikepoc.ui.bikeinfo.BikeInfoScreen
import com.felipecoronado.tcsbikepoc.ui.calendar.CalendarScreen
import com.felipecoronado.tcsbikepoc.ui.login.LoginScreen
import com.felipecoronado.tcsbikepoc.ui.navigation.Screens
import com.felipecoronado.tcsbikepoc.ui.rodadas.RodadaDetailsScreen
import com.felipecoronado.tcsbikepoc.ui.rodadas.RodadasScreen

@Composable
fun NavGraph(navController: NavHostController) {


    NavHost(navController = navController, startDestination = Screens.Login.route) {

        composable(Screens.Login.route) {
            LoginScreen {
                navController.navigate(Screens.Calendar.route)
            }
        }

        composable(Screens.Calendar.route) {
            CalendarScreen {
                navController.navigate("${Screens.BikeInfo.route}/false")
            }
        }

        composable(
            route = "${Screens.BikeInfo.route}/{hasBike}",
            arguments = listOf(navArgument("hasBike") { type = NavType.BoolType })
        ) { backStackEntry ->
            val hasNotifications = backStackEntry.arguments?.getBoolean("hasBike") ?: false
            BikeInfoScreen(hasNotifications)
        }

        composable(Screens.Rodadas.route) {
            RodadasScreen(navController)
        }

        composable(
            "${Screens.RodadasDetails.route}/{sitio}/{hora}/{dificultad}/{image}",
            arguments = listOf(
                navArgument("sitio") { type = NavType.StringType },
                navArgument("hora") { type = NavType.StringType },
                navArgument("dificultad") { type = NavType.StringType },
                navArgument("image") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val sitio = backStackEntry.arguments?.getString("sitio") ?: 0
            val hora = backStackEntry.arguments?.getString("hora") ?: 0
            val dificultad = backStackEntry.arguments?.getString("dificultad") ?: 0
            val image = backStackEntry.arguments?.getInt("image") ?: 0
            RodadaDetailsScreen(sitio.toString(), hora.toString(), dificultad.toString(), image)
        }
    }
}
