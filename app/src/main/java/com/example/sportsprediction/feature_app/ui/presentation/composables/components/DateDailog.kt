package com.example.sportsprediction.feature_app.ui.presentation.composables.components

import android.widget.Toast
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.sportsprediction.core.util.Constants.CANCEL
import com.example.sportsprediction.core.util.Constants.OK
import com.example.sportsprediction.core.util.Constants.Select_Date
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.MaterialDialogState
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import java.time.LocalDate


@Composable
fun ShowDateDialog(
    selectedDate: (date: LocalDate) -> Unit,
    initialDate: LocalDate,
    dateDialogState: MaterialDialogState
) {

    val context = LocalContext.current

    MaterialDialog(
        dialogState = dateDialogState,
        elevation = 10.dp,
        buttons = {
            positiveButton(text = OK, textStyle = MaterialTheme.typography.body1) {
                Toast.makeText(context, "Date Selected", Toast.LENGTH_LONG).show()
            }
            negativeButton(text = CANCEL, textStyle = MaterialTheme.typography.body1) {
                Toast.makeText(context, "The date was not changed", Toast.LENGTH_LONG).show()
            }
        }
    ) {
        datepicker(
            initialDate = initialDate,
            title = Select_Date,
        ) { selectedDate ->
            selectedDate(selectedDate)
        }
    }
}