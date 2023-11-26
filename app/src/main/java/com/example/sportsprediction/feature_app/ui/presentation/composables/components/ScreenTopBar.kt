package com.example.sportsprediction.feature_app.ui.presentation.composables.components


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.example.sportsprediction.R
import com.example.sportsprediction.core.util.Constants.MatchInfo
import com.example.sportsprediction.core.util.Constants.emptyString
import com.example.sportsprediction.feature_app.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PredictionsScreenTopBar(
    label: String,
    openOrCloseFilterCard: () -> Unit,
    moreOptions: () -> Unit
) {
    TopAppBar (
        title = {
            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(modifier = Modifier.weight(4f),
                contentAlignment = Alignment.CenterStart) {
                    Text(
                        text = label,
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                }
                Box(modifier = Modifier.weight(1f)
                    .padding(end = LocalSpacing.current.small)
                    .clickable {
                        openOrCloseFilterCard()
                    },
                contentAlignment = Alignment.CenterEnd) {
                    Icon(
                        painter = painterResource(id = R.drawable.filter),
                        tint  = MaterialTheme.colorScheme.onBackground,
                        contentDescription = emptyString
                    )
                }

            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            titleContentColor = MaterialTheme.colorScheme.onBackground,
            navigationIconContentColor = MaterialTheme.colorScheme.onBackground
        ),
        navigationIcon = {
            IconButton(
                onClick = moreOptions
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    )

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventsInfoScreenTopBar(
    navigateBack: () -> Unit
) {

    val backgroundColor = Blue10
    val contentColor = BlueGrey90

    TopAppBar (
        title = {
            Text(text = MatchInfo, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.SemiBold)
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = backgroundColor,
            titleContentColor = contentColor,
            navigationIconContentColor = contentColor
        ),
        navigationIcon = {
            IconButton(
                onClick = navigateBack
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    tint = contentColor
                )
            }
        }
    )

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserPreferencesTopBar(
    navigateBack: () -> Unit
) {
    TopAppBar (
        title = {
            Text(text = "Preferences", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.SemiBold)
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            titleContentColor = MaterialTheme.colorScheme.onBackground,
            navigationIconContentColor = MaterialTheme.colorScheme.onBackground
        ),
        navigationIcon = {
            IconButton(
                onClick = navigateBack
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.background
                )
            }
        }
    )

}



@OptIn(ExperimentalMaterial3Api::class)
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
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            titleContentColor = MaterialTheme.colorScheme.onBackground,
            navigationIconContentColor = MaterialTheme.colorScheme.onBackground
        ),
        navigationIcon = {
            IconButton(
                onClick = moreOptions
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        },
    )

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BuildBetScreenTopBar(
    openFilterCard: () -> Unit,
    openSearchCard: () -> Unit,
    navigateBack: () -> Unit,
) {
    TopAppBar (
        title = {
            BuildBetTopAppBar(
                openFilterCard = openFilterCard,
                openSearchCard = openSearchCard,
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            titleContentColor = MaterialTheme.colorScheme.onBackground,
            navigationIconContentColor = MaterialTheme.colorScheme.onBackground
        ),
        navigationIcon = {
            IconButton(
                onClick = navigateBack
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        },
    )

}



