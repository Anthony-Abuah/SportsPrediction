package com.example.sportsprediction.feature_app.ui.presentation.composables.components

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sportsprediction.core.util.Constants.emptyString
import com.example.sportsprediction.feature_app.data.local.entities.events.EventsEntity
import com.example.sportsprediction.feature_app.ui.theme.LocalSpacing
import com.example.sportsprediction.feature_app.ui.theme.PrimaryThemeColor
import com.example.sportsprediction.feature_app.ui.theme.Shapes

@Composable
fun AlertDialogCheckboxPage (
    countries: Map<String, List<EventsEntity>>,
    theSelectedTournaments: Map<String, String>,
    closeFilter: () -> Unit,
    getLeagueNames: (selectedValues: Map<String, String>) -> Unit
) {
    val selectedTournaments by remember { mutableStateOf(theSelectedTournaments.toMutableMap()) }

    var selectedCountry by remember { mutableStateOf(emptyString) }
    var countryIsSelected by remember { mutableStateOf(false ) }
    var tournaments = if (selectedCountry.isNotEmpty()) countries[selectedCountry] else emptyList()
    var isChecked by remember { mutableStateOf(false)
    }

    var groupedTournaments = tournaments?.groupBy { it.tournamentName ?: "Unknown tournament" } ?: emptyMap()

    Column {

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
                SelectLeagueText(text = "Countries")
                Spacer(modifier = Modifier.height(LocalSpacing.current.default))
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .verticalScroll(state = scrollState, enabled = true),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start
                ) {
                    countries.forEach { country ->
                        val countrySize = country.value.size
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    isChecked = false
                                    countryIsSelected = selectedCountry == country.key
                                    selectedCountry = country.key
                                    tournaments = countries[country.key] ?: emptyList()
                                    groupedTournaments = tournaments?.groupBy {
                                        it.tournamentName ?: "Unknown tournament"
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
                                val thisCountry = if (country.key.length > 15) {
                                    "${country.key.take(12)}..."
                                } else country.key
                                BasicText(
                                    text = thisCountry,
                                    fontSize = 16.sp,
                                    textColor = Color.Black
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(LocalSpacing.current.extraSmall),
                                contentAlignment = Alignment.Center
                            ) {
                                BasicText(
                                    text = "$countrySize",
                                    fontSize = 16.sp,
                                    textColor = Color.Black
                                )
                            }

                        }
                    }
                }
            }

            Spacer(
                modifier = Modifier
                    .padding(LocalSpacing.current.small)
                    .width(1.dp)
                    .background(Color.LightGray)
                    .fillMaxHeight()
            )

            Column(
                modifier = Modifier
                    .weight(3f)
                    .fillMaxHeight()
            ) {
                val scrollState = rememberScrollState(0)
                SelectLeagueText(text = "Select League" )
                Spacer(modifier = Modifier.height(LocalSpacing.current.default))
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .verticalScroll(state = scrollState, enabled = true),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start
                ) {
                    groupedTournaments.keys.forEach { tournamentName ->

                        isChecked = selectedTournaments.contains("$selectedCountry$tournamentName") 

                        val tournamentSize = groupedTournaments[tournamentName]?.size ?: 0
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(LocalSpacing.current.small)
                                .toggleable(value = isChecked,
                                    onValueChange = { selected ->
                                        //isChecked = selected
                                        if (selected) {
                                            selectedTournaments["$selectedCountry$tournamentName"] = tournamentName
                                            selectedTournaments.forEach { tourney ->
                                                Log.d(
                                                    "AlertDialogCheckbox",
                                                    "selectedCountry: ${tourney.key}\nselectedTournament: ${tourney.value}"
                                                )
                                            }
                                        } else {
                                            selectedTournaments.remove("$selectedCountry$tournamentName")

                                            if (selectedTournaments.isNotEmpty()) {
                                                selectedTournaments.forEach { tourney ->
                                                    Log.d(
                                                        "AlertDialogCheckbox",
                                                        "selectedCountry: ${tourney.key}\nselectedTournament: ${tourney.value}"
                                                    )
                                                }
                                            } else {
                                                Log.d(
                                                    "AlertDialogCheckbox",
                                                    "selectedTournament.minus(): empty map"
                                                )
                                            }
                                        }
                                        isChecked = selectedTournaments.contains("$selectedCountry$tournamentName")

                                    }
                                ),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .width(LocalSpacing.current.medium)
                                    .height(LocalSpacing.current.medium)
                                    .padding(
                                        start = LocalSpacing.current.small,
                                        end = LocalSpacing.current.smallMedium
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Checkbox(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(Color.Transparent, Shapes.small),
                                    checked = isChecked,
                                    onCheckedChange = { selected ->
                                        if (selected) {selectedTournaments["$selectedCountry$tournamentName"] = tournamentName
                                            selectedTournaments.forEach { tourney ->
                                                Log.d(
                                                    "AlertDialogCheckbox",
                                                    "selectedCountry: ${tourney.key}\nselectedTournament: ${tourney.value}"
                                                )
                                            }
                                        } else {
                                            selectedTournaments.remove("$selectedCountry$tournamentName")

                                            if (selectedTournaments.isNotEmpty()) {
                                                selectedTournaments.forEach { tourney ->
                                                    Log.d(
                                                        "AlertDialogCheckbox",
                                                        "selectedCountry: ${tourney.key}\nselectedTournament: ${tourney.value}"
                                                    )
                                                }
                                            } else {
                                                Log.d(
                                                    "AlertDialogCheckbox",
                                                    "selectedTournament.minus(): empty map"
                                                )
                                            }
                                        }
                                        isChecked = selectedTournaments.contains("$selectedCountry$tournamentName")

                                    },
                                    colors = CheckboxDefaults.colors(
                                        checkedColor = PrimaryThemeColor,
                                        uncheckedColor = PrimaryThemeColor,
                                        checkmarkColor = Color.White
                                    )
                                )
                            }

                            Box(
                                modifier = Modifier
                                    .weight(5f)
                                    .padding(horizontal = LocalSpacing.current.small),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                val thisTournamentName = if (tournamentName.length > 23) {
                                    "${tournamentName.take(18)}..."
                                } else tournamentName
                                BasicText(
                                    text = thisTournamentName,
                                    fontSize = 16.sp,
                                    textColor = Color.Black
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(horizontal = LocalSpacing.current.small),
                                contentAlignment = Alignment.CenterEnd
                            ) {
                                BasicText(
                                    text = "$tournamentSize",
                                    fontSize = 16.sp,
                                    textColor = Color.Black
                                )
                            }

                        }

                    }
                }
            }

        }


        Divider()

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(LocalSpacing.current.small)
                .height(LocalSpacing.current.topAppBarSize),
            verticalAlignment = CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Card(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(LocalSpacing.current.small)
                    .align(CenterVertically)
                    .clickable {
                        selectedTournaments.clear()
                        if (selectedTournaments.isEmpty())
                            Log.d("AlertDialogCheckbox", "selectedTournament.clear() is empty")
                        else selectedTournaments.forEach { tourney ->
                            Log.d(
                                "AlertDialogCheckbox",
                                "selectedTournament.clear(): $tourney"
                            )
                        }
                    },
                colors = CardDefaults.cardColors(
                    containerColor = Color.White,
                    contentColor = PrimaryThemeColor
                ),
                border = BorderStroke(LocalSpacing.current.extraSmall, PrimaryThemeColor),
                shape = Shapes.small
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    BasicText(text = "Reset", fontSize = 16.sp, textColor = PrimaryThemeColor)
                }
            }
            Card(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(LocalSpacing.current.small)
                    .align(CenterVertically)
                    .clickable {
                        getLeagueNames(selectedTournaments)
                        closeFilter()
                    },
                colors = CardDefaults.cardColors(
                    containerColor = PrimaryThemeColor,
                    contentColor = Color.White
                ),
                border = BorderStroke(LocalSpacing.current.extraSmall, PrimaryThemeColor),
                shape = Shapes.small
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    BasicText(text = "Accept", fontSize = 16.sp, textColor = Color.White)
                }
            }
        }

    }
}
