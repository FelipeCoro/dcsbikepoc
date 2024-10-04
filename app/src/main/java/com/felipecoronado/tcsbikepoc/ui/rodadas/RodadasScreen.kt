package com.felipecoronado.tcsbikepoc.ui.rodadas

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.felipecoronado.tcsbikepoc.R
import com.felipecoronado.tcsbikepoc.ui.navigation.Screens
import kotlin.random.Random


@Composable
fun RodadasScreen(navController: NavHostController) {
    val random = Random(System.currentTimeMillis())

    LazyColumn {
        items(8) {
            Rodada(random) { sitio, hora, dificultad, image ->
                navController.navigate("${Screens.RodadasDetails.route}/$sitio/$hora/$dificultad/$image")
            }
        }
    }
}

@Composable
fun Rodada(random: Random, onClick: (Int, Int, Int, Int) -> Unit) {
    val sitio = random.nextInt(100) + 1
    val hora = random.nextInt(12) + 1
    val dificultad = random.nextInt(10) + 1

    val imageResources = listOf(
        R.drawable.img1,
        R.drawable.img2,
        R.drawable.img3,
        R.drawable.img4
    )
    val randomImage = imageResources[random.nextInt(imageResources.size)]

    Card(
        onClick = { onClick(sitio, hora, dificultad, randomImage) }
    ) {
        Row {
            Image(
                painter = painterResource(id = randomImage),
                contentDescription = null
            )
            Column {
                Text("Rodada en sitio $sitio")
                Text("Hora salida $hora:00")
                Text("Dificultad $dificultad")
            }
        }
    }
}