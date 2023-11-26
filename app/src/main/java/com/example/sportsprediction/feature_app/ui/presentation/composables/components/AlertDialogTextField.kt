package com.example.sportsprediction.feature_app.ui.presentation.composables.components

import androidx.compose.foundation.text.KeyboardOptions
<<<<<<< HEAD
import androidx.compose.material.Checkbox
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
=======
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
>>>>>>> 07c8880 (Second commit)
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
<<<<<<< HEAD
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White,
            focusedIndicatorColor = Color.DarkGray,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = Color.Black,
            focusedLabelColor = Color.Black,
            unfocusedLabelColor = Color.Black,
            disabledLabelColor = Color.Black,
=======
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedIndicatorColor = MaterialTheme.colorScheme.onBackground,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.onSurface,
            cursorColor = MaterialTheme.colorScheme.primary,
            focusedLabelColor = MaterialTheme.colorScheme.onBackground,
            unfocusedLabelColor = MaterialTheme.colorScheme.onSurface,
            disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
>>>>>>> 07c8880 (Second commit)
        ),
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = ImeAction.Done
        ),
        textStyle = TextStyle(
<<<<<<< HEAD
            color = Color.Black,
            fontSize = 16.sp,
            textAlign = TextAlign.Center
=======
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold
>>>>>>> 07c8880 (Second commit)
        ),

    )

}
