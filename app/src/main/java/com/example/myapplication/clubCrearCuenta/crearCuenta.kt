package com.example.myapplication.clubCrearCuenta

import com.example.myapplication.DB.UserRepository
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.DB.UserDatabaseHelper
import com.example.myapplication.ui.componenets.CrearCuentaScreen
import com.example.myapplication.ui.theme.backgroundDarkGray


@Composable

fun crearCuenta(navController: NavController,userRepository: UserRepository) {
    Scaffold(
        content = { paddingValues ->
            ContentCrearCuenta(paddingValues, navController,userRepository)
        }
    )
}

@Composable
fun ContentCrearCuenta(paddingValues: PaddingValues, navController: NavController,userRepository: UserRepository) {

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
            CrearCuentaScreen(userRepository = userRepository, navController =navController )
        }

    }

}

@Preview(showBackground = true)
@Composable
fun CrearCuentaPreview() {
    val navController = rememberNavController()
    val context= LocalContext.current
    val userDatabaseHelper= UserDatabaseHelper(context)
    val userRepository = UserRepository(userDatabaseHelper)
    crearCuenta(navController,userRepository)
}



