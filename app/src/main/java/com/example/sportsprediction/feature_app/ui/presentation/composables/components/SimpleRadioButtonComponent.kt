package com.example.sportsprediction.feature_app.ui.presentation.composables.components

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.RadioButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import com.example.sportsprediction.feature_app.ui.theme.LocalSpacing


@Composable
fun SimpleRadioButtonComponent(
    radioOptions: List<String>?,
    oldSelectedOption: String,
    getOptionSelected: (selectedOption: String) -> Unit
) {

    val context = LocalContext.current
    val theRadioOptions = radioOptions ?: listOf("Nothing")
    val selectedOptionPosition = if (theRadioOptions.isNotEmpty() && !theRadioOptions.contains(oldSelectedOption)) theRadioOptions.indexOf(oldSelectedOption) else 0
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(theRadioOptions[0]) }


    Log.d("selected Option", "MarketTypeEnum: $selectedOption")
    Log.d("selected Option Position", "MarketTypeEnum: $selectedOptionPosition")

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(LocalSpacing.current.extraSmall),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
    ) {
        theRadioOptions.forEach { radioOption ->
            Row(Modifier.fillMaxWidth()
                // below method is use to add selectable to our radio button.
                .selectable(
                    // this method is called when radio button is selected.
                    selected = (radioOption == selectedOption),
                    // below method is called on clicking of radio button.
                    onClick = {
                        onOptionSelected(radioOption)
                        getOptionSelected(radioOption)
                    }
                )
                .padding(horizontal = LocalSpacing.current.small),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // below line is use to generate radio button
                RadioButton(
                    // inside this method we are adding selected with a option.
                    selected = (radioOption == selectedOption),
                    modifier = Modifier.padding(LocalSpacing.current.extraSmall),
                    onClick = {
                        // inside on click method we are setting a selected option of our radio buttons.
                        onOptionSelected(radioOption)
                        getOptionSelected(radioOption)
                        // after clicking a radio button we are displaying a toast message.
                        Toast.makeText(context, radioOption, Toast.LENGTH_LONG).show()
                    }
                )

                BasicText(text = radioOption, fontSize = 16.sp, textColor = Color.Black)

            }
        }

    }
}