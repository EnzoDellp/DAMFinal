package com.example.myapplication.GestionSocios

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.DB.UserDatabaseHelper
import com.example.myapplication.DB.UserRepository
import com.example.myapplication.ui.componenets.CustomButton
import com.example.myapplication.ui.theme.backgroundButtonBlue
import com.example.myapplication.ui.theme.backgroundDarkGray

@Composable
fun GestionSocios(navController: NavController) {
    Scaffold(
        content = { paddingValues ->
            GestionSociosContent(paddingValues, navController)
        }
    )
}

@Composable
fun GestionSociosContent(paddingValues: PaddingValues, navController: NavController) {
    val context = LocalContext.current
    val dbHelper = UserDatabaseHelper(context)
    val userRepository = UserRepository(dbHelper)
    val socios = remember { mutableStateOf(listOf<UserRepository.Socio>()) }
    LaunchedEffect(Unit) {
        socios.value = userRepository.getAllSocios()
    }

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
            ) {
                Text(
                    text = "Gestión\nSocios",
                    color = Color.White,
                    fontSize = 32.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
        }

        item {
            Column {
                socios.value.forEach { socio ->
                    SocioRow(socio, navController, userRepository, socios)
                }
            }
        }

        item {
            val columnWidth = 280.dp
            Column(
                Modifier
                    .width(columnWidth)
                    .padding(top = 16.dp),
                verticalArrangement = Arrangement.Center
            ) {
                CustomButton(
                    text = "Agregar Socio",
                    backgroundColor = backgroundButtonBlue,
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { navController.navigate("AgregarSocio") }
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

@Composable
fun SocioRow(
    socio: UserRepository.Socio,
    navController: NavController,
    userRepository: UserRepository,
    socios: MutableState<List<UserRepository.Socio>>
) {
    val showDeleteDialog = remember { mutableStateOf(false) }
    val showEditDialog = remember { mutableStateOf(false) }

    if (showDeleteDialog.value) {
        AlertDialog(
            onDismissRequest = {
                showDeleteDialog.value = false
            },
            title = {
                Text(text = "Confirmar eliminación")
            },
            text = {
                Text("¿Estás seguro de que quieres eliminar a ${socio.socioName} ${socio.socioLastname}?")
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        userRepository.deleteSocio(socio.socioId)
                        socios.value = userRepository.getAllSocios()
                        showDeleteDialog.value = false
                    }
                ) {
                    Text("Sí")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showDeleteDialog.value = false
                    }
                ) {
                    Text("No")
                }
            }
        )
    }

    if (showEditDialog.value) {
        EditarSocioDialog(
            socio = socio,
            onDismiss = { showEditDialog.value = false },
            onSave = { updatedSocio ->
                userRepository.updateSocio(updatedSocio)
                socios.value = userRepository.getAllSocios()
                showEditDialog.value = false
            }
        )
    }

    Row(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "${socio.socioName} | ${socio.socioLastname}",
            color = Color.White,
            modifier = Modifier.weight(1f)
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = { showEditDialog.value = true },
                modifier = Modifier.size(60.dp, 24.dp)
            ) {
                Text("Editar", color = Color.White, fontSize = 7.sp)
            }
            Button(
                onClick = {
                    showDeleteDialog.value = true
                },
                modifier = Modifier.size(60.dp, 24.dp)
            ) {
                Text("Eliminar", color = Color.White, fontSize = 7.sp)
            }
        }
    }
}
@Composable
fun EditarSocioDialog(
    socio: UserRepository.Socio,
    onDismiss: () -> Unit,
    onSave: (UserRepository.Socio) -> Unit
) {
    var name by remember { mutableStateOf(socio.socioName) }
    var lastname by remember { mutableStateOf(socio.socioLastname) }
    var email by remember { mutableStateOf(socio.socioEmail) }
    var dni by remember { mutableStateOf(socio.socioDni) }
    var isMonthly by remember { mutableStateOf(socio.esSocio) }
    var registrationDate by remember { mutableStateOf(socio.socioFecha) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(text = "Editar Socio")
        },
        text = {
            Column {
                TextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Nombre") },
                    modifier = Modifier.fillMaxWidth()
                )
                TextField(
                    value = lastname,
                    onValueChange = { lastname = it },
                    label = { Text("Apellido") },
                    modifier = Modifier.fillMaxWidth()
                )
                TextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth()
                )
                TextField(
                    value = dni,
                    onValueChange = { dni = it },
                    label = { Text("DNI") },
                    modifier = Modifier.fillMaxWidth()
                )
                Divider(Modifier.padding(vertical = 8.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text("Es Socio Mensual", modifier = Modifier.weight(1f))
                    Switch(
                        checked = isMonthly,
                        onCheckedChange = { isMonthly = it }
                    )
                }
                Divider(Modifier.padding(vertical = 8.dp))
                Text("Fecha de Inscripción: $registrationDate")
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    val updatedSocio = socio.copy(
                        socioName = name,
                        socioLastname = lastname,
                        socioEmail = email,
                        socioDni = dni,
                        esSocio = isMonthly
                    )
                    onSave(updatedSocio)
                }
            ) {
                Text("Guardar")
            }
        },
        dismissButton = {
            TextButton(onDismiss) {
                Text("Cancelar")
            }
        }
    )
}


@Preview(showBackground = true)
@Composable
fun GestionSociosPreview() {
    val navController = rememberNavController()
    GestionSocios(navController)
}
