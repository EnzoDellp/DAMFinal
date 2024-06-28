package com.example.myapplication.AgregarSocio

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.DB.UserDatabaseHelper
import com.example.myapplication.DB.UserRepository
import com.example.myapplication.ui.componenets.InputForm
import com.example.myapplication.ui.theme.backgroundButtonBlue
import com.example.myapplication.ui.theme.backgroundDarkGray

@Composable
fun AgregarSocio(navController: NavController, context: Context) {
    Scaffold(
        content = { paddingValues ->
            AgregarSocioContent(paddingValues, navController, context)
        }
    )
}

@Composable
fun AgregarSocioContent(
    paddingValues: PaddingValues,
    navController: NavController,
    context: Context
) {
    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var dni by remember { mutableStateOf("") }
    var selected by remember { mutableStateOf(false) }
    var esSocio by remember { mutableStateOf(false) }
    var textFieldValue by remember { mutableStateOf(TextFieldValue("")) }
    var errorMessage by remember { mutableStateOf("") }

    val datePattern = Regex("^([0-2][0-9]|(3)[0-1])/((0)[1-9]|(1)[0-2])/\\d{4}$")

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
                    text = "Agregar\n\nSocio",
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontSize = 34.sp
                )
            }
        }

        item {
            Column(
                Modifier.width(300.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                InputForm(label = "Nombre", text = nombre, onValueChange = { nombre = it })
                InputForm(label = "Apellido", text = apellido, onValueChange = { apellido = it })
                InputForm(label = "Email", text = email, onValueChange = { email = it })
                InputForm(label = "Dni", text = dni, onValueChange = { dni = it })
                Divider(Modifier.padding(top = 16.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "¿Agregar Socio como Mensual?",
                        color = Color.White,
                        fontSize = 15.sp,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(12.dp),
                    )
                    RadioButton(
                        enabled = true,
                        selected = selected,
                        onClick = {
                            selected = !selected
                            esSocio = !esSocio
                        },
                        modifier = Modifier.width(24.dp)
                    )
                }

                Divider()

                Text(
                    text = "Fecha de Inscripción",
                    color = Color.White,
                    fontSize = 15.sp,
                    modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                )
                TextField(
                    value = textFieldValue,
                    onValueChange = { newValue ->
                        val formattedValue = formatInput(newValue.text)
                        textFieldValue = newValue.copy(
                            text = formattedValue,
                            selection = TextRange(formattedValue.length)
                        )
                        errorMessage =
                            if (datePattern.matches(formattedValue)) "" else "Fecha inválida, use el formato dd/mm/yyyy"
                    },
                    label = { Text(text = "Ingresa fecha (dd/mm/yyyy)") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    visualTransformation = VisualTransformation.None
                )

                if (errorMessage.isNotEmpty()) {
                    Text(
                        text = errorMessage,
                        color = Color.Red,
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                val widthValue = 300.dp
                Column(Modifier.width(widthValue)) {
                    Button(
                        onClick = {
                            if (nombre.isNotEmpty()
                                && apellido.isNotEmpty()
                                && email.isNotEmpty()
                                && dni.isNotEmpty()
                                && datePattern.matches(textFieldValue.text)
                            ) {
                                val fecha = textFieldValue.text
                                val result = agregarSocioToDatabase(
                                    context,
                                    nombre,
                                    apellido,
                                    email,
                                    dni,
                                    fecha,
                                    esSocio
                                )
                                if (result != -1L) {
                                    Toast.makeText(
                                        context,
                                        "Socio agregado correctamente",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                } else {
                                    Toast.makeText(
                                        context,
                                        "Error al agregar socio",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            } else {
                                Toast.makeText(
                                    context,
                                    "Complete todos los campos correctamente",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        },
                        modifier = Modifier

                            .padding(top = 16.dp)
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(backgroundButtonBlue)


                    ) {
                        Text(
                            text = "Agregar Socio",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    }
                }
            }
        }
    }
}

fun formatInput(input: String): String {
    val cleanedInput = input.filter { it.isDigit() }
    val stringBuilder = StringBuilder(cleanedInput)

    if (cleanedInput.length > 2) {
        stringBuilder.insert(2, "/")
    }
    if (cleanedInput.length > 4) {
        stringBuilder.insert(5, "/")
    }
    return stringBuilder.toString()
}

fun agregarSocioToDatabase(
    context: Context,
    nombre: String,
    apellido: String,
    email: String,
    dni: String,
    fecha: String,
    esSocio: Boolean
): Long {
    val dbHelper = UserDatabaseHelper(context)
    val userRepository = UserRepository(dbHelper)
    return userRepository.addSocio(nombre, apellido, email, dni, fecha, esSocio)
}

@Preview(showBackground = true)
@Composable
fun AgregarSocioPreview() {
    val navController = rememberNavController()
    val context =
        androidx.compose.ui.platform.LocalContext.current // Este contexto es solo para la vista previa
    AgregarSocio(navController, context)
}
