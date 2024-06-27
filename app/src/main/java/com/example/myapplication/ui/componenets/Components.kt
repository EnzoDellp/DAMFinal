package com.example.myapplication.ui.componenets

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.DB.UserRepository
import com.example.myapplication.ui.theme.backgroundDarkGray
import com.example.myapplication.ui.theme.backgroundButtonBlue
import com.example.myapplication.ui.theme.backgroundColorInput


@Composable
fun LoginOptions(painterResource: Int, contentDescriptor: String) {
    //Crea una imagen redondeada
    Image(
        painter = painterResource(painterResource), contentDescription = contentDescriptor,
        modifier = Modifier
            .padding(start = 10.dp, end = 10.dp)
            .size(60.dp)
            .clip(RoundedCornerShape(100.dp))
    )
}

@Composable
fun InputForm(
    label: String = "Label",
    text: String,
    onValueChange: (String) -> Unit,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    Column(
        Modifier.fillMaxWidth()
    ) {
        Text(
            text = "$label: ",
            color = Color.White,
            fontSize = 22.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(start = 16.dp, bottom = 3.dp)
        )

        TextField(
            value = text,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            maxLines = 1,
            textStyle = TextStyle(color = Color.Black),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Black,
                unfocusedContainerColor = Color.LightGray,
                unfocusedIndicatorColor = Color.Black.copy(alpha = 0.5f),
                disabledIndicatorColor = Color.Black.copy(alpha = 0.2f),
            ),
            visualTransformation = visualTransformation
        )
    }
}

@Composable
fun CustomButton(
    text: String = "Texto del boton",
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.Blue,
    shape: Shape = RectangleShape,
    contentColor: Color = Color.White,
    onClick: () -> Unit,

    ) {
    Button(
        onClick = onClick,
        modifier = modifier.padding(8.dp),
        shape = shape,
        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor)


    ) {
        Text(
            text = text,
            color = contentColor,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )

    }
}

@Composable
fun ClickableTextCustom(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = TextStyle(color = Color.Blue),
    onClick: () -> Unit
) {
    ClickableText(
        text = AnnotatedString(text),
        modifier = modifier,
        style = style,
        onClick = { onClick() }
    )


}

@Composable
fun CustomTextArea(
    textState: TextFieldValue,
    onTextChange: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    textStyle: TextStyle = TextStyle(
        color = Color.Black,
        fontSize = 16.sp,
        textAlign = TextAlign.Start
    ),
    backgroundColor: Color = Color.LightGray,
    contentColor: Color = Color.LightGray
) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .background(backgroundColor),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(value = textState,
            onValueChange = onTextChange,
            modifier= Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(backgroundColor),
            textStyle=textStyle,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = backgroundColorInput,
                unfocusedContainerColor = backgroundColorInput,
                disabledContainerColor = backgroundColorInput,
            ),
            maxLines = Int.MAX_VALUE,
            placeholder={
                Text(text = placeholder, style = textStyle.copy(color=Color.Gray))
            }
        )



    }

}
//LoginLogic
@Composable
fun LoginScreen(userRepository: UserRepository, navController: NavController) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text(text = "Usuario") },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = backgroundColorInput,
                unfocusedContainerColor = backgroundColorInput,
                disabledContainerColor = backgroundColorInput,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                disabledTextColor = Color.White
            ),
            textStyle = TextStyle(color = Color.White)
        )
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(text = "Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = backgroundColorInput,
                unfocusedContainerColor = backgroundColorInput,
                disabledContainerColor = backgroundColorInput,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                disabledTextColor = Color.White
            ),
            textStyle = TextStyle(color = Color.White)
        )
        Button(
            onClick = {
                val cursor = userRepository.getUser(username, password)
                if (cursor.count > 0) {
                    navController.navigate("MenuPrincipal")
                } else {
                    Toast.makeText(context, "Usuario o Contraseña Incorrectos", Toast.LENGTH_SHORT)
                        .show()
                }

            },

            Modifier.padding(top = 16.dp),
            shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
        ) {
            Text(text = "Login", color = Color.White)
        }
    }
}

//CrearCuentaLogic
@Composable
fun CrearCuentaScreen(userRepository: UserRepository, navController: NavController) {
    var username by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .padding(top = 25.dp)
            .width(300.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        InputForm(
            label = "Usuario",
            text = username,
            onValueChange = { username = it }
        )
        InputForm(
            text = apellido,
            label = "Apellido",
            onValueChange = { apellido = it }
        )
        InputForm(
            text = email,
            label = "Email",
            onValueChange = { email = it }
        )
        InputForm(
            text = password,
            label = "Contraseña",
            visualTransformation = PasswordVisualTransformation(),
            onValueChange = { password = it }
        )
        InputForm(
            text = confirmPassword,
            visualTransformation = PasswordVisualTransformation(),
            label = "Repetir Contraseña",
            onValueChange = { confirmPassword = it }
        )

        Button(
            onClick = {
                if (username.isNotEmpty()
                    && apellido.isNotEmpty()
                    && email.isNotEmpty()
                    && password.isNotEmpty()
                    && confirmPassword.isNotEmpty()
                ) {
                    if (password == confirmPassword) {
                        Log.d("CrearCuentaScreen", "Intentando crear usuario con:")
                        Log.d(
                            "CrearCuentaScreen",
                            "Username: $username, Apellido: $apellido, Email: $email, Password: $password"
                        )

                        val result = userRepository.addUser(username, apellido, password, email)
                        if (result != -1L) {
                            Log.d(
                                "CrearCuentaScreen",
                                "Usuario creado correctamente con ID: $result"
                            )
                            Toast.makeText(
                                context,
                                "Usuario Creado Correctamente",
                                Toast.LENGTH_SHORT
                            ).show()
                            navController.popBackStack()

                        } else {
                            Log.d(
                                "CrearCuentaScreen",
                                "Error al crear usuario. Resultado de inserción: $result"
                            )
                            Toast.makeText(context, "Error al crear usuario", Toast.LENGTH_SHORT)
                                .show()
                        }
                    } else {
                        Log.d("CrearCuentaScreen", "Las contraseñas no coinciden")
                        Toast.makeText(context, "Las contraseñas no coinciden", Toast.LENGTH_SHORT)
                            .show()
                    }
                } else {
                    Log.d(
                        "CrearCuentaScreen",
                        "Campos incompletos. Username: $username, Apellido: $apellido, Email: $email, Password: $password, ConfirmPassword: $confirmPassword"
                    )
                    Toast.makeText(
                        context,
                        "Por favor complete todos los campos",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            },
            modifier = Modifier.padding(top = 18.dp),
            shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(containerColor = backgroundColorInput)
        ) {
            Text("Crear Cuenta")
        }
    }
}
