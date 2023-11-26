package com.example.sportsprediction.feature_app.ui.presentation.view_model.states

import com.example.sportsprediction.core.util.Constants.emptyString
import com.google.firebase.auth.AuthResult

data class EmailSignUpState (
    val success: String? = null,
    val isLoading: Boolean = false,
    val error: String = emptyString
)