package com.example.sportsprediction.feature_app.ui.presentation.composables.components

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.sportsprediction.core.util.Constants.All
import com.example.sportsprediction.core.util.Constants.emptyString
import com.example.sportsprediction.feature_app.ui.theme.LocalSpacing

@Composable
fun SimpleChecklistComponent(
) {

}


@Composable
fun ScrollableChecklistComponent(
    checkListOptions: List<String>?,
    checkedItems: List<String>,
    getCheckedItems: (checkedItems: List<String>) -> Unit
) {
    val theCheckListOptions = checkListOptions ?: listOf(emptyString)
    var thisCheckedItems = mutableListOf<String>()

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .requiredHeight(250.dp)
            .padding(LocalSpacing.current.extraSmall),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onBackground
        ),
        shape = RectangleShape,
        elevation = CardDefaults.cardElevation(LocalSpacing.current.noElevation)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(1f)
                .background(MaterialTheme.colorScheme.background)
                .padding(LocalSpacing.current.noPadding)
                .verticalScroll(state = ScrollState(0), enabled = true),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
        ) {
            theCheckListOptions.forEach { option ->
                var isChecked = checkedItems.contains(option)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(LocalSpacing.current.small)
                        .toggleable(value = isChecked, //checkedItems.contains(option),
                            onValueChange = { selected ->
                                if (selected) {
                                    if (option == All) {
                                        thisCheckedItems = theCheckListOptions.toMutableList()
                                    } else {
                                        thisCheckedItems.add(option)
                                    }
                                    thisCheckedItems = thisCheckedItems.toSet().toMutableList()
                                    //checkedItems.remove(All)
                                } else {
                                    thisCheckedItems = thisCheckedItems.toSet().toMutableList()
                                    thisCheckedItems.remove(option)
                                    thisCheckedItems.remove(All)
                                }
                                getCheckedItems(thisCheckedItems)
                                isChecked = checkedItems.contains(option)
                            }
                        ),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .padding(LocalSpacing.current.small)
                            .requiredHeight(LocalSpacing.current.checkbox)
                            .requiredWidth(LocalSpacing.current.checkbox),
                        contentAlignment = Alignment.Center
                    ) {
                        Checkbox(
                            modifier = Modifier,
                            checked = isChecked,
                            onCheckedChange = { selected ->
                                if (selected) {
                                    if (option == All) {
                                        thisCheckedItems = checkListOptions?.toMutableList() ?: mutableListOf()
                                    } else {
                                        thisCheckedItems.add(option)
                                    }
                                    thisCheckedItems = thisCheckedItems.toSet().toMutableList()
                                    //checkedItems.remove(All)
                                } else {
                                    thisCheckedItems = thisCheckedItems.toSet().toMutableList()
                                    thisCheckedItems.remove(option)
                                    thisCheckedItems.remove(All)
                                }
                                getCheckedItems(thisCheckedItems)
                                isChecked = if (thisCheckedItems.containsAll(checkListOptions ?: emptyList())) true else thisCheckedItems.contains(option)
                            },
                            colors = CheckboxDefaults.colors(
                                checkedColor = MaterialTheme.colorScheme.onPrimaryContainer,
                                uncheckedColor = MaterialTheme.colorScheme.onPrimaryContainer,
                                checkmarkColor = MaterialTheme.colorScheme.primaryContainer
                            )
                        )

                    }

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = LocalSpacing.current.small),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            text = option,
                            style = MaterialTheme.typography.bodyLarge,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                }

            }


        }

    }
}


