package com.example.sportsprediction.feature_app.ui.presentation.composables.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.example.sportsprediction.core.util.Constants.MatchInfo
import com.example.sportsprediction.feature_app.ui.theme.PrimaryThemeColor
import com.example.sportsprediction.feature_app.ui.theme.TopBarTextColor

@Composable
fun PredictionsScreenTopBar(
    label: String,
    moreOptions: () -> Unit
) {
    TopAppBar (
        title = {
            PredictionBarTitle(title = label, 18.sp, TopBarTextColor)
        },
        backgroundColor = PrimaryThemeColor,
        navigationIcon = {
            IconButton(
                onClick = moreOptions
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    tint = TopBarTextColor
                )
            }
        }
    )

}


@Composable
fun EventsInfoScreenTopBar(
    navigateBack: () -> Unit
) {
    TopAppBar (
        title = {
            Text(text = MatchInfo, style = MaterialTheme.typography.h5)
        },
        backgroundColor = PrimaryThemeColor,
        navigationIcon = {
            IconButton(
                onClick = navigateBack
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
    )

}



@Composable
fun TipstersScreenTopBar(
    label: String,
    navigateToRegisterTipster: () -> Unit,
    moreOptions: () -> Unit
) {
    TopAppBar (
        title = {
            TipstersTopAppBar(
                title = label,
                openFilterCard = { /*TODO*/ },
                openSearchCard = { /*TODO*/ }) {
                navigateToRegisterTipster()
            }
        },
        backgroundColor = PrimaryThemeColor,
        navigationIcon = {
            IconButton(
                onClick = moreOptions
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    tint = TopBarTextColor
                )
            }
        },
    )

}


@Composable
fun BuildBetScreenTopBar(
    label: String,
    openFilterCard: () -> Unit,
    openSearchCard: () -> Unit,
    navigateBack: () -> Unit,
) {
    TopAppBar (
        title = {
            BuildBetTopAppBar(
                title = label,
                openFilterCard = openFilterCard,
                openSearchCard = openSearchCard,
                )
        },
        backgroundColor = PrimaryThemeColor,
        navigationIcon = {
            IconButton(
                onClick = navigateBack
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    tint = TopBarTextColor
                )
            }
        },
    )

}



