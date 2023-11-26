package com.example.sportsprediction.feature_app.ui.presentation.composables.components


import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.Divider
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.example.sportsprediction.core.util.Constants.CANCEL
import com.example.sportsprediction.core.util.Constants.OK
import com.example.sportsprediction.feature_app.ui.theme.LocalSpacing

@Composable
fun CustomAlertDialog(
    openDialog: Boolean,
    title: String,
    closeDialog: () -> Unit,
    content: @Composable () -> Unit
) {
    if (openDialog) {
        val context = LocalContext.current

        AlertDialog(
            properties = DialogProperties(dismissOnBackPress = true,
                dismissOnClickOutside = true,
                usePlatformDefaultWidth = true),
            modifier = Modifier
                .padding(LocalSpacing.current.extraSmall)
                .fillMaxWidth(),
            onDismissRequest = { closeDialog() },
            title = {
                Column(modifier = Modifier.fillMaxWidth()
                    .wrapContentHeight(align = Alignment.CenterVertically)) {
                    Box(modifier = Modifier.fillMaxWidth().padding(LocalSpacing.current.extraSmall),
                        contentAlignment = Alignment.Center){
                        AlertDialogTitleText(text = title)
                    }
                    Spacer(modifier = Modifier.height(LocalSpacing.current.small))
                    Column(
                        modifier = Modifier.fillMaxWidth().wrapContentHeight(align = Alignment.CenterVertically),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        content()
                    }
                    Divider()
                }
            },

            confirmButton = {
                TextButton(
                    onClick = {
                        closeDialog()
                    }
                ) {
                    BasicText(text = OK, fontSize = 16.sp, textColor = Color.Black)
                }
            },

            dismissButton = {
                TextButton(
                    onClick = {
                        closeDialog()
                    }
                ) {
                    BasicText(text = CANCEL, fontSize = 16.sp, textColor = Color.Black)
                }
            }
        )
    }
}


@Composable
fun AutoCompleteAlertDialog(
    openDialog: Boolean,
    title: String,
    closeDialog: () -> Unit,
    content: @Composable () -> Unit
) {
    if (openDialog) {
        val context = LocalContext.current

        AlertDialog(
            properties = DialogProperties(dismissOnBackPress = true,
                decorFitsSystemWindows = true,
                dismissOnClickOutside = true,
                usePlatformDefaultWidth = true),
            modifier = Modifier
                .padding(LocalSpacing.current.extraSmall)
                .fillMaxWidth(),
            onDismissRequest = { closeDialog() },
            title = {
                Box(modifier = Modifier.fillMaxWidth().padding(LocalSpacing.current.extraSmall),
                    contentAlignment = Alignment.Center){
                    AlertDialogTitleText(text = title)
                }
            },
            text = {

                Column(modifier = Modifier.fillMaxWidth()) {
                    Spacer(modifier = Modifier.height(LocalSpacing.current.small))
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        content()
                    }
                    Divider()
                }
            },

            confirmButton = {
                TextButton(
                    onClick = {
                        closeDialog()
                    }
                ) {
                    BasicText(text = OK, fontSize = 16.sp, textColor = Color.Black)
                }
            },

            dismissButton = {
                TextButton(
                    onClick = {
                        //Toast.makeText(context, "$selectedRadioOption was selected", Toast.LENGTH_LONG).show()
                        closeDialog()
                    }
                ) {
                    BasicText(text = CANCEL, fontSize = 16.sp, textColor = Color.Black)
                }
            }

        )
    }
}



