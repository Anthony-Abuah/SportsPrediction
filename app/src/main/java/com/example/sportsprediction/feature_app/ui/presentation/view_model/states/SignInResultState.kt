package com.example.sportsprediction.feature_app.ui.presentation.view_model.states

import com.example.sportsprediction.core.util.Constants.emptyString
import com.example.sportsprediction.feature_app.ui.presentation.sign_in.SignInResult

data class SignInResultState (
    val signInResult: SignInResult? = SignInResult(null, emptyString),
    val isLoading: Boolean = false,
    val error: String? = null
)
