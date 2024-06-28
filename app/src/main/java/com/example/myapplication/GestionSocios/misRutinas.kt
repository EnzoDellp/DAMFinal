package com.example.myapplication.GestionSocios

import androidx.compose.foundation.background
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.componenets.CustomButton
import com.example.myapplication.ui.theme.backgroundDarkGray

import com.example.myapplication.ui.theme.backgroundButtonBlue

@Composable
fun misRutinas(navController: NavController) {
    Scaffold(content = { paddingValues ->
        misRutinasContent(paddingValues, navController)

    })
}

@Composable
fun misRutinasContent(paddingValues: PaddingValues, navController: NavController) {
    Column(
        Modifier
            .fillMaxSize()
            .background(backgroundDarkGray),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(text = "Mis Rutinas",
            color = Color.White,
            textAlign = TextAlign.Center,
            fontSize = 32.sp

            )
        Row(Modifier
            .padding(top=16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Rutina 1",color=Color.White,modifier=Modifier.padding(end=22.dp))
            Text(text = "Rutina 2",color=Color.White)
        }
        val columnWidth = 280.dp
        Column(
            Modifier
                .width(columnWidth)
                .padding(top = 200.dp),
            verticalArrangement = Arrangement.Center,

            ) {
            CustomButton(
                text = "Crear Rutina",
                backgroundColor = backgroundButtonBlue,
                modifier = Modifier.fillMaxWidth(),
                onClick = { navController.navigate("crearRutina") }
            )
            CustomButton(
                text = "Volver al Menú",
                backgroundColor = backgroundButtonBlue,
                modifier = Modifier.fillMaxWidth(),
                onClick = { navController.navigate("MenuPrincipal") }
            )
        }

    }


}

@Preview(showBackground = true)
@Composable
fun misRutinasPreview() {
    val navController = rememberNavController()
    misRutinas(navController)

}