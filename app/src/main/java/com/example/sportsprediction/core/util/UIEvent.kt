package com.example.sportsprediction.core.util

sealed class UIEvent{
        data class ShowSnackBar(val message: String): UIEvent()
    }