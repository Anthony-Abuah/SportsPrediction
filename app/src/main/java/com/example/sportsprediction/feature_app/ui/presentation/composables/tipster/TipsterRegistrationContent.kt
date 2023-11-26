package com.example.sportsprediction.feature_app.ui.presentation.composables.tipster

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import com.example.sportsprediction.core.util.Constants.emptyString
import com.example.sportsprediction.core.util.Functions.toDate
import com.example.sportsprediction.feature_app.data.local.entities.tipster.TipsterEntity
import com.example.sportsprediction.feature_app.data.local.entities.user_profile.UserEntity
import com.example.sportsprediction.feature_app.ui.presentation.composables.components.*
import com.example.sportsprediction.feature_app.ui.theme.LocalSpacing
import java.time.LocalDate

@Composable
fun TipsterRegistrationContent(
    getUser: (username: String, password: String) -> Unit,
    user: UserEntity?,
    registerTipster: (tipster: TipsterEntity) -> Unit,
    navigateToTipsterLoginScreen: () -> Unit
) {
    val scrollState = rememberScrollState(0)

    val context = LocalContext.current

    var userPassword by remember {
        mutableStateOf(emptyString)
    }
    var username by remember {
        mutableStateOf(emptyString)
    }
    var newPassword by remember {
        mutableStateOf(emptyString)
    }
    var confirmPassword by remember {
        mutableStateOf(emptyString)
    }
    var tipsterName by remember {
        mutableStateOf(emptyString)
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
                TipsterRegistrationQuestionsText(text = "User name")
            }
            Box(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(), contentAlignment = Alignment.CenterStart) {
                CustomTextFieldWithLeadingIcon(
                    placeholder = "Username",
                    keyboardType = KeyboardType.Text,
                    imageVector = Icons.Default.Person
                ) {_username->
                   username = _username
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
                TipsterRegistrationQuestionsText(text = "User Password")
            }
            Box(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(), contentAlignment = Alignment.CenterStart){
                CustomTextFieldWithLeadingIcon(
                    placeholder = "User Password",
                    keyboardType = KeyboardType.Text,
                    imageVector = Icons.Default.Person
                ){_userPassword->
                    userPassword = _userPassword
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
                TipsterRegistrationQuestionsText(text = "Tipster Name")
            }
            Box(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(), contentAlignment = Alignment.CenterStart){
                CustomTextFieldWithLeadingIcon(
                    placeholder = "Tipster Name",
                    keyboardType = KeyboardType.Text,
                    imageVector = Icons.Default.Person
                ){_tipsterName->
                    tipsterName = _tipsterName
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
                TipsterRegistrationQuestionsText(text = "New Password")
            }
            Box(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
                contentAlignment = Alignment.CenterStart){
                CustomTextFieldWithLeadingIcon(placeholder = "New Password",
                    keyboardType = KeyboardType.Text,
                    imageVector = Icons.Default.Lock
                ){_newPassword->
                    newPassword = _newPassword
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
                TipsterRegistrationQuestionsText(text = "Confirm Password")
            }
            Box(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
                contentAlignment = Alignment.CenterStart){
                CustomTextFieldWithLeadingIcon(
                    placeholder = "Confirm Password",
                    keyboardType = KeyboardType.Text,
                    imageVector = Icons.Default.Lock
                ){_confirmPassword->
                    confirmPassword = _confirmPassword

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
            Box(modifier = Modifier.clickable { navigateToTipsterLoginScreen() }) {
                BasicText(text = "Register", fontSize = 14.sp, textColor = Color.Blue)
            }
        }

        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(LocalSpacing.current.large),
            contentAlignment = Alignment.Center
        ) {
            RegisterTipsterButton(buttonName = "Register") {
                getUser(username, userPassword)
                val localDateNow = LocalDate.now()
                val currentDate = localDateNow.toDate()
                if(newPassword != confirmPassword){
                    Toast.makeText(context, "Passwords do not match", Toast.LENGTH_LONG).show()
                }
                else {
                    val tipster = TipsterEntity(
                        null,
                        user = user,
                        bankAccount = null,
                        tipsterName = tipsterName,
                        tipsterPassword = newPassword,
                        dateJoined = currentDate,
                        tipsterProfilePicture = null,
                        subscribers = null,
                        paidSubscribers = null,
                        posts = null,
                        rating = null,
                        tips = null,
                        reviews = null,
                        packages = null,
                        isVerified = false
                    )
                    registerTipster(tipster)
                }

            }
        }


    }

}

