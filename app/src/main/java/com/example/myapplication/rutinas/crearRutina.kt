package com.example.myapplication.rutinas

import androidx.compose.foundation.background
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ManuPrincipal.ContentMenu
import com.example.myapplication.ui.componenets.CustomButton
import com.example.myapplication.ui.componenets.CustomTextArea
import com.example.myapplication.ui.theme.backgroundDarkGray

import com.example.myapplication.ui.theme.backgroundButtonBlue
import com.example.myapplication.ui.theme.backgroundColorInput

@Composable
fun crearRutina(navController: NavController) {
    Scaffold(
        content = { paddingValues ->
            crearRutinaContent(paddingValues, navController)
        }
    )


}

@Composable
fun crearRutinaContent(paddingValues: PaddingValues, navController: NavController) {
    LazyColumn(
        Modifier
            .fillMaxSize()
            .padding(paddingValues)
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
                    text = "Crea tu  Rutina \n Personalizada",
                    color = Color.White,
                    fontSize = 32.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 16.dp)
                )

//                inuptForm("nombre de la rutina")
//
//                inuptForm("Musculo Principal")

            }
        }
        item {
            var textState by remember { mutableStateOf(TextFieldValue()) }
            CustomTextArea(
                textState = textState,
                onTextChange = { textState = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = "Descripcion de la rutina",
                textStyle = TextStyle(
                    color = Color.White,
                    fontSize = 16.sp
                ),
                backgroundColor = Color.Black,
                contentColor = Color.White


            )

        }
        item {
            val columnWidth = 280.dp
            Column(
                Modifier
                    .width(columnWidth)
                    .padding(top = 16.dp),
                verticalArrangement = Arrangement.Center,

                ) {
                CustomButton(
                    text = "Crear Rutina",
                    backgroundColor = backgroundButtonBlue,
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { navController.navigate("misRutinas") }
                )
                CustomButton(
                    text = "Volver",
                    backgroundColor = backgroundButtonBlue,
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { navController.navigate("MenuPrincipal") }
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CrearRutinaPreview() {
    val navController = rememberNavController()
    crearRutina(navController)
}