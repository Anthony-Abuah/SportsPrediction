package com.example.sportsprediction.feature_app.ui.presentation.composables.user_profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.sportsprediction.R
import com.example.sportsprediction.core.util.Constants.emptyString
import com.example.sportsprediction.feature_app.ui.presentation.composables.components.*
import com.example.sportsprediction.feature_app.ui.theme.FacebookColor
import com.example.sportsprediction.feature_app.ui.theme.LocalSpacing
import com.example.sportsprediction.feature_app.ui.theme.MainBackgroundColor
import com.example.sportsprediction.feature_app.ui.theme.PrimaryThemeColor

@Composable
fun UserLoginContent(
    loginUser: (username: String, password: String) -> Unit,
    isLoadingEmailSignIn: Boolean?,
    isLoadingGoogleSignIn: Boolean?,
    getGoogleSignInCredentials: () -> Unit,
    navigateToHomeScreen: () -> Unit
) {
    val scrollState = rememberScrollState(0)

    var thisEmail by remember {
        mutableStateOf(emptyString)
    }
    var thisPassword by remember {
        mutableStateOf(emptyString)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(bottom = LocalSpacing.current.topAppBarSize)
            .verticalScroll(state = scrollState, enabled = true),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        BasicImage(
            drawableResource = R.drawable.login_image,
            width = 100.dp,
            height = 100.dp,
            backgroundColor = MainBackgroundColor
        )

        Column(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(LocalSpacing.current.medium)
        ) {

            Box(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(), contentAlignment = Alignment.CenterStart) {
                CustomTextFieldWithLeadingIcon(
                    placeholder = "Email",
                    keyboardType = KeyboardType.Text,
                    imageVector = Icons.Default.Email
                ) {_email->
                    thisEmail = _email
                }
            }
        }


        Column(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(
                LocalSpacing.current.medium
            )
        ) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(), contentAlignment = Alignment.CenterStart){
                PasswordTextField(
                    placeholder = "Type in your password",
                    keyboardType = KeyboardType.Password,
                    imageVector = Icons.Default.Lock
                ){_password->
                    thisPassword = _password
                }
            }
        }


        Box(modifier = Modifier
            .padding(LocalSpacing.current.medium)
            .fillMaxWidth()
            .height(LocalSpacing.current.buttonHeight),
            contentAlignment = Alignment.Center
        ) {
            RegisterTipsterButton(buttonName = "Login",
            isLoading = isLoadingEmailSignIn) {
                loginUser(thisEmail, thisPassword)
                navigateToHomeScreen()

            }
        }

        Spacer(modifier = Modifier.height(LocalSpacing.current.medium))

        Button(modifier = Modifier
            .padding(horizontal = LocalSpacing.current.medium)
            .fillMaxWidth()
            .height(LocalSpacing.current.buttonHeight)
            .background(MainBackgroundColor, RoundedCornerShape(LocalSpacing.current.smallMedium)),
            onClick = { getGoogleSignInCredentials() },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MainBackgroundColor,
                contentColor = Color.Black
            )
        ) {
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconImage(
                    drawableResource = R.drawable.google,
                    modifier = Modifier.padding(LocalSpacing.current.small)
                )
                Spacer(modifier = Modifier.width(LocalSpacing.current.smallMedium))
                Text(
                    text = "Sign in with Google",
                    fontWeight = FontWeight.Normal,
                    color = Color.Black
                )
                if (isLoadingGoogleSignIn == true) {
                    Spacer(modifier = Modifier.width(LocalSpacing.current.small))
                    Box(modifier = Modifier.size(LocalSpacing.current.medium),
                        contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(
                            modifier = Modifier.height(LocalSpacing.current.medium),
                            color = PrimaryThemeColor,
                            strokeWidth = LocalSpacing.current.borderStroke
                        )
                    }
                }
            }

        }
        Spacer(modifier = Modifier.height(LocalSpacing.current.smallMedium))

        Button(modifier = Modifier
            .padding(horizontal = LocalSpacing.current.medium)
            .fillMaxWidth()
            .height(LocalSpacing.current.buttonHeight)
            .background(FacebookColor, RoundedCornerShape(LocalSpacing.current.smallMedium)),
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = FacebookColor,
                contentColor = Color.White
            )
        ) {

                IconImage(
                    drawableResource = R.drawable.facebook_image,
                    modifier = Modifier.padding(LocalSpacing.current.small)
                )
                Spacer(modifier = Modifier.width(LocalSpacing.current.smallMedium))
                Text(text = "Sign in with Facebook", fontWeight = FontWeight.Normal, color = Color.White)


        }
        Spacer(modifier = Modifier.height(LocalSpacing.current.smallMedium))

        Button(modifier = Modifier
            .padding(horizontal = LocalSpacing.current.medium)
            .fillMaxWidth()
            .height(LocalSpacing.current.buttonHeight)
            .background(MainBackgroundColor, RoundedCornerShape(LocalSpacing.current.smallMedium)),
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Black,
                contentColor = Color.Black
            )
        ) {
            IconImage(
                drawableResource = R.drawable.twitterx_image,
                modifier = Modifier
                    .padding(LocalSpacing.current.small)
                    .wrapContentSize()
                    .background(MainBackgroundColor, RoundedCornerShape(LocalSpacing.current.small))
            )

            Spacer(modifier = Modifier.width(LocalSpacing.current.smallMedium))
            Text(text = "Sign in with X", fontWeight = FontWeight.Normal, color = Color.White)
        }


    }

}