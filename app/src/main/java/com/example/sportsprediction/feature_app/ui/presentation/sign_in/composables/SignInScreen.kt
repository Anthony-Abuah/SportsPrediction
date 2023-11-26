package com.example.sportsprediction.feature_app.ui.presentation.sign_in.composables

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.sportsprediction.feature_app.ui.presentation.sign_in.states.SignInState
import com.example.sportsprediction.feature_app.ui.theme.LocalSpacing

@Composable
fun SignInScreen(
    signInState: SignInState,
    onSignInClick: () -> Unit
){
    val context = LocalContext.current

    LaunchedEffect(key1 = signInState.signInError){
        signInState.signInError?.let {error->
            Toast.makeText(context, error, Toast.LENGTH_LONG).show()
        }
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(LocalSpacing.current.default),
        contentAlignment = Alignment.Center
    ){
        Button(onClick = {
            onSignInClick()
        }) {
            Text(text = "Sign In")

        }

    }


}