package com.example.myapplication.clubCrearCuenta

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.backgroundDarkGray
import com.example.myapplication.ui.componenets.inuptForm
import com.example.myapplication.ui.componenets.CustomButton
import com.example.myapplication.ui.theme.backgroundButtonBlue


@Composable

fun crearCuenta(navController: NavController) {
    Scaffold(
        content = { paddingValues ->
            ContentCrearCuenta(paddingValues, navController)
        }
    )
}

@Composable
fun ContentCrearCuenta(paddingValues: PaddingValues, navController: NavController) {

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
                    .fillMaxWidth()
                    .padding(top = 50.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center

            ) {
                Text(
                    //en la ejecucion se ve mejor
                    text = "Crear\n \nCuenta",
                    color = Color.White,
                    fontSize = 43.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 2.sp,

                    )

            }

        }
        item {
            Column(
                modifier = Modifier
                    .padding(top = 25.dp)
                    .width(300.dp)

            ) {
                inuptForm("Nombre")
                inuptForm("Apellido")
                inuptForm("Correo Electronico")
                inuptForm(
                    "Contraseña",
                    visualTransformation = PasswordVisualTransformation()
                )
                inuptForm(
                    "Repitir contraseña",
                    visualTransformation = PasswordVisualTransformation()
                )
            }
        }
        item {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center


            ) {
                CustomButton(
                    "Crear Cuenta",
                    backgroundColor = backgroundButtonBlue,
                    contentColor = Color.White,
                    shape = RectangleShape,
                    modifier = Modifier.padding(top = 16.dp),
                    onClick = { navController?.navigate("Login") }
                )
            }

        }
    }


}

@Preview(showBackground = true)
@Composable
fun LoginCrearCuenta() {
    val navController = rememberNavController()
    crearCuenta(navController)
}



