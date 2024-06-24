package com.example.myapplication.pagarCuota

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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.R
import com.example.myapplication.ui.componenets.LoginOptions
import com.example.myapplication.ui.theme.backgroundDarkGray
import com.example.myapplication.ui.componenets.inuptForm
import com.example.myapplication.ui.theme.backgroundButtonBlue
import com.example.myapplication.ui.componenets.CustomButton

@Composable
fun pagarCuota(navController: NavController) {
    Scaffold(
        content = { paddingValues ->
            pagarCuotaContent(paddingValues, navController)

        }
    )
}

@Composable
fun pagarCuotaContent(paddingValues: PaddingValues, navController: NavController) {

    LazyColumn(
        Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .background(backgroundDarkGray),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        item {
            Column(Modifier.padding(bottom = 24.dp)) {
                Text(
                    text = "Pagar\n\nCuota",
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontSize = 34.sp
                )
            }

            inuptForm("Monto")
            Text(
                text = "Elige una forma de Pago",
                color = Color.White,
                modifier = Modifier
                    .padding(top = 12.dp, bottom = 12.dp)
            )
            Row() {
                LoginOptions(painterResource = R.drawable.paypal, contentDescriptor = "PayPal")
                LoginOptions(
                    painterResource = R.drawable.ethereum_ic,
                    contentDescriptor = "Bitcoin"
                )
                LoginOptions(painterResource = R.drawable.bitcoin_ic, contentDescriptor = "ETH")
            }

        }
        item {
            val widthValue = 270.dp
            Column(Modifier.width(widthValue)) {
                CustomButton(
                    text = "Pagar",
                    onClick = { navController.navigate("pagoResponse") },
                    modifier = Modifier
                        .padding(top = 22.dp)
                        .fillMaxWidth(),


                    )
                CustomButton(
                    text = "Volver",
                    onClick = { navController.navigate("MenuPrincipal") },
                    modifier = Modifier
                        .padding(top = 22.dp)
                        .fillMaxWidth(),
                )
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun PagarCuotaPreview() {
    val navController = rememberNavController()
    pagarCuota(navController)
}