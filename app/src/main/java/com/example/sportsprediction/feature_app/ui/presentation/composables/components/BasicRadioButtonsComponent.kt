package com.example.sportsprediction.feature_app.ui.presentation.composables.components

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.sportsprediction.core.util.Constants.emptyString

@Composable
fun BasicRadioButtonsComponent(
    oldSelectedOption: String,
    radioOptions: List<String>?,
    getSelectedOption: (selectedOption: String) -> Unit
) {
    var selectedRadioOption by remember {
        mutableStateOf(emptyString)
    }
    val context = LocalContext.current

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        SimpleRadioButtonComponent(
            radioOptions = radioOptions,
            oldSelectedOption = oldSelectedOption,
            getOptionSelected = { selectedOption ->
                getSelectedOption(selectedOption)
                selectedRadioOption = selectedOption
                Toast.makeText(context, "$selectedRadioOption was selected", Toast.LENGTH_LONG).show()
            }
        )
    }


}