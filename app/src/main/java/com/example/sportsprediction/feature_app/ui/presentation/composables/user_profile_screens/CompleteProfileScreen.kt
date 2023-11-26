package com.example.sportsprediction.feature_app.ui.presentation.composables.user_profile_screens

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.sportsprediction.feature_app.ui.presentation.composables.bottom_nav.BottomBar
import com.example.sportsprediction.feature_app.ui.presentation.composables.components.BuildBetScreenTopBar
import com.example.sportsprediction.feature_app.ui.presentation.composables.user_profile.CompleteProfileContent
import com.example.sportsprediction.feature_app.ui.presentation.sign_in.view_model.SignInViewModel
import com.example.sportsprediction.feature_app.ui.presentation.view_model.UserProfileViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider

@Composable
fun CompleteProfileScreen(
    userProfileViewModel: UserProfileViewModel = hiltViewModel(),
    navController: NavHostController,
    signInViewModel: SignInViewModel = hiltViewModel(),
    navigateToHomePage: () -> Unit,
    navigateBack: () -> Unit,
){
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val firebaseUser = signInViewModel.userData

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

    LaunchedEffect(Unit){
        signInViewModel.getCurrentlySignedInUser()
    }

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
        CompleteProfileContent(
            firebaseUID = firebaseUser?.userId ?: "Did not load",
            email = firebaseUser?.email ?: "emptyemail@gmail.com",
            isSavingUserInfo = userProfileViewModel.userProfileState.value.isSaving,
            addUserInfo = {_user->
                userProfileViewModel.insertUser(_user)
            }
        ) {
            navigateToHomePage()
        }
    }
}