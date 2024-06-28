package com.example.myapplication

import com.example.myapplication.DB.UserRepository
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.AgregarSocio.AgregarSocio
import com.example.myapplication.AgregarSocio.pagoResponse
import com.example.myapplication.DB.UserDatabaseHelper
import com.example.myapplication.GestionSocios.GestionSocios

import com.example.myapplication.GestionSocios.misRutinas
import com.example.myapplication.Login.Login
import com.example.myapplication.ManuPrincipal.MenuPrincipal
import com.example.myapplication.Recuperar.Recuperar
import com.example.myapplication.clubWelcome.clubDeportivoWelcome
import com.example.myapplication.clubCrearCuenta.crearCuenta
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.vencimientos.vencimientos


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                val navController = rememberNavController()
                val context = LocalContext.current
                val userDatabaseHelper = UserDatabaseHelper(context)
                val userRepository = UserRepository(userDatabaseHelper)

                //UsuarioAdmin
                val username = "admin"
                val password = "admin"
                val lastName = "admin"
                val email = "admin"
                val adminAdd: Long = userRepository.addUser(username, password, lastName, email)



                NavHost(navController = navController, startDestination = "clubDeportivoWelcome") {
                    composable(route = "clubDeportivoWelcome") {
                        clubDeportivoWelcome(navController)
                    }
                    composable(route = "crearCuenta") {
                        crearCuenta(navController, userRepository)
                    }
                    composable(route = "Login") {
                        Login(navController, userRepository)
                    }
                    composable(route = "MenuPrincipal") {
                        MenuPrincipal(navController)
                    }
                    composable(route = "Recuperar") {
                        Recuperar(navController)
                    }
                    composable(route = "pagarCuota") {
                        AgregarSocio(navController, context)
                    }
                    composable(route = "pagoResponse") {
                        pagoResponse(navController)
                    }
                    composable(route = "vencimientos") {
                        vencimientos(navController)
                    }
                    composable(route = "GestionSocios") {
                        GestionSocios(navController)
                    }
                    composable(route = "misRutinas") {
                        misRutinas(navController)
                    }


                }
            }
        }
    }
}
