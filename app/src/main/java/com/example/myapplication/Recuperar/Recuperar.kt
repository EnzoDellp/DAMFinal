package com.example.myapplication.Recuperar

import androidx.compose.foundation.background
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.componenets.CustomButton
import com.example.myapplication.ui.theme.backgroundDarkGray
import com.example.myapplication.ui.componenets.inuptForm

@Composable
fun Recuperar(navController: NavController) {
    Scaffold(
        content = { paddingValues ->
            contentRecuperar(paddingValues,)
        }
    )
}

@Composable
fun contentRecuperar(paddingValues: PaddingValues) {

    LazyColumn(
        Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .background(backgroundDarkGray),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        item {
            Column {
                Text(
                    text = "Recuperar\n\n Contraseña",
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontSize = 32.sp,
                    modifier = Modifier.padding(bottom = 15.dp)

                )

            }

        }
        item {
            val columnWidth = 350.dp
            var mostrarTexto by remember { mutableStateOf(false) }
            Column(Modifier.width(columnWidth)) {

                inuptForm("E-Mail")
                CustomButton(
                    text = "Recuperar",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    onClick = { mostrarTexto = !mostrarTexto } //mostrar texto debajo
                )


            }
            if (mostrarTexto) {
                Row(
                    Modifier.padding(top = 15.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Revise su casilla de Correo para recueprar su contraseña",
                        color = Color.LightGray,
                        textAlign = TextAlign.Center,
                        fontSize = 18.sp,
                        letterSpacing = 2.sp,
                        style = TextStyle(
                            fontStyle = FontStyle.Italic
                        )

                    )
                }
            }

        }

    }
}


@Preview(showBackground = true)
@Composable
fun RecuperarPreview() {
    val navController = rememberNavController()
    Recuperar(navController)
}