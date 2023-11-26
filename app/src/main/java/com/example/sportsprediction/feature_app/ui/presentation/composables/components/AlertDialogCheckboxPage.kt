package com.example.sportsprediction.feature_app.ui.presentation.composables.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import com.example.sportsprediction.core.util.Constants.Accept
import com.example.sportsprediction.core.util.Constants.Countries
import com.example.sportsprediction.core.util.Constants.Reset
import com.example.sportsprediction.core.util.Constants.SelectLeagues
import com.example.sportsprediction.core.util.Constants.UnknownTournament
import com.example.sportsprediction.core.util.Constants.emptyString
import com.example.sportsprediction.feature_app.data.local.entities.events.EventsEntity
import com.example.sportsprediction.feature_app.ui.theme.LocalSpacing

@Composable
fun AlertDialogCheckboxPage (
    groupedCountryEvents: Map<String, List<EventsEntity>>,
    theSelectedTournaments: Map<String, String>,
    closeFilter: () -> Unit,
    getLeagueNames: (selectedValues: Map<String, String>) -> Unit
) {
    val checkedTournaments by remember { mutableStateOf(theSelectedTournaments.toMutableMap()) }

    var toggledCountry by remember { mutableStateOf(emptyString) }
    var countryIsSelected by remember { mutableStateOf(false ) }
    var toggledTournaments = if (toggledCountry.isNotEmpty()) groupedCountryEvents[toggledCountry] else emptyList()
    var isChecked by remember { mutableStateOf(false)
    }

    var groupedToggledTournaments = toggledTournaments?.groupBy { it.tournamentName ?: UnknownTournament } ?: emptyMap()

    Column (modifier = Modifier.background(MaterialTheme.colorScheme.surfaceVariant)){
        // This is the row for the Country and league selection checkboxes
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f),
            verticalAlignment = CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {

            Column(
                modifier = Modifier
                    .weight(2f)
                    .fillMaxHeight()
            ) {
                val scrollState = rememberScrollState(0)
                SelectLeagueText(text = Countries)
                Spacer(modifier = Modifier.height(LocalSpacing.current.default))
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .verticalScroll(state = scrollState, enabled = true),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start
                ) {
                    groupedCountryEvents.forEach { country ->
                        val numberOfCountryEvents = country.value.size
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(if (countryIsSelected) MaterialTheme.colorScheme.primaryContainer else Color.Transparent)
                                .clickable {
                                    isChecked = false
                                    countryIsSelected = toggledCountry == country.key
                                    toggledCountry = country.key
                                    toggledTournaments =
                                        groupedCountryEvents[country.key] ?: emptyList()
                                    // We need to group the tournaments in order to get the name of a each of the various tournaments a country may have
                                    groupedToggledTournaments = toggledTournaments?.groupBy {
                                        it.tournamentName ?: UnknownTournament
                                    } ?: emptyMap()

                                },
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .weight(4.5f)
                                    .padding(LocalSpacing.current.extraSmall),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                Text(text = country.key,
                                    style= MaterialTheme.typography.bodyLarge,
                                    overflow = TextOverflow.Ellipsis
                                )

                            }
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(LocalSpacing.current.extraSmall),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = "$numberOfCountryEvents",
                                    style= MaterialTheme.typography.bodyLarge,
                                    overflow = TextOverflow.Ellipsis
                                )

                            }

                        }

                    }
                }
            }

            // this serves as the divider
            Spacer(
                modifier = Modifier
                    .padding(LocalSpacing.current.small)
                    .width(LocalSpacing.current.borderStroke)
                    .background(MaterialTheme.colorScheme.onSurfaceVariant)
                    .fillMaxHeight()
            )

            Column(
                modifier = Modifier
                    .weight(3f)
                    .fillMaxHeight()
            ) {
                val scrollState = rememberScrollState(0)
                SelectLeagueText(text = SelectLeagues )
                Spacer(modifier = Modifier.height(LocalSpacing.current.default))
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .verticalScroll(state = scrollState, enabled = true),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start
                ) {
                    groupedToggledTournaments.keys.forEach { tournamentName ->

                        isChecked = checkedTournaments.contains("$toggledCountry$tournamentName")

                        val tournamentSize = groupedToggledTournaments[tournamentName]?.size ?: 0
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(LocalSpacing.current.small)
                                .toggleable(value = isChecked,
                                    onValueChange = { selected ->
                                        if (selected) {
                                            // Because different countries may have the same tournament name,
                                            // we concatenate the country name with the tournament name and use it as the key
                                            // while we use the tournament name as the value. This ensures that we get the actual tournament we selected
                                            checkedTournaments["$toggledCountry$tournamentName"] =
                                                tournamentName
                                        } else {
                                            checkedTournaments.remove("$toggledCountry$tournamentName")
                                        }
                                        // We reset the value of isChecked accordingly, to ensure that when we go to a different country, the tournament is not checked if it hasn't been actually selected
                                        isChecked =
                                            checkedTournaments.contains("$toggledCountry$tournamentName")
                                    }
                                ),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = CenterVertically
                        ) {

                            Box(modifier = Modifier
                                .padding(LocalSpacing.current.small)
                                .requiredHeight(LocalSpacing.current.checkbox)
                                .requiredWidth(LocalSpacing.current.checkbox),
                            contentAlignment = Alignment.Center){
                                Checkbox(
                                    modifier = Modifier,
                                    checked = isChecked,
                                    onCheckedChange = { selected ->
                                        if (selected) {
                                            // Because different countries may have the same tournament name,
                                            // we concatenate the country name with the tournament name and use it as the key
                                            // while we use the tournament name as the value. This ensures that we get the actual tournament we selected
                                            checkedTournaments["$toggledCountry$tournamentName"] =
                                                tournamentName
                                        } else {
                                            checkedTournaments.remove("$toggledCountry$tournamentName")
                                        }
                                        // We reset the value of isChecked accordingly, to ensure that when we go to a different country, the tournament is not checked if it hasn't been actually selected
                                        isChecked =
                                            checkedTournaments.contains("$toggledCountry$tournamentName")
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
                                    .weight(5f)
                                    .padding(horizontal = LocalSpacing.current.small),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                Text(text = tournamentName,
                                    style= MaterialTheme.typography.bodyMedium,
                                    overflow = TextOverflow.Ellipsis,
                                    maxLines = 2,
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(horizontal = LocalSpacing.current.extraSmall),
                                contentAlignment = Alignment.CenterEnd
                            ) {
                                Text(text = "$tournamentSize",
                                    style= MaterialTheme.typography.bodyLarge,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                    }
                }
            }
        }

        Divider()

        // this is the row for the Reset and Accept
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(LocalSpacing.current.small)
                .height(LocalSpacing.current.topAppBarSize),
            verticalAlignment = CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            // Reset Button
            Card(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(LocalSpacing.current.small)
                    .align(CenterVertically)
                    .clickable {
                        checkedTournaments.clear()
                        getLeagueNames(checkedTournaments)
                        closeFilter()
                    },
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.tertiary,
                    contentColor = MaterialTheme.colorScheme.onTertiary
                ),
                border = BorderStroke(LocalSpacing.current.borderStroke, MaterialTheme.colorScheme.onPrimary),
                shape = MaterialTheme.shapes.medium
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = Reset,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            // Accept Button
            Card(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(LocalSpacing.current.small)
                    .align(CenterVertically)
                    .clickable {
                        getLeagueNames(checkedTournaments)
                        closeFilter()
                    },
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                border = BorderStroke(LocalSpacing.current.borderStroke, MaterialTheme.colorScheme.primaryContainer),
                shape = MaterialTheme.shapes.medium
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = Accept,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

    }
}
