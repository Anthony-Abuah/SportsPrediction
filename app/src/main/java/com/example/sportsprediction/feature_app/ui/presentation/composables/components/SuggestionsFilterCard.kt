package com.example.sportsprediction.feature_app.ui.presentation.composables.components

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import com.example.sportsprediction.feature_app.ui.theme.LocalSpacing
import com.example.sportsprediction.R
import com.example.sportsprediction.core.util.Constants.Percentage
import com.example.sportsprediction.core.util.Constants.GroupSuggestionBy
import com.example.sportsprediction.core.util.Constants.Market_Category
import com.example.sportsprediction.core.util.Constants.Market_Type
import com.example.sportsprediction.core.util.Constants.Match_Period
import com.example.sportsprediction.core.util.Constants.Order_Suggestions_By
import com.example.sportsprediction.core.util.Constants.Teams
import com.example.sportsprediction.core.util.Constants.emptyString

@Composable
fun SuggestionsFilterCard(
    percentageFilterText: String,
    groupSuggestionBy: String,
    arrangeSuggestionBy: String,
    selectedMarketCategory: String,
    selectedMarketType: String,
    selectedMatchPeriod: String,
    selectedTeams: String,
    openPercentageFilterAlertDialog: ()-> Unit,
    openSuggestionGroupingDialog: ()-> Unit,
    openSuggestionArrangementOrderAlertDialog: ()-> Unit,
    openMarketCategoryAlertDialog: ()-> Unit,
    openMarketTypeAlertDialog: ()-> Unit,
    openMatchPeriodAlertDialog: ()-> Unit,
    openTeamsAlertDialog: ()-> Unit,
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.4f)
            .background(MaterialTheme.colorScheme.background)
            .padding(LocalSpacing.current.noPadding)
            .verticalScroll(state = ScrollState(0), enabled = true),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ){
        Card(
            modifier = Modifier
                .padding(horizontal = LocalSpacing.current.noPadding)
                .wrapContentHeight()
                .fillMaxWidth(),
            elevation = CardDefaults.cardElevation(LocalSpacing.current.noElevation),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.background,
                contentColor = MaterialTheme.colorScheme.onBackground
            ),
            shape = RectangleShape
        ) {
            // Percentage
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(LocalSpacing.current.default)
                .clickable { openPercentageFilterAlertDialog() },
                verticalAlignment = Alignment.CenterVertically
            ){
                Spacer(modifier = Modifier.width(LocalSpacing.current.smallMedium))

                Box(modifier = Modifier
                    .size(LocalSpacing.current.large)
                    .padding(LocalSpacing.current.small),
                    contentAlignment = Alignment.CenterStart
                ){
                    Icon(
                        painter = painterResource(id = R.drawable.percentage),
                        modifier = Modifier,
                        contentDescription = emptyString,
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }

                Spacer(modifier = Modifier.width(LocalSpacing.current.medium))

                Box(modifier = Modifier.padding(horizontal = LocalSpacing.current.small),
                contentAlignment = Alignment.CenterStart){
                    SuggestionFilterCardText(text = Percentage)
                }

                Spacer(modifier = Modifier.width(LocalSpacing.current.small))

                // the Dot composable
                Box(modifier = Modifier
                    .size(LocalSpacing.current.small)
                    .background(MaterialTheme.colorScheme.onBackground, CircleShape)
                    .padding(horizontal = LocalSpacing.current.default),
                    contentAlignment = Alignment.CenterStart){
                    SuggestionFilterCardText(text = Percentage)
                }

                Spacer(modifier = Modifier.width(LocalSpacing.current.small))

                Box(modifier = Modifier.padding(horizontal = LocalSpacing.current.small),
                contentAlignment = Alignment.CenterStart){
                    SuggestionFilterCardValueText(text = percentageFilterText)
                }


            }

            // Group Suggestions
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(LocalSpacing.current.default)
                .clickable { openSuggestionGroupingDialog() },
                verticalAlignment = Alignment.CenterVertically
            ){
                Spacer(modifier = Modifier.width(LocalSpacing.current.smallMedium))

                Box(modifier = Modifier
                    .size(LocalSpacing.current.large)
                    .padding(LocalSpacing.current.small),
                    contentAlignment = Alignment.CenterStart
                ){
                    Icon(
                        painter = painterResource(id = R.drawable.group),
                        modifier = Modifier,
                        contentDescription = emptyString,
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }

                Spacer(modifier = Modifier.width(LocalSpacing.current.medium))

                Box(modifier = Modifier.padding(horizontal = LocalSpacing.current.small),
                    contentAlignment = Alignment.CenterStart){
                    SuggestionFilterCardText(text = GroupSuggestionBy)
                }

                Spacer(modifier = Modifier.width(LocalSpacing.current.small))

                // the Dot composable
                Box(modifier = Modifier
                    .size(LocalSpacing.current.small)
                    .background(MaterialTheme.colorScheme.onBackground, CircleShape)
                    .padding(horizontal = LocalSpacing.current.default),
                    contentAlignment = Alignment.CenterStart){
                    SuggestionFilterCardText(text = GroupSuggestionBy)
                }

                Spacer(modifier = Modifier.width(LocalSpacing.current.small))

                Box(modifier = Modifier.padding(horizontal = LocalSpacing.current.small),
                    contentAlignment = Alignment.CenterStart){
                    SuggestionFilterCardValueText(text = groupSuggestionBy)
                }


            }

            // Order Suggestions
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(LocalSpacing.current.default)
                .clickable { openSuggestionArrangementOrderAlertDialog() },
                verticalAlignment = Alignment.CenterVertically
            ){
                Spacer(modifier = Modifier.width(LocalSpacing.current.smallMedium))

                Box(modifier = Modifier
                    .size(LocalSpacing.current.large)
                    .padding(LocalSpacing.current.small),
                    contentAlignment = Alignment.CenterStart
                ){
                    Icon(
                        painter = painterResource(id = R.drawable.order),
                        modifier = Modifier,
                        contentDescription = emptyString,
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }

                Spacer(modifier = Modifier.width(LocalSpacing.current.medium))

                Box(modifier = Modifier.padding(horizontal = LocalSpacing.current.small),
                    contentAlignment = Alignment.CenterStart){
                    SuggestionFilterCardText(text = Order_Suggestions_By)
                }

                Spacer(modifier = Modifier.width(LocalSpacing.current.small))

                // the Dot composable
                Box(modifier = Modifier
                    .size(LocalSpacing.current.small)
                    .background(MaterialTheme.colorScheme.onBackground, CircleShape)
                    .padding(horizontal = LocalSpacing.current.default),
                    contentAlignment = Alignment.CenterStart){
                    SuggestionFilterCardText(text = Percentage)
                }

                Spacer(modifier = Modifier.width(LocalSpacing.current.small))

                Box(modifier = Modifier.padding(horizontal = LocalSpacing.current.small),
                    contentAlignment = Alignment.CenterStart){
                    SuggestionFilterCardValueText(text = arrangeSuggestionBy)
                }


            }

            Divider()

            // Market Category
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(LocalSpacing.current.default)
                .clickable { openMarketCategoryAlertDialog() },
                verticalAlignment = Alignment.CenterVertically
            ){
                Spacer(modifier = Modifier.width(LocalSpacing.current.smallMedium))

                Box(modifier = Modifier
                    .size(LocalSpacing.current.large)
                    .padding(LocalSpacing.current.small),
                    contentAlignment = Alignment.CenterStart
                ){
                    Icon(
                        painter = painterResource(id = R.drawable.market_category),
                        modifier = Modifier,
                        contentDescription = emptyString,
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }

                Spacer(modifier = Modifier.width(LocalSpacing.current.medium))

                Box(modifier = Modifier.padding(horizontal = LocalSpacing.current.small),
                    contentAlignment = Alignment.CenterStart){
                    SuggestionFilterCardText(text = Market_Category)
                }

                Spacer(modifier = Modifier.width(LocalSpacing.current.small))

                // the Dot composable
                Box(modifier = Modifier
                    .size(LocalSpacing.current.small)
                    .background(MaterialTheme.colorScheme.onBackground, CircleShape)
                    .padding(horizontal = LocalSpacing.current.default),
                    contentAlignment = Alignment.CenterStart){
                    SuggestionFilterCardText(text = Percentage)
                }

                Spacer(modifier = Modifier.width(LocalSpacing.current.small))

                Box(modifier = Modifier.padding(horizontal = LocalSpacing.current.small),
                    contentAlignment = Alignment.CenterStart){
                    SuggestionFilterCardValueText(text = selectedMarketCategory)
                }


            }

            // Market Type
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(LocalSpacing.current.default)
                .clickable { openMarketTypeAlertDialog() },
                verticalAlignment = Alignment.CenterVertically
            ){
                Spacer(modifier = Modifier.width(LocalSpacing.current.smallMedium))

                Box(modifier = Modifier
                    .size(LocalSpacing.current.large)
                    .padding(LocalSpacing.current.small),
                    contentAlignment = Alignment.CenterStart
                ){
                    Icon(
                        painter = painterResource(id = R.drawable.market_category),
                        modifier = Modifier,
                        contentDescription = emptyString,
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }

                Spacer(modifier = Modifier.width(LocalSpacing.current.medium))

                Box(modifier = Modifier.padding(horizontal = LocalSpacing.current.small),
                    contentAlignment = Alignment.CenterStart){
                    SuggestionFilterCardText(text = Market_Type)
                }

                Spacer(modifier = Modifier.width(LocalSpacing.current.small))

                // the Dot composable
                Box(modifier = Modifier
                    .size(LocalSpacing.current.small)
                    .background(MaterialTheme.colorScheme.onBackground, CircleShape)
                    .padding(horizontal = LocalSpacing.current.default),
                    contentAlignment = Alignment.CenterStart){
                    SuggestionFilterCardText(text = Percentage)
                }

                Spacer(modifier = Modifier.width(LocalSpacing.current.small))

                Box(modifier = Modifier.padding(horizontal = LocalSpacing.current.small),
                    contentAlignment = Alignment.CenterStart){
                    SuggestionFilterCardValueText(text = selectedMarketType)
                }
            }

            // Match Period
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(LocalSpacing.current.default)
                .clickable { openMatchPeriodAlertDialog() },
                verticalAlignment = Alignment.CenterVertically
            ){
                Spacer(modifier = Modifier.width(LocalSpacing.current.smallMedium))

                Box(modifier = Modifier
                    .size(LocalSpacing.current.large)
                    .padding(LocalSpacing.current.small),
                    contentAlignment = Alignment.CenterStart
                ){
                    Icon(
                        painter = painterResource(id = R.drawable.market_category),
                        modifier = Modifier,
                        contentDescription = emptyString,
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }

                Spacer(modifier = Modifier.width(LocalSpacing.current.medium))

                Box(modifier = Modifier.padding(horizontal = LocalSpacing.current.small),
                    contentAlignment = Alignment.CenterStart){
                    SuggestionFilterCardText(text = Match_Period)
                }

                Spacer(modifier = Modifier.width(LocalSpacing.current.small))

                // the Dot composable
                Box(modifier = Modifier
                    .size(LocalSpacing.current.small)
                    .background(MaterialTheme.colorScheme.onBackground, CircleShape)
                    .padding(horizontal = LocalSpacing.current.default),
                    contentAlignment = Alignment.CenterStart){
                    SuggestionFilterCardText(text = Percentage)
                }

                Spacer(modifier = Modifier.width(LocalSpacing.current.small))

                Box(modifier = Modifier.padding(horizontal = LocalSpacing.current.small),
                    contentAlignment = Alignment.CenterStart){
                    SuggestionFilterCardValueText(text = selectedMatchPeriod)
                }


            }

            // Teams
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(LocalSpacing.current.default)
                .clickable { openTeamsAlertDialog() },
                verticalAlignment = Alignment.CenterVertically
            ){
                Spacer(modifier = Modifier.width(LocalSpacing.current.smallMedium))

                Box(modifier = Modifier
                    .size(LocalSpacing.current.large)
                    .padding(LocalSpacing.current.small),
                    contentAlignment = Alignment.CenterStart
                ){
                    Icon(
                        painter = painterResource(id = R.drawable.market_category),
                        modifier = Modifier,
                        contentDescription = emptyString,
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }

                Spacer(modifier = Modifier.width(LocalSpacing.current.medium))

                Box(modifier = Modifier.padding(horizontal = LocalSpacing.current.small),
                    contentAlignment = Alignment.CenterStart){
                    SuggestionFilterCardText(text = Teams)
                }

                Spacer(modifier = Modifier.width(LocalSpacing.current.small))

                // the Dot composable
                Box(modifier = Modifier
                    .size(LocalSpacing.current.small)
                    .background(MaterialTheme.colorScheme.onBackground, CircleShape)
                    .padding(horizontal = LocalSpacing.current.default),
                    contentAlignment = Alignment.CenterStart){
                    SuggestionFilterCardText(text = Percentage)
                }

                Spacer(modifier = Modifier.width(LocalSpacing.current.small))

                Box(modifier = Modifier.padding(horizontal = LocalSpacing.current.small),
                    contentAlignment = Alignment.CenterStart){
                    SuggestionFilterCardValueText(text = selectedTeams)
                }


            }


        }

    }
}
