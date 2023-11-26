package com.example.sportsprediction.feature_app.ui.presentation.composables.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.sportsprediction.core.util.Constants.emptyString
import com.example.sportsprediction.feature_app.ui.theme.LocalSpacing
import com.example.sportsprediction.feature_app.ui.theme.PrimaryThemeColor


@Composable
fun SearchTextField(
    searchValue: String,
    getValue: (value: String) -> Unit,
    closeDialog: () -> Unit,
) {
    var value by remember { mutableStateOf(searchValue) }

    Row(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(1f)
    ) {
        Box(modifier = Modifier.weight(1f).fillMaxHeight(),
            contentAlignment = Alignment.CenterStart
        ) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                value = value,
                onValueChange = { newValue ->
                    value = newValue
                    getValue(value)
                },
                placeholder = {
                    BasicText(text = "Search...", fontSize = 16.sp, textColor = Color.Gray)
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.onSurface
                    )

                },
                trailingIcon = {
                    if (value.isNotEmpty()) {
                        Icon(modifier = Modifier.clickable {
                            value = emptyString
                        },
                            imageVector = Icons.Default.Close,
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }

                },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = MaterialTheme.colorScheme.surface,
                    focusedIndicatorColor = MaterialTheme.colorScheme.onSurface,
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.onSurface,
                    cursorColor = MaterialTheme.colorScheme.onSurface,
                    focusedLabelColor = MaterialTheme.colorScheme.surfaceVariant,
                    unfocusedLabelColor = MaterialTheme.colorScheme.surfaceVariant,
                    disabledLabelColor = MaterialTheme.colorScheme.surfaceVariant,
                ),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                textStyle = TextStyle(
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Start
                ),
            )
        }

        Box(modifier = Modifier
            .width(LocalSpacing.current.topAppBarSize)
            .height(LocalSpacing.current.topAppBarSize)
            .clickable {
                getValue(value)
                closeDialog()
            }
            .background(if (value.isEmpty()) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primaryContainer),
            contentAlignment = Alignment.Center
        ) {
            Icon(modifier = Modifier.padding(LocalSpacing.current.small),
                imageVector = if (value.isEmpty()) Icons.Default.Close else Icons.Default.ArrowForward,
                contentDescription = "",
                tint = if (value.isEmpty()) MaterialTheme.colorScheme.onError else MaterialTheme.colorScheme.onPrimaryContainer
            )
        }

    }
}