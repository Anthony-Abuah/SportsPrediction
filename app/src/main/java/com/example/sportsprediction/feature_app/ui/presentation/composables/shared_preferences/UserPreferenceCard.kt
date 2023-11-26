package com.example.sportsprediction.feature_app.ui.presentation.composables.shared_preferences

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import com.example.sportsprediction.core.util.Constants.emptyString
import com.example.sportsprediction.feature_app.ui.presentation.composables.components.*
import com.example.sportsprediction.feature_app.ui.theme.*
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserPreferenceCard(
    alertDialogTitleText: String,
    primaryUserPreferenceText: String,
    userPreferenceValue: String,
    preferenceImage: Int,
    getUserPreferenceValue: () -> Unit,
    content: @Composable () -> Unit
) {
    var openAlertDialog by mutableStateOf(false)


    Card(modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onBackground
        ),
        onClick = { openAlertDialog = true }){
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = LocalSpacing.current.small, vertical = LocalSpacing.current.default) ,
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier
                .weight(1f)
                .padding(LocalSpacing.current.noPadding),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = preferenceImage),
                    contentDescription = emptyString
                )

            }

            Column(modifier = Modifier.weight(9f)) {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .padding(LocalSpacing.current.noPadding),
                    contentAlignment = Alignment.CenterStart
                ){
                    Text(text = primaryUserPreferenceText,
                        style = MaterialTheme.typography.bodyLarge,
                        overflow = TextOverflow.Visible,
                        fontWeight = FontWeight.SemiBold
                    )
                    //UserPreferenceMainText(text = primaryUserPreferenceText, color = primaryPreferenceColor)
                }

                Box(modifier = Modifier
                    .fillMaxWidth()
                    .padding(LocalSpacing.current.noPadding),
                    contentAlignment = Alignment.CenterStart
                ){
                    Text(text = userPreferenceValue,
                    style = MaterialTheme.typography.bodyMedium,
                    overflow = TextOverflow.Visible,
                        fontWeight = FontWeight.Normal
                    )
                    //UserPreferenceValueText(text = userPreferenceValue, color = secondaryPreferenceColor)
                }
            }

        }

        CustomAlertDialog(
            openDialog = openAlertDialog,
            alertDialogTitleText = alertDialogTitleText,
            closeDialog = { openAlertDialog = false
                getUserPreferenceValue()
            }
        ) { content() }

    }


}