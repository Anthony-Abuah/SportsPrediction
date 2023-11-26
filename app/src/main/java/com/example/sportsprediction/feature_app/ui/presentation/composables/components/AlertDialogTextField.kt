package com.example.sportsprediction.feature_app.ui.presentation.composables.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Checkbox
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun AlertDialogTextField (
    oldValue: String,
    keyboardType: KeyboardType,
    getNewValue: (newValue: String) -> Unit
) {
    var value by remember {
        mutableStateOf(oldValue)
    }

    TextField(
        value = value,
        onValueChange = { newValue ->
            value = newValue
            getNewValue(value)
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White,
            focusedIndicatorColor = Color.DarkGray,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = Color.Black,
            focusedLabelColor = Color.Black,
            unfocusedLabelColor = Color.Black,
            disabledLabelColor = Color.Black,
        ),
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = ImeAction.Done
        ),
        textStyle = TextStyle(
            color = Color.Black,
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        ),

    )

}
