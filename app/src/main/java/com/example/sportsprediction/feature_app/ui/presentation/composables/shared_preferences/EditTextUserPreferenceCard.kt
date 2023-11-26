package com.example.sportsprediction.feature_app.ui.presentation.composables.shared_preferences

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.sportsprediction.core.util.Constants.Percentage_Filter
import com.example.sportsprediction.core.util.Constants.emptyString
import com.example.sportsprediction.feature_app.ui.presentation.composables.components.*
import com.example.sportsprediction.feature_app.ui.theme.*
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTextUserPreferenceCard(
    userPreference: String,
    oldValue: String,
    displayValue: String,
    getValue: (value: String) -> Unit,
    imageVector: Int

) {
    var openAlertDialog by mutableStateOf(false)

    Card(
        shape = MaterialTheme.shapes.small,
        colors = CardDefaults.cardColors(
            contentColor = Color.Black,
            containerColor = Color.White,
        ),
        //border = CardDefaults.outlinedCardBorder(),
        modifier = Modifier
            .fillMaxWidth()
            .padding(LocalSpacing.current.small),
        onClick = { openAlertDialog  = true },
        elevation = CardDefaults.cardElevation(
            defaultElevation = LocalSpacing.current.noElevation
        )
    ) {
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier
                .weight(1f)
                .padding(LocalSpacing.current.noPadding),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = imageVector),
                    contentDescription = emptyString
                )

            }

            Column(modifier = Modifier.weight(8f)) {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .padding(LocalSpacing.current.noPadding),
                    contentAlignment = Alignment.CenterStart
                ){
                    UserPreferenceMainText(text = userPreference)
                }

                Box(modifier = Modifier
                    .fillMaxWidth()
                    .padding(LocalSpacing.current.noPadding),
                    contentAlignment = Alignment.CenterStart
                ){
                    UserPreferenceValueText(text = displayValue)
                }
            }

        }

        EditTextAlertDialog(
            openDialog = openAlertDialog,
            title = Percentage_Filter,
            oldValue = oldValue,
            getValue = getValue
        ) {
            openAlertDialog = false
        }

    }


}