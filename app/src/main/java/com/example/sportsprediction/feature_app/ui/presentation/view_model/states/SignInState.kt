package com.example.sportsprediction.feature_app.ui.presentation.view_model.states

import com.google.firebase.auth.AuthResult

data class SignInState (
    val success: AuthResult? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)