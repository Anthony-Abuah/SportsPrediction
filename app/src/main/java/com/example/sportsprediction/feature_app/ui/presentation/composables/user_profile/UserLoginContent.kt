package com.example.sportsprediction.feature_app.ui.presentation.composables.user_profile

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import com.example.sportsprediction.feature_app.ui.presentation.composables.components.CustomTextFieldWithLeadingIcon
import com.example.sportsprediction.feature_app.ui.presentation.composables.components.RegisterTipsterButton
import com.example.sportsprediction.feature_app.ui.presentation.composables.components.TipsterRegistrationQuestionsText
import com.example.sportsprediction.feature_app.ui.theme.LocalSpacing

@Composable
fun UserLoginContent(
    username: String,
    password: String,
    loginUser: (username: String, password: String) -> Unit,
    messages: List<String>?,
    navigateToHomeScreen: () -> Unit
) {
    val scrollState = rememberScrollState(0)

    val context = LocalContext.current

    var thisUsername by remember {
        mutableStateOf(username)
    }
    var thisPassword by remember {
        mutableStateOf(password)
    }

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
            Text(text = "Your Personal Details", style = MaterialTheme.typography.h1)
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
                CustomTextFieldWithLeadingIcon(
                    placeholder = "Username",
                    keyboardType = KeyboardType.Text,
                    imageVector = Icons.Default.Person
                ) {_username->
                    thisUsername = _username
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
                .wrapContentHeight(), contentAlignment = Alignment.CenterStart){
                CustomTextFieldWithLeadingIcon(
                    placeholder = "Password",
                    keyboardType = KeyboardType.Text,
                    imageVector = Icons.Default.Person
                ){_password->
                    thisPassword = _password
                }
            }

        }


        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(LocalSpacing.current.large),
            contentAlignment = Alignment.Center
        ) {
            RegisterTipsterButton(buttonName = "Login") {
                loginUser(thisUsername, thisPassword)
                navigateToHomeScreen()
                messages?.forEach { message ->
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
                }
            }
        }


    }

}