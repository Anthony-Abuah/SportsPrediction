package com.example.sportsprediction.feature_app.ui.presentation.composables.components

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.Divider
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.example.sportsprediction.core.util.Constants.CANCEL
import com.example.sportsprediction.core.util.Constants.emptyString
import com.example.sportsprediction.feature_app.ui.theme.LocalSpacing

@Composable
fun RadioAlertDialog(
    openDialog: Boolean,
    title: String,
    oldSelectedOption: String,
    radioOptions: List<String>?,
    getSelectedOption: (selectedOption: String) -> Unit,
    closeDialog: () -> Unit,
) {
    var selectedRadioOption by remember {
        mutableStateOf(emptyString)
    }
        if (openDialog) {
            val context = LocalContext.current

            AlertDialog(
                properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true,
                usePlatformDefaultWidth = true),
                modifier = Modifier
                    .padding(LocalSpacing.current.extraSmall)
                    .fillMaxWidth(),
                onDismissRequest = {
                    Toast.makeText(context, "$selectedRadioOption was selected", Toast.LENGTH_LONG).show()
                    closeDialog()
                },
                title = {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Box(modifier = Modifier.padding(LocalSpacing.current.extraSmall),
                        contentAlignment = Alignment.Center){
                            AlertDialogTitleText(text = title)
                        }
                        Divider()
                        Spacer(modifier = Modifier.height(LocalSpacing.current.small))
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
                                    closeDialog()
                                }
                            )
                        }
                        Divider()
                    }
                },

                confirmButton = {
                },

                dismissButton = {
                    TextButton(
                        onClick = {
                            Toast.makeText(context, "$selectedRadioOption was selected", Toast.LENGTH_LONG).show()
                            closeDialog()
                        }
                    ) {
                        Box(modifier = Modifier.fillMaxWidth().padding(horizontal = LocalSpacing.current.smallMedium),
                        contentAlignment = Alignment.CenterEnd){
                            BasicText(text = CANCEL, fontSize = 16.sp, textColor = Color.Black)
                        }
                    }
                }
            )
        }
}