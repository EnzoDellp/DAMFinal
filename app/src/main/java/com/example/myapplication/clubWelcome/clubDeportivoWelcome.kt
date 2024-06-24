package com.example.myapplication.clubWelcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.R
import com.example.myapplication.ui.componenets.CustomButton
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.componenets.LoginOptions
import com.example.myapplication.ui.theme.backgroundButtonBlue
import com.example.myapplication.ui.theme.backgroundDarkGray

@Composable
fun clubDeportivoWelcome(navController: NavController) {
    ViewContent(navController)
}

@Composable
fun ViewContent(navController: NavController) {
    Scaffold(
        content = { paddingValues ->
            ContentWelcome(paddingValues, navController)
        }
    )
}

@Composable
fun ContentWelcome(paddingValues: PaddingValues, navController: NavController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(backgroundDarkGray)
    ) {
        item {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 80.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Bienvenido",
                    fontSize = 34.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                )
            }
        }
        // Imagen
        item {
            Box(
                Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = "Logo Club Deportivo",
                    modifier = Modifier.size(170.dp)
                )
            }
        }
        // Login Options
        item {
            Row(
                Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp, bottom = 16.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "iniciar sesión con".uppercase(),
                    color = Color.White,
                    fontSize = 24.sp,
                )
            }
            // Icons
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Absolute.Center
            ) {
                LoginOptions(
                    contentDescriptor = "Login With Facebook",
                    painterResource = R.drawable.facebook_ic
                )
                LoginOptions(
                    contentDescriptor = "Login With Twitter",
                    painterResource = R.drawable.whatsapp_ic
                )
                LoginOptions(
                    contentDescriptor = "Login With Google",
                    painterResource = R.drawable.google_ic
                )
            }
        }

        item {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                CustomButton(
                    "Crear Cuenta",
                    backgroundColor = backgroundButtonBlue,
                    contentColor = Color.White,
                    shape = RectangleShape,
                    modifier = Modifier.padding(top = 16.dp),
                    onClick = { navController?.navigate("crearCuenta") }
                )
            }
        }
        item {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                CustomButton(
                    "Iniciar Sesión",
                    backgroundColor = backgroundButtonBlue,
                    contentColor = Color.White,
                    shape = RectangleShape,
                    modifier = Modifier.padding(top = 16.dp),
                    onClick = { navController?.navigate("Login") }
                )
            }
        }

        item {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp, end = 30.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Al iniciar sesión en esta aplicación, aceptás nuestros términos y condiciones.",
                    color = Color.LightGray,
                    letterSpacing = 2.sp,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewClubDeportivoWelcome() {
    val navController= rememberNavController()
    MyApplicationTheme {
        clubDeportivoWelcome(navController)
    }
    }

