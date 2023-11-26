package com.example.sportsprediction.feature_app.ui.presentation.composables.user_profile

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import com.example.sportsprediction.core.util.Constants
import com.example.sportsprediction.core.util.Functions.toDate
import com.example.sportsprediction.feature_app.data.local.entities.user_profile.UserEntity
import com.example.sportsprediction.feature_app.ui.presentation.composables.components.*
import com.example.sportsprediction.feature_app.ui.theme.LocalSpacing
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun RegisterUserContent(
    registerUser: (user: UserEntity) -> Unit,
    navigateToTipsterLoginScreen: (username: String, password: String) -> Unit
) {
    val scrollState = rememberScrollState(0)

    val context = LocalContext.current

    var firstName by remember {
        mutableStateOf(Constants.emptyString)
    }
    var lastName by remember {
        mutableStateOf(Constants.emptyString)
    }
    var email by remember {
        mutableStateOf(Constants.emptyString)
    }
    var username by remember {
        mutableStateOf(Constants.emptyString)
    }
    var newPassword by remember {
        mutableStateOf(Constants.emptyString)
    }
    var confirmPassword by remember {
        mutableStateOf(Constants.emptyString)
    }
    var gender by remember {
        mutableStateOf(Constants.emptyString)
    }
    var day by remember {
        mutableStateOf(Constants.emptyString)
    }
    var month by remember {
        mutableStateOf(Constants.emptyString)
    }
    var year by remember {
        mutableStateOf(Constants.emptyString)
    }
    var country by remember {
        mutableStateOf(Constants.emptyString)
    }
    var countryCode by remember {
        mutableStateOf(Constants.emptyString)
    }
    var phoneNumber by remember {
        mutableStateOf(Constants.emptyString)
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
                TipsterRegistrationQuestionsText(text = "First Name")
            }
            Box(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(), contentAlignment = Alignment.CenterStart) {
                CustomTextFieldWithLeadingIcon(
                    placeholder = "First Name",
                    keyboardType = KeyboardType.Text,
                    imageVector = Icons.Default.Person
                ) {_firstName->
                    firstName = _firstName
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
                TipsterRegistrationQuestionsText(text = "Last Name")
            }
            Box(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(), contentAlignment = Alignment.CenterStart){
                CustomTextFieldWithLeadingIcon(
                    placeholder = "Last Name",
                    keyboardType = KeyboardType.Text,
                    imageVector = Icons.Default.Person
                ){_lastName->
                    lastName = _lastName
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
                CustomTextFieldWithLeadingIcon(placeholder = "Email",
                    keyboardType = KeyboardType.Email,
                    imageVector = Icons.Default.Email
                ){_email->
                    email = _email
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
                TipsterRegistrationQuestionsText(text = "Username")
            }
            Box(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(), contentAlignment = Alignment.CenterStart){
                CustomTextFieldWithLeadingIcon(
                    placeholder = "Username",
                    keyboardType = KeyboardType.Text,
                    imageVector = Icons.Default.Person
                ){_username->
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


        Column(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(
                horizontal = LocalSpacing.current.large,
                vertical = LocalSpacing.current.semiLarge
            )) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = LocalSpacing.current.small),
                contentAlignment = Alignment.CenterStart){
                TipsterRegistrationQuestionsText(text = "Gender")
            }
            BasicRadioButtonsComponent(
                oldSelectedOption = Constants.Male,
                radioOptions = Constants.GenderList
            ) {selectedGender->
                gender = selectedGender
            }

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
                TipsterRegistrationQuestionsText(text = "Date Of Birth")
            }

            Row(modifier = Modifier.fillMaxWidth()) {

                Column(modifier = Modifier
                    .weight(1f)
                    .wrapContentHeight()
                    .padding(
                        end = LocalSpacing.current.small,
                        top = LocalSpacing.current.small,
                        bottom = LocalSpacing.current.small
                    )
                ) {
                    Box(modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center){
                        TipsterRegistrationQuestionsText(text = "Day")
                    }
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                        contentAlignment = Alignment.CenterStart
                    ){
                        AutoCompleteTextField(
                            placeholderText = "01",
                            listItems = Constants.DaysOfTheMonthList,
                            keyboardType = KeyboardType.Decimal
                        ){_day->
                            day = _day
                        }
                    }
                }


                Column(modifier = Modifier
                    .weight(1.5f)
                    .wrapContentHeight()
                    .padding(LocalSpacing.current.small)
                ) {
                    Box(modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center){
                        TipsterRegistrationQuestionsText(text = "Month")
                    }
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                        contentAlignment = Alignment.CenterStart
                    ){
                        AutoCompleteTextField(
                            placeholderText = "Jan",
                            listItems = Constants.MonthOfTheYearList,
                            keyboardType = KeyboardType.Text
                        ){_month->
                            month = _month
                        }
                    }
                }

                Column(modifier = Modifier
                    .weight(2f)
                    .wrapContentHeight()
                    .padding(
                        start = LocalSpacing.current.small,
                        top = LocalSpacing.current.small,
                        bottom = LocalSpacing.current.small
                    )
                ) {
                    Box(modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center){
                        TipsterRegistrationQuestionsText(text = "Year")
                    }
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                        contentAlignment = Alignment.CenterStart
                    ){
                        AutoCompleteTextField(
                            placeholderText = "1997",
                            listItems = Constants.YearList,
                            keyboardType = KeyboardType.Decimal
                        ){_year->
                            year = _year
                        }
                    }
                }

            }
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
                TipsterRegistrationQuestionsText(text = "Country")
            }
            Box(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
                contentAlignment = Alignment.CenterStart
            ){
                AutoCompleteTextField(
                    placeholderText = "Country",
                    listItems = Constants.MonthOfTheYearList,
                    keyboardType = KeyboardType.Text
                ){_country->
                    country = _country
                }
            }
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
                TipsterRegistrationQuestionsText(text = "Phone Number")
            }

            Row(modifier = Modifier.fillMaxWidth()) {

                Column(modifier = Modifier
                    .weight(1f)
                    .wrapContentHeight()
                    .padding(
                        end = LocalSpacing.current.small,
                        top = LocalSpacing.current.small,
                        bottom = LocalSpacing.current.small
                    )
                ) {
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                        contentAlignment = Alignment.CenterStart
                    ){
                        AutoCompleteTextField(
                            placeholderText = "+233",
                            listItems = Constants.MonthOfTheYearList,
                            keyboardType = KeyboardType.Decimal
                        ){_countryCode->
                            countryCode = _countryCode
                        }
                    }
                }


                Column(modifier = Modifier
                    .weight(2.5f)
                    .wrapContentHeight()
                    .padding(
                        start = LocalSpacing.current.small,
                        top = LocalSpacing.current.small,
                        bottom = LocalSpacing.current.small
                    )
                ) {
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                        contentAlignment = Alignment.CenterStart
                    ){
                        CustomTextFieldWithLeadingIcon(
                            placeholder = "500912348",
                            keyboardType = KeyboardType.Phone,
                            imageVector = Icons.Default.Phone
                        ){_phoneNumber->
                            phoneNumber = _phoneNumber
                        }
                    }
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
            Box(modifier = Modifier.clickable { navigateToTipsterLoginScreen(username, newPassword) }) {
                BasicText(text = "Register", fontSize = 14.sp, textColor = Color.Blue)
            }
        }

        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(LocalSpacing.current.large),
            contentAlignment = Alignment.Center
        ) {
            RegisterTipsterButton(buttonName = "Register") {
                val shortDateFormatter = DateTimeFormatter.ofPattern(Constants.shortDateFormat)
                val monthOfBirth = Constants.MonthOfTheYearList.indexOf(month).plus(1)
                val theMonthOfBirth = if(monthOfBirth<10) "0$monthOfBirth" else "$monthOfBirth"
                val dayOfBirth = day.toInt()
                val theDayOfBirth = if(dayOfBirth<10) "0$dayOfBirth" else "$dayOfBirth"
                val yearOfBirth = year
                val dateOfBirth = "$yearOfBirth-$theMonthOfBirth-$theDayOfBirth"
                val localDateNow = LocalDate.now()
                val localDateOfBirth = LocalDate.parse(dateOfBirth, shortDateFormatter)
                val newDateOfBirth = localDateOfBirth.toDate()
                val currentDate = localDateNow.toDate()
                val phone = "$countryCode$phoneNumber"
                if(newPassword != confirmPassword){
                    Toast.makeText(context, "Passwords do not match", Toast.LENGTH_LONG).show()
                }
                else {
                    val user = UserEntity(
                        userId = null,
                        firstName = firstName,
                        lastName = lastName,
                        dateOfBirth = newDateOfBirth,
                        gender = gender,
                        email = email,
                        phoneNumber = phone,
                        countryOfResidence = country,
                        userName = username,
                        password = newPassword,
                        dateJoined = currentDate,
                        profilePicture = null,
                        comments = null,
                        userSubscription = null,
                        bankAccounts = null
                    )
                    registerUser(user)
                    navigateToTipsterLoginScreen(username, newPassword)
                }

            }
        }


    }

}