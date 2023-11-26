package com.example.sportsprediction.feature_app.ui.presentation.composables.tipster

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import com.example.sportsprediction.core.util.Constants.DaysOfTheMonthList
import com.example.sportsprediction.core.util.Constants.GenderList
import com.example.sportsprediction.core.util.Constants.Male
import com.example.sportsprediction.core.util.Constants.MonthOfTheYearList
import com.example.sportsprediction.core.util.Constants.YearList
import com.example.sportsprediction.feature_app.ui.presentation.composables.components.*
import com.example.sportsprediction.feature_app.ui.theme.LocalSpacing
import com.example.sportsprediction.feature_app.ui.theme.MainBackgroundColor

@Composable
fun TipsterLoginContent(
    navigateToRegisterTipsterScreen: () -> Unit
) {
    val scrollState = rememberScrollState(0)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(bottom = LocalSpacing.current.topAppBarSize)
            .verticalScroll(state = scrollState, enabled = true)
    ) {

        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = LocalSpacing.current.large,
                vertical = LocalSpacing.current.extraLarge
            ),
            contentAlignment = Alignment.CenterStart) {
            Text(text = "Login into your Account", style = MaterialTheme.typography.h1)
        }

        Column(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(
                horizontal = LocalSpacing.current.large,
                vertical = LocalSpacing.current.semiLarge
            )
        ) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = LocalSpacing.current.medium),
                contentAlignment = Alignment.CenterStart){
                TipsterRegistrationQuestionsText(text = "Username")
            }
            Box(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(), contentAlignment = Alignment.CenterStart) {
                CustomTextField(placeholder = "Username", keyboardType = KeyboardType.Text) {

                }
            }
        }


        Column(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(
                horizontal = LocalSpacing.current.large,
                vertical = LocalSpacing.current.semiLarge
            )) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = LocalSpacing.current.medium),
                contentAlignment = Alignment.CenterStart){
                TipsterRegistrationQuestionsText(text = "Email")
            }
            Box(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(), contentAlignment = Alignment.CenterStart){
                CustomTextField(placeholder = "Email", keyboardType = KeyboardType.Email){

                }
            }

        }


        Column(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(
                horizontal = LocalSpacing.current.large,
                vertical = LocalSpacing.current.semiLarge
            )) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = LocalSpacing.current.medium),
                contentAlignment = Alignment.CenterStart){
                TipsterRegistrationQuestionsText(text = "Password")
            }
            Box(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
                contentAlignment = Alignment.CenterStart){
                CustomTextFieldWithLeadingIcon(
                    placeholder = "Password",
                    keyboardType = KeyboardType.Text,
                    imageVector = Icons.Default.Lock
                ){

                }
            }

        }

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(LocalSpacing.current.large),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BasicText(text = "Have not Registered?", fontSize = 14.sp, textColor = Color.Blue)
            Spacer(modifier = Modifier.width(LocalSpacing.current.small))
            Box(modifier = Modifier.clickable { navigateToRegisterTipsterScreen() }) {
                BasicText(text = "Register", fontSize = 14.sp, textColor = Color.Blue)
            }
        }


        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(LocalSpacing.current.large),
            contentAlignment = Alignment.Center
        ) {
            RegisterTipsterButton(buttonName = "Register") {}
        }


    }

}