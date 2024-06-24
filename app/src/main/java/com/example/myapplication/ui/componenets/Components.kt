package com.example.myapplication.ui.componenets

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
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
fun inuptForm(
//    Crea un label y un input
    labelinput: String = "label",
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    var text by remember {
        mutableStateOf("")
    }
    Column(
        Modifier
            .fillMaxWidth(),

        ) {
        Text(

            text = "$labelinput: ",
            color = Color.White,
            fontSize = 22.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(start = 16.dp, bottom = 3.dp)
        )

        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center

        )
        {

            TextField(

                value = text,
                onValueChange = { newText -> text = newText },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp),
                maxLines = 1,
                textStyle = TextStyle(color = Color.White),

                colors = TextFieldDefaults.colors(
                    focusedContainerColor = backgroundColorInput,
                    unfocusedContainerColor = backgroundColorInput,
                    disabledContainerColor = backgroundColorInput,
                ),
                visualTransformation = visualTransformation

            )
        }
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