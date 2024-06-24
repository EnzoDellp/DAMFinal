package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.Login.Login
import com.example.myapplication.ManuPrincipal.MenuPrincipal
import com.example.myapplication.Recuperar.Recuperar
import com.example.myapplication.clubWelcome.clubDeportivoWelcome
import com.example.myapplication.clubCrearCuenta.crearCuenta
import com.example.myapplication.pagarCuota.pagarCuota
import com.example.myapplication.pagarCuota.pagoResponse
import com.example.myapplication.rutinas.crearRutina
import com.example.myapplication.rutinas.misRutinas
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.vencimientos.vencimientos


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "clubDeportivoWelcome") {
                    composable(route = "clubDeportivoWelcome") {
                        clubDeportivoWelcome(navController)
                    }
                    composable(route = "crearCuenta") {
                        crearCuenta(navController)
                    }
                    composable(route = "Login") {
                        Login(navController)
                    }
                    composable(route = "MenuPrincipal") {
                        MenuPrincipal(navController)
                    }
                    composable(route = "Recuperar") {
                        Recuperar(navController)
                    }
                    composable(route = "pagarCuota") {
                        pagarCuota(navController)
                    }
                    composable(route = "pagoResponse") {
                        pagoResponse(navController)
                    }
                    composable(route = "vencimientos") {
                        vencimientos(navController)
                    }
                    composable(route = "crearRutina") {
                        crearRutina(navController)
                    }
                    composable(route = "misRutinas") {
                        misRutinas(navController)
                    }


                }
            }
        }
    }
}
