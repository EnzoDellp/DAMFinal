package com.example.myapplication.AgregarSocio

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
import com.example.myapplication.ui.theme.backgroundDarkGray

@Composable
fun pagoResponse(navController: NavController) {
    Scaffold { paddingValues ->
        PagoResponseContent(paddingValues,)
    }
}

@Composable
fun PagoResponseContent(paddingValues: PaddingValues,) {
    LazyColumn(
        Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .background(backgroundDarkGray),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        item {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                Text(
                    text = "Su pago\n fue Aprobado",
                    color = Color.White,
                    fontSize = 34.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
                Text(
                    modifier = Modifier.padding(top = 12.dp),
                    letterSpacing = 1.sp,
                    textAlign = TextAlign.Center,
                    text = "Enviamos un comprobante de pago a su correo electrónico",
                    color = Color.White,
                    fontSize = 18.sp,
                )
            }


        }
    }


}

@Preview(showBackground = true)
@Composable
fun PagoResponsePreview() {
    val navController = rememberNavController()
    pagoResponse(navController)
}
