package com.example.sportsprediction.feature_app.ui.presentation.composables.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.sportsprediction.feature_app.ui.theme.LocalSpacing
import java.time.LocalDate

@Composable
fun DateChip(
    currentDate: LocalDate,
    selectedLocalDate: LocalDate,
    day: String,
    date: String,
    getToggledValue: (isSelected: Boolean) -> Unit,
){
    val isSelected = currentDate == selectedLocalDate

    Card(
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            contentColor = if ((isSelected)) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface,
            containerColor = if ((isSelected)) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface
        ),
        modifier = Modifier
            .padding(LocalSpacing.current.small)
            .height(LocalSpacing.current.topAppBarSize)
            .width(LocalSpacing.current.dateChipWidth)
            .toggleable(value = isSelected,
                onValueChange = {
                    getToggledValue(it)
                }),
        elevation = CardDefaults.cardElevation(defaultElevation = LocalSpacing.current.small)
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(LocalSpacing.current.noPadding),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier
                .padding(LocalSpacing.current.noPadding)
                .fillMaxWidth()
                .weight(2f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = day,
                    color = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onBackground,
                    fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
                    fontSize = 12.sp
                )
            }
            Card(
                shape = MaterialTheme.shapes.small,
                colors = CardDefaults.cardColors(
                    contentColor = if ((currentDate == selectedLocalDate)) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.onSurfaceVariant,
                    containerColor = if ((currentDate == selectedLocalDate)) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.surfaceVariant
                ),
                modifier = Modifier
                    .weight(3f)
                    .fillMaxWidth()
                    .padding(LocalSpacing.current.noPadding),
                elevation = CardDefaults.cardElevation(defaultElevation = LocalSpacing.current.noElevation)
            ) {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        LocalSpacing.current.noPadding
                        /*
                        start = LocalSpacing.current.small,
                        end = LocalSpacing.current.small,
                        top = LocalSpacing.current.small,
                        bottom = LocalSpacing.current.noPadding*/
                    ),
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier
                            .padding(bottom = LocalSpacing.current.small),
                        text = date,
                        color = if (isSelected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.onSurface,
                        fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
                        fontSize = 10.sp,
                        textAlign = TextAlign.Center
                    )
                    Spacer(
                        modifier = Modifier
                            .width(LocalSpacing.current.smallMedium)
                            .background(if (isSelected) MaterialTheme.colorScheme.primaryContainer else Color.Transparent)
                            .height(LocalSpacing.current.extraSmall)
                    )
                }

            }

        }

    }
}