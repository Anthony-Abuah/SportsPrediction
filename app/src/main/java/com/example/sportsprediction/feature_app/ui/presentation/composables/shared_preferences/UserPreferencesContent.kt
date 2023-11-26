package com.example.sportsprediction.feature_app.ui.presentation.composables.shared_preferences

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import com.example.sportsprediction.R
import com.example.sportsprediction.core.util.Constants.ArrangementOrder
import com.example.sportsprediction.core.util.Constants.ArrangementOrderList
import com.example.sportsprediction.core.util.Constants.Descending
import com.example.sportsprediction.core.util.Constants.Market_Type
import com.example.sportsprediction.core.util.Constants.NumberOfHeadToHeadEvents
import com.example.sportsprediction.core.util.Constants.NumberOfPastEvents
import com.example.sportsprediction.core.util.Constants.Past_Events
import com.example.sportsprediction.core.util.Constants.Past_Head_To_Head_Events
import com.example.sportsprediction.core.util.Constants.PercentageFilter
import com.example.sportsprediction.core.util.Constants.Percentage_Filter
import com.example.sportsprediction.core.util.Constants.SportsPredictionPreferences
import com.example.sportsprediction.core.util.Constants.SuggestionGroupOption
import com.example.sportsprediction.core.util.Constants.SuggestionGroupingList
import com.example.sportsprediction.core.util.Constants.Suggestions_Arrangement_Order
import com.example.sportsprediction.core.util.Constants.Suggestions_Grouping
import com.example.sportsprediction.core.util.UserPreferences
import com.example.sportsprediction.feature_app.ui.presentation.composables.components.AlertDialogTextField
import com.example.sportsprediction.feature_app.ui.presentation.composables.components.BasicScreenColumn
import com.example.sportsprediction.feature_app.ui.presentation.composables.components.SimpleRadioButtonComponent
import com.example.sportsprediction.feature_app.ui.theme.LocalSpacing
import kotlinx.coroutines.launch


@Composable
fun UserPreferencesContent(
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val userPreferences = UserPreferences(context)

    val sportPredictionPreferences = context.getSharedPreferences(SportsPredictionPreferences, Context.MODE_PRIVATE)
    val editor = sportPredictionPreferences?.edit()


    val suggestionsArrangement = sportPredictionPreferences.getString(ArrangementOrder, Descending) ?: Descending
    val suggestionGrouping = sportPredictionPreferences.getString(SuggestionGroupOption, Market_Type) ?: Market_Type
    val percentageFilterValue = sportPredictionPreferences.getString(PercentageFilter, "50") ?: "50"
    val numberOfPastEvents = sportPredictionPreferences.getString(NumberOfPastEvents, "6") ?: "6"
    val numberOfHeadToHeadEvents = sportPredictionPreferences.getString(NumberOfHeadToHeadEvents, "6") ?: "4"

    var newPercentageValue by remember {
        mutableStateOf(percentageFilterValue)
    }

    var typedInNumberOfPastEvents by remember {
        mutableStateOf(numberOfPastEvents)
    }

    var typedInNumberOfHeadToHeadEvents by remember {
        mutableStateOf(numberOfHeadToHeadEvents)
    }

    var typedInPercentageValue by remember {
        mutableStateOf(newPercentageValue)
    }

    var selectedSuggestionGroupOption by remember {
        mutableStateOf(suggestionGrouping)
    }
    var selectedSuggestionArrangementOrder by remember {
        mutableStateOf(suggestionsArrangement)
    }
    var newNumberOfPastEvents by remember {
        mutableStateOf(numberOfPastEvents)
    }
    var newNumberOfPastHeadToHeadEvents by remember {
        mutableStateOf(numberOfHeadToHeadEvents)
    }


    BasicScreenColumn {
        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(LocalSpacing.current.topAppBarSize))

            UserPreferenceCard(
                title = Past_Events,
                userPreference = Past_Events,
                displayValue = "Take last $newNumberOfPastEvents matches",
                imageVector = R.drawable.football,
                getValue = {
                    newNumberOfPastEvents = try {
                        if (typedInNumberOfPastEvents.toDouble().isNaN()) "6"
                        else if (typedInNumberOfPastEvents.toDouble() < 1.0) "6"
                        else if (typedInNumberOfPastEvents.toDouble() > 10.0) "6"
                        else typedInNumberOfPastEvents
                    } catch (e: NumberFormatException) { "6" }

                    editor?.apply {
                        putString(NumberOfPastEvents, newNumberOfPastEvents)
                        apply()
                    }
                    Toast.makeText(context, "$newNumberOfPastEvents is selected", Toast.LENGTH_LONG).show()
                }
            ) {
                AlertDialogTextField(
                    oldValue = newNumberOfPastEvents,
                    keyboardType = KeyboardType.Number,
                    getNewValue = { value ->
                        typedInNumberOfPastEvents = value
                    },
                )
            }

            UserPreferenceCard(
                title = Past_Head_To_Head_Events,
                userPreference = Past_Head_To_Head_Events,
                displayValue = "Take last $newNumberOfPastHeadToHeadEvents matches",
                imageVector = R.drawable.football,
                getValue = {
                    newNumberOfPastHeadToHeadEvents = try {
                        if (typedInNumberOfHeadToHeadEvents.toDouble().isNaN()) "4"
                        else if (typedInNumberOfHeadToHeadEvents.toDouble() < 1.0) "4"
                        else if (typedInNumberOfHeadToHeadEvents.toDouble() > 10.0) "4"
                        else typedInNumberOfHeadToHeadEvents
                    } catch (e: NumberFormatException) { "4" }

                    editor?.apply {
                        putString(NumberOfHeadToHeadEvents, newNumberOfPastHeadToHeadEvents)
                        apply()
                    }
                    Toast.makeText(context, "$newNumberOfPastHeadToHeadEvents is selected", Toast.LENGTH_LONG).show()
                }
            ) {
                AlertDialogTextField(
                    oldValue = newNumberOfPastHeadToHeadEvents,
                    keyboardType = KeyboardType.Number,
                    getNewValue = { value ->
                        typedInNumberOfHeadToHeadEvents = value
                    },
                )
            }

            UserPreferenceCard(
                title = Percentage_Filter,
                userPreference = Percentage_Filter,
                displayValue = "${newPercentageValue.take(5)}%",
                imageVector = R.drawable.football,
                getValue = {
                    newPercentageValue = try {
                        if (typedInPercentageValue.toDouble().isNaN()) "50"
                        else if (typedInPercentageValue.toDouble() < 1.0) typedInPercentageValue.toDouble().times(100.0).toString()
                        else if (typedInPercentageValue.toDouble() > 100.0) "100"
                        else typedInPercentageValue
                    } catch (e: NumberFormatException) { "50" }

                    editor?.apply {
                        putString(PercentageFilter,newPercentageValue)
                        apply()
                    }
                    Toast.makeText(context, "$newPercentageValue is selected", Toast.LENGTH_LONG).show()
                }
            ) {
                AlertDialogTextField(
                    oldValue = newPercentageValue,
                    keyboardType = KeyboardType.Decimal,
                    getNewValue = { value ->
                        typedInPercentageValue = value
                    },
                )
            }



            UserPreferenceCard(
                title = Suggestions_Grouping,
                userPreference = Suggestions_Grouping,
                displayValue = selectedSuggestionGroupOption,
                imageVector = R.drawable.football,
                getValue = {
                    editor?.apply {
                        putString(SuggestionGroupOption,selectedSuggestionGroupOption)
                        apply()
                    }
                    Toast.makeText(
                        context,
                        "$selectedSuggestionGroupOption is selected",
                        Toast.LENGTH_LONG
                    ).show()
                },
            ) {
                SimpleRadioButtonComponent(
                    radioOptions = SuggestionGroupingList,
                    oldSelectedOption = suggestionGrouping,
                    getOptionSelected = { selectedOption ->
                        selectedSuggestionGroupOption = selectedOption
                    },
                )
            }




            UserPreferenceCard(
                title = Suggestions_Arrangement_Order,
                userPreference = Suggestions_Arrangement_Order,
                displayValue = selectedSuggestionArrangementOrder,
                imageVector = R.drawable.football,
                getValue = {
                    coroutineScope.launch {
                        userPreferences.saveSuggestionArrangementOrder(
                            selectedSuggestionArrangementOrder
                        )
                    }
                    editor?.apply {
                        putString(ArrangementOrder,selectedSuggestionArrangementOrder)
                        apply()
                    }
                    Toast.makeText(
                        context,
                        "$selectedSuggestionArrangementOrder is selected",
                        Toast.LENGTH_LONG
                    ).show()
                },
            ) {
                SimpleRadioButtonComponent(
                    radioOptions = ArrangementOrderList,
                    oldSelectedOption = suggestionsArrangement,
                    getOptionSelected = { selectedOption ->
                        selectedSuggestionArrangementOrder = selectedOption
                    },
                )
            }


        }
    }

}