package com.example.myapplication.vencimientos

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
import com.example.myapplication.ManuPrincipal.ContentMenu
import com.example.myapplication.ui.componenets.CustomButton
import com.example.myapplication.ui.theme.backgroundDarkGray

import com.example.myapplication.ui.theme.backgroundButtonBlue

@Composable
fun vencimientos(navController: NavController) {
    Scaffold(
        content = { paddingValues ->
            vencimientosContent(paddingValues, navController)
        }
    )
}

@Composable
fun vencimientosContent(paddingValues: PaddingValues, navController: NavController) {
    LazyColumn(
        Modifier
            .fillMaxSize()
            .background(backgroundDarkGray)
            .padding(paddingValues),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        item {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Vencimientos",
                    color = Color.White,
                    fontSize = 32.sp
                )
                Text(
                    modifier=Modifier.padding(top=12.dp),
                    text = "Su Próximo Vencimiento es: \n\n XX/XX/XXXX",
                    color = Color.White,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center
                )

            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun vencimientosPreview() {
    val navController = rememberNavController()
    vencimientos(navController)

}