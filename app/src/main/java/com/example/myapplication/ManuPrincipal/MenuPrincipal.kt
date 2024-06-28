package com.example.myapplication.ManuPrincipal

import androidx.compose.foundation.background
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
fun MenuPrincipal(navController: NavController) {
    Scaffold(
        content = { paddingValues ->
            ContentMenu(paddingValues, navController)
        }
    )
}

@Composable
fun ContentMenu(paddingValues: PaddingValues, navController: NavController) {
    LazyColumn(
        Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(backgroundDarkGray),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Row(
                Modifier
                    .padding(top = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Menú\n\nPrincipal",
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontSize = 43.sp,
                )
            }
        }
        item {

            val columnWidth = 200.dp
            Column(
                Modifier
                    .width(columnWidth)
                    .padding(top = 16.dp),
                verticalArrangement = Arrangement.Center,

                ) {
                CustomButton(
                    text = "Agregar Socio",
                    backgroundColor = backgroundButtonBlue,
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { navController.navigate("pagarCuota") }
                )
                CustomButton(
                    text = "Vencimientos",
                    backgroundColor = backgroundButtonBlue,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    onClick = { navController.navigate("vencimientos") }
                )
                CustomButton(
                    text = "Gestión Socios",
                    backgroundColor = backgroundButtonBlue,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    onClick = { navController.navigate("GestionSocios") }
                )
                CustomButton(
                    text = "Mis Rutinas",
                    backgroundColor = backgroundButtonBlue,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    onClick = { navController.navigate("misRutinas") }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MenuPreview() {
    val navController = rememberNavController()
    MenuPrincipal(navController)
}



