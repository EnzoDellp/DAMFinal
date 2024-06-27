package com.example.myapplication.Login

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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.DB.UserDatabaseHelper
import com.example.myapplication.ui.componenets.ClickableTextCustom
import com.example.myapplication.ui.theme.backgroundDarkGray
import com.example.myapplication.ui.componenets.LoginScreen

@Composable
fun Login(navController: NavController, userRepository: UserRepository) {
    Scaffold(
        content = { paddingValues ->
            ContentLogin(paddingValues, navController, userRepository)
        }
    )
}

@Composable
fun ContentLogin(paddingValues: PaddingValues, navController: NavController, userRepository: UserRepository) {
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
                    .fillMaxSize()
                    .padding(top = 50.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Club\n \n Deportivo ",
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontSize = 43.sp
                )
            }
        }
        item {
            // LoginLogic
            LoginScreen(userRepository = userRepository, navController = navController)
        }
        item {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ClickableTextCustom(
                    text = "¿Olvidaste tu Contraseña?",
                    style = TextStyle(
                        Color.White,
                        textDecoration = TextDecoration.Underline,
                        fontSize = 18.sp
                    ),
                    onClick = { navController.navigate("Recuperar") }
                )

                ClickableTextCustom(
                    text = "Crear Cuenta",
                    style = TextStyle(
                        Color.Yellow,
                        textDecoration = TextDecoration.Underline,
                        fontSize = 18.sp
                    ),
                    modifier = Modifier.padding(top = 10.dp),
                    onClick = { navController.navigate("crearCuenta") }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    val navController = rememberNavController()
    val context= LocalContext.current
    val userDatabaseHelper=UserDatabaseHelper(context)
    val userRepository = UserRepository(userDatabaseHelper)

    Login(navController, userRepository)
}
