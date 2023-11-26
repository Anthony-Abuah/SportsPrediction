package com.example.sportsprediction.feature_app.ui.presentation.composables.user_profile_screens

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.sportsprediction.core.util.Constants.emptyString
import com.example.sportsprediction.core.util.Constants.web_client_Id
import com.example.sportsprediction.feature_app.ui.presentation.composables.bottom_nav.BottomBar
import com.example.sportsprediction.feature_app.ui.presentation.composables.components.BuildBetScreenTopBar
import com.example.sportsprediction.feature_app.ui.presentation.composables.user_profile.RegisterUserContent
import com.example.sportsprediction.feature_app.ui.presentation.sign_in.view_model.SignInViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.launch

@Composable
fun RegisterUserScreen(
    navController: NavHostController,
    signInViewModel: SignInViewModel = hiltViewModel(),
    navigateBack: () -> Unit,
    navigateToCompleteProfileScreen: () -> Unit,
    navigateToLoginScreen: () -> Unit
){
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val googleSignInState = signInViewModel.googleSignInState.value
    val emailSignUpState = signInViewModel.emailSignUpState.value

    var email by remember {
        mutableStateOf(emptyString)
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
        onResult ={
            val googleAccount = GoogleSignIn.getSignedInAccountFromIntent(it.data)
            try {
                val result = googleAccount.getResult(ApiException::class.java)
                val credentials = GoogleAuthProvider.getCredential(result.idToken, null)
                signInViewModel.signInWithGoogle(credentials)
            } catch (it: ApiException){
                print(it)
            }
        }
    )

    Scaffold(
        topBar = {
            BuildBetScreenTopBar(
                openFilterCard = {
                },
                openSearchCard = {
                },
                navigateBack = navigateBack
            )
        },
        bottomBar = {
            BottomBar(navHostController = navController)
        },
    ){it
        RegisterUserContent(
            registerUser = {_email, _password ->
                email = _email
                coroutineScope.launch{
                    signInViewModel.registerUserWithEmailAndPassword(_email, _password)
                }
            },
            isLoadingEmailSignUp = emailSignUpState.isLoading,
            isLoadingGoogleSignIn = googleSignInState.isLoading,
            getGoogleSignInCredentials = {
                val googleSignInOption = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .requestIdToken(web_client_Id)
                    .build()

                val googleSignInClient = GoogleSignIn.getClient(context, googleSignInOption)
                launcher.launch(googleSignInClient.signInIntent)
            },
            navigateToCompleteProfileScreen = navigateToCompleteProfileScreen,
            navigateToLoginScreen = navigateToLoginScreen
        )

        LaunchedEffect(key1 = googleSignInState.success){
            coroutineScope.launch {
                if (googleSignInState.success?.user != null){
                    Toast.makeText(context, "Registered Successfully", Toast.LENGTH_LONG).show()
                }
            }
        }


        LaunchedEffect(key1 = googleSignInState.error){
            coroutineScope.launch {
                if (googleSignInState.error != null){
                    Toast.makeText(context, "Unable to register", Toast.LENGTH_LONG).show()
                }
            }
        }


        LaunchedEffect(key1 = emailSignUpState.success){
            coroutineScope.launch {
                if (emailSignUpState.success?.user != null){
                    Toast.makeText(context, "Registered Successfully", Toast.LENGTH_LONG).show()
                }
            }
        }


        LaunchedEffect(key1 = emailSignUpState.error){
            coroutineScope.launch {
                if (emailSignUpState.error != null){
                    val errorMessage = emailSignUpState.error
                    Toast.makeText(context, "$errorMessage", Toast.LENGTH_LONG).show()
                }
            }
        }



    }
}