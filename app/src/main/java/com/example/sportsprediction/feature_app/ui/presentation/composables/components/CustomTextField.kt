package com.example.sportsprediction.feature_app.ui.presentation.composables.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.sportsprediction.R
import com.example.sportsprediction.core.util.Constants.emptyString
import com.example.sportsprediction.feature_app.ui.theme.LocalSpacing
import com.example.sportsprediction.feature_app.ui.theme.MainBackgroundColor
import com.example.sportsprediction.feature_app.ui.theme.PrimaryThemeColor


@Composable
fun CustomTextField(
    placeholder: String,
    keyboardType: KeyboardType,
    getValue: (value: String) -> Unit
) {

    var value by remember {
        mutableStateOf(emptyString)
    }

    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(LocalSpacing.current.textFieldHeight)
            .padding(horizontal = LocalSpacing.current.noPadding)
            .border(
                width = LocalSpacing.current.extraSmall,
                color = PrimaryThemeColor,
                shape = RoundedCornerShape(LocalSpacing.current.medium)
            ),
        value = value,
        onValueChange = { newValue ->
            value = newValue
            getValue(value)
        },
        placeholder = {
            BasicText(text = placeholder, fontSize = 16.sp, textColor = Color.Gray)
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MainBackgroundColor,
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
            textAlign = TextAlign.Start
        ),
        shape = RoundedCornerShape(LocalSpacing.current.medium)
    )
}


@Composable
fun CustomNonEditableTextField(
    country: String,
    accessories: List<String>,
    getCountryCode: (country: String, accessories: List<String>) -> String,
    getValue: (value: String) -> Unit
) {
    val showValue = getCountryCode(country, accessories)

    val value by remember {
        mutableStateOf(showValue)
    }

    TextField(
        readOnly = true,
        modifier = Modifier
            .fillMaxWidth()
            .height(LocalSpacing.current.textFieldHeight)
            .padding(horizontal = LocalSpacing.current.noPadding)
            .border(
                width = LocalSpacing.current.extraSmall,
                color = PrimaryThemeColor,
                shape = RoundedCornerShape(LocalSpacing.current.medium)
            ),
        value = value,
        onValueChange = { newValue ->
            getValue(newValue)
        },
        placeholder = {
            BasicText(text = showValue, fontSize = 16.sp, textColor = Color.Gray)
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MainBackgroundColor,
            focusedIndicatorColor = Color.DarkGray,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = Color.Black,
            focusedLabelColor = Color.Black,
            unfocusedLabelColor = Color.Black,
            disabledLabelColor = Color.Black,
        ),
        singleLine = true,
        textStyle = TextStyle(
            color = Color.Black,
            fontSize = 16.sp,
            textAlign = TextAlign.Start
        ),
        shape = RoundedCornerShape(LocalSpacing.current.medium)
    )
}




@Composable
fun CustomTextFieldWithLeadingIcon(
    placeholder: String,
    keyboardType: KeyboardType,
    imageVector: ImageVector,
    getValue: (value: String) -> Unit
) {

    var value by remember {
        mutableStateOf(emptyString)
    }

    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(LocalSpacing.current.textFieldHeight)
            .padding(horizontal = LocalSpacing.current.noPadding)
            .border(
                width = LocalSpacing.current.extraSmall,
                color = PrimaryThemeColor,
                shape = RoundedCornerShape(LocalSpacing.current.medium)
            ),
        value = value,
        onValueChange = { newValue ->
            value = newValue
            getValue(value)
        },
        placeholder = {
            BasicText(text = placeholder, fontSize = 16.sp, textColor = Color.Gray)
        },
        leadingIcon = {
            Icon(imageVector = imageVector, contentDescription = emptyString, tint = Color.Gray)
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MainBackgroundColor,
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
            textAlign = TextAlign.Start
        ),
        shape = RoundedCornerShape(LocalSpacing.current.medium)
    )
}


@Composable
fun PasswordTextField(
    placeholder: String,
    keyboardType: KeyboardType,
    imageVector: ImageVector,
    getValue: (value: String) -> Unit
) {

    var value by remember {
        mutableStateOf(emptyString)
    }

    var hidePassword by remember {
        mutableStateOf(false)
    }

    val visualTransformation = if(hidePassword){
        VisualTransformation.None
    }
    else{
        PasswordVisualTransformation()
    }

    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(LocalSpacing.current.textFieldHeight)
            .padding(horizontal = LocalSpacing.current.noPadding)
            .border(
                width = LocalSpacing.current.extraSmall,
                color = PrimaryThemeColor,
                shape = RoundedCornerShape(LocalSpacing.current.medium)
            ),
        value = value,
        /*
        label = {
            Text(text = label)
        },
        */
        onValueChange = { newValue ->
            value = newValue
            getValue(value)
        },
        placeholder = {
            BasicText(text = placeholder, fontSize = 16.sp, textColor = Color.Gray)
        },
        leadingIcon = {
            Icon(imageVector = imageVector, contentDescription = emptyString, tint = Color.Gray)
        },
        trailingIcon = {
            Icon(
                modifier = Modifier.clickable {
                hidePassword = !hidePassword },
                painter = painterResource(id = if (hidePassword) R.drawable.hide_password else R.drawable.show_password),
                contentDescription = emptyString,
                tint = Color.Gray)
        },
        visualTransformation = visualTransformation,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MainBackgroundColor,
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
            textAlign = TextAlign.Start
        ),
        shape = RoundedCornerShape(LocalSpacing.current.medium)
    )
}






