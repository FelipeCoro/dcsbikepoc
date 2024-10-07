package com.felipecoronado.tcsbikepoc.ui.rodadas

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.felipecoronado.tcsbikepoc.R
import com.felipecoronado.tcsbikepoc.ui.navigation.Screens
import kotlin.random.Random


@Composable
fun RodadasScreen(navController: NavHostController) {
    val random = Random(System.currentTimeMillis())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        Image(
            painter = painterResource(id = R.drawable.ic_logo_white),
            contentDescription = "Logo",
            modifier = Modifier.size(120.dp)
        )

        Text(
            text = "Próximos Eventos",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 8.dp, bottom = 16.dp)
        )
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            val rodada = listOf(
                RodadaInfo("Verjon", "7:00", "4", R.drawable.img1),
                RodadaInfo("Patios", "8:00", "2", R.drawable.img2),
                RodadaInfo("Cuchilla", "6:00", "6",R.drawable.img3),
                RodadaInfo("Sisga", "5:30", "6",R.drawable.img4)
            )

            items(rodada.size) {
                Rodada(rodada[it]) { sitio, hora, dificultad, image ->
                    navController.navigate("${Screens.RodadasDetails.route}/$sitio/$hora/$dificultad/$image")
                }
            }
        }
    }
}

@Composable
fun Rodada(rodada: RodadaInfo, onClick: (String, String, String, Int) -> Unit) {

    Card(
        onClick = { onClick(rodada.sitio, rodada.hora, rodada.dificultad, rodada.image) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = rodada.image),
                contentDescription = null,
                modifier = Modifier.size(80.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = "Rodada ${rodada.sitio}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Text(text = "Hora salida: ${rodada.hora}", fontSize = 16.sp)
                Text(text = "Dificultad: ${rodada.dificultad}", fontSize = 16.sp)
            }
        }
    }
}

data class RodadaInfo(
    val sitio: String,
    val hora: String,
    val dificultad: String,
    val image: Int
)

