package com.example.sportsprediction.feature_app.ui.presentation.composables.components

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.example.sportsprediction.core.util.Constants.CANCEL
import com.example.sportsprediction.core.util.Constants.OK
import com.example.sportsprediction.feature_app.ui.theme.LocalSpacing

@Composable
fun FilterAlertDialog(
    openDialog: Boolean,
    title: String,
    oldValue: String,
    getValue: (value: String) -> Unit,
    closeDialog: () -> Unit,
) {
    var value by remember {
        mutableStateOf(oldValue)
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
                    Toast.makeText(context, "$value was selected", Toast.LENGTH_LONG).show()
                    closeDialog()
                },
                title = {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Box(
                            modifier = Modifier.padding(LocalSpacing.current.noPadding),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            AlertDialogTitleText(text = title)
                        }
                        Spacer(modifier = Modifier.height(LocalSpacing.current.small))


                        TextField(
                            value = value,
                            onValueChange = { newValue ->
                                value = newValue
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
                                keyboardType = KeyboardType.Decimal,
                                imeAction = ImeAction.Done
                            ),
                            textStyle = TextStyle(
                                color = Color.Black,
                                fontSize = 16.sp,
                                textAlign = TextAlign.Center
                            ),
                        )

                        Divider(thickness = LocalSpacing.current.extraSmall, color = Color.Black)


                    }

                },

                confirmButton = {
                    TextButton(
                        onClick = {
                            val actualValue = if (value.toDouble().isNaN()) "50"
                            else if(value.toDouble()<1.0) value.toDouble().times(100.0).toString()
                            else if(value.toDouble()>100.0) "100"
                            else value
                            getValue(actualValue)
                            Toast.makeText(context, "$actualValue% was selected", Toast.LENGTH_LONG).show()
                            closeDialog()
                        }
                    ) {
                        Box(modifier = Modifier.padding(horizontal = LocalSpacing.current.small),
                            contentAlignment = Alignment.Center){
                            BasicText(text = OK, fontSize = 16.sp, textColor = Color.Black)
                        }
                    }
                },

                dismissButton = {
                    TextButton(
                        onClick = {
                            closeDialog()
                        }
                    ) {
                        Box(modifier = Modifier.padding(horizontal = LocalSpacing.current.small),
                            contentAlignment = Alignment.Center){
                            BasicText(text = CANCEL, fontSize = 16.sp, textColor = Color.Black)
                        }
                    }
                }
            )
        }
}