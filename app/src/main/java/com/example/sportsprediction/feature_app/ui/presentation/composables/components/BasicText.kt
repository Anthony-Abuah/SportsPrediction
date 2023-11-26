package com.example.sportsprediction.feature_app.ui.presentation.composables.components


import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.sportsprediction.feature_app.ui.theme.Blue10
import com.example.sportsprediction.feature_app.ui.theme.Blue90
import com.example.sportsprediction.feature_app.ui.theme.BlueGrey90
import com.example.sportsprediction.feature_app.ui.theme.Grey90


@Composable
fun BasicText(
    text: String,
    fontSize: TextUnit,
    textColor: Color
) {
    Text(
        text = text,
        fontWeight= FontWeight.Normal,
        color = textColor,
        fontSize = fontSize,
        textAlign = TextAlign.Center
    )
}

@Composable
fun SuggestionFilterCardText(
    text: String
) {
    Text(
        text = text,
        fontWeight= FontWeight.SemiBold,
        color = MaterialTheme.colorScheme.onBackground,
        style = MaterialTheme.typography.labelLarge

    )
}
@Composable
fun SuggestionFilterCardValueText(
    text: String
) {
    Text(
        text = text,
        fontWeight= FontWeight.Normal,
        color = MaterialTheme.colorScheme.onSurface,
        style = MaterialTheme.typography.labelLarge,
        overflow = TextOverflow.Ellipsis

    )
}

@Composable
fun EventScoresTeamText(
    text: String,
    fontWeight: FontWeight
) {
    Text(
        text = text,
        fontWeight= fontWeight,
        color = MaterialTheme.colorScheme.onBackground,
        style = MaterialTheme.typography.bodyMedium,
        textAlign = TextAlign.Start,
        overflow = TextOverflow.Ellipsis,
        maxLines = 1
    )
}

@Composable
fun UnderlinedRegisteredText(
    text: String,
    fontSize: TextUnit,
    textColor: Color
) {
    Text(
        text = text,
        fontWeight= FontWeight.Normal,
        color = textColor,
        fontSize = fontSize,
        textAlign = TextAlign.Start
    )
}


@Composable
fun SaveBetSlipText(
    text: String
) {
    Text(
        text = text,
        fontWeight= FontWeight.Bold,
        color = Color.White,
        fontSize = 16.sp,
        textAlign = TextAlign.Center
    )
}

@Composable
fun BetSlipTeamText(
    text: String
) {
    Text(
        text = text,
        fontWeight= FontWeight.Bold,
        color = Color.Black,
        fontSize = 16.sp,
        textAlign = TextAlign.Center
    )
}


@Composable
fun SelectLeagueText(
    text: String
) {
    Text(
        text = text,
        fontWeight= FontWeight.Bold,
        style = MaterialTheme.typography.titleMedium,
        textAlign = TextAlign.Center
    )
}

@Composable
fun StartTimeFilterText(
    text: String
) {
    Text(
        text = text,
        fontWeight= FontWeight.SemiBold,
        style = MaterialTheme.typography.titleMedium,
        textAlign = TextAlign.Center
    )
}

@Composable
fun BetSlipSuggestionText(
    text: String
) {
    Text(
        text = text,
        fontWeight= FontWeight.Normal,
        color = MaterialTheme.colorScheme.onBackground,
        style = MaterialTheme.typography.labelMedium,
        textAlign = TextAlign.Center
    )
}

@Composable
fun BetSlipLightStreakText(
    text: String
) {
    Text(
        text = text,
        fontWeight= FontWeight.Normal,
        color = MaterialTheme.colorScheme.onBackground,
        style = MaterialTheme.typography.labelMedium,
        textAlign = TextAlign.Center
    )
}


@Composable
fun BetSlipBoldStreakText(
    text: String
) {
    Text(
        text = text,
        fontWeight= FontWeight.Bold,
        color = MaterialTheme.colorScheme.onBackground,
        style = MaterialTheme.typography.labelMedium,
        textAlign = TextAlign.Center
    )
}

@Composable
fun OddsNameText(
    text: String
) {
    Text(
        text = text,
        fontWeight= FontWeight.Normal,
        color = MaterialTheme.colorScheme.outline,
        style = MaterialTheme.typography.labelMedium,
        textAlign = TextAlign.Center
    )
}

@Composable
fun OddsDisplayText(
    text: String
) {
    Text(
        text = text,
        fontWeight= FontWeight.Bold,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        style = MaterialTheme.typography.bodyLarge,
        textAlign = TextAlign.Center
    )
}

@Composable
fun TopAppBarText(
    text: String,
    fontSize: TextUnit,
    textColor: Color
) {
    Text(
        text = text,
        fontWeight= FontWeight.Bold,
        color = textColor,
        fontSize = fontSize,
        textAlign = TextAlign.Center
    )
}

@Composable
fun TipsterRegistrationQuestionsText(
    text: String
) {
    Text(
        text = text,
        fontWeight= FontWeight.Bold,
        color = Color.Black,
        fontSize = 14.sp,
        textAlign = TextAlign.Start
    )
}

@Composable
fun TeamEmblemText(
    text: String
) {
    Text(
        text = text,
        fontWeight= FontWeight.SemiBold,
        color = BlueGrey90,
        style = MaterialTheme.typography.labelLarge,
        //fontSize = 12.sp,
        textAlign = TextAlign.Center,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
fun EventTeamDateText(
    text: String
) {
    Text(
        text = text,
        fontWeight= FontWeight.Normal,
        color = Color.Gray,
        fontSize = 13.sp,
        textAlign = TextAlign.Center
    )
}


@Composable
fun EventTeamTimeText(
    text: String
) {
    Text(
        text = text,
        fontWeight= FontWeight.Bold,
        color = MaterialTheme.colorScheme.onTertiary,
        fontSize = 14.sp,
        textAlign = TextAlign.Center
    )
}

@Composable
fun EventTeamTournamentText(
    text: String
) {
    Text(
        text = text,
        fontWeight= FontWeight.SemiBold,
        color = Color.Gray,
        fontSize = 12.sp,
        textAlign = TextAlign.Center
    )
}



@Composable
fun TeamPastEventText(
    text: String
) {
    Text(
        text = text,
        fontWeight= FontWeight.SemiBold,
        color = MaterialTheme.colorScheme.onBackground,
        style = MaterialTheme.typography.titleMedium,
        //fontSize = 16.sp,
        textAlign = TextAlign.Center
    )
}


@Composable
fun FormGuideTeamNameText(
    text: String
) {
    Text(
        text = text,
        fontWeight= FontWeight.SemiBold,
        color = MaterialTheme.colorScheme.onSecondaryContainer,
        style = MaterialTheme.typography.labelSmall,
        overflow = TextOverflow.Ellipsis,
        textAlign = TextAlign.Center
    )
}

@Composable
fun FormCardHeaderText(
    text: String
) {
    Text(
        text = text,
        fontWeight= FontWeight.SemiBold,
        color = MaterialTheme.colorScheme.onSecondaryContainer,
        style = MaterialTheme.typography.bodySmall,
        textAlign = TextAlign.Center
    )
}



@Composable
fun DateEventTeamNameText(
    text: String
) {
    Text(
        text = text,
        fontWeight= FontWeight.SemiBold,
        color = Color.Black,
        fontSize = 14.sp,
        textAlign = TextAlign.Center
    )
}


@Composable
fun UserPreferenceMainText(
    text: String,
    color: Color
) {
    Text(
        text = text,
        fontWeight= FontWeight.Normal,
        color = color,
        fontSize = 16.sp,
        textAlign = TextAlign.Start,
        overflow = TextOverflow.Ellipsis,
        maxLines = 1
    )
}


@Composable
fun UserPreferenceValueText(
    text: String,
    color: Color,
) {
    Text(
        text = text,
        fontWeight= FontWeight.Normal,
        color = color,
        fontSize = 14.sp,
        textAlign = TextAlign.Center,
        maxLines = 1
    )
}



@Composable
fun AlertDialogTitleText(
    text: String
) {
    Text(
        text = text,
        fontWeight= FontWeight.Bold,
        color = Color.Black,
        fontSize = 16.sp,
        textAlign = TextAlign.Center
    )
}


@Composable
fun SelectedTabText(
    text: String
) {
    Text(
        text = text,
        fontWeight= FontWeight.SemiBold,
        color = Color.White,
        fontSize = 16.sp,
        textAlign = TextAlign.Center
    )
}


@Composable
fun UnselectedTabText(
    text: String
) {
    Text(
        text = text,
        fontWeight= FontWeight.Normal,
        color = Color.White,
        fontSize = 15.sp,
        textAlign = TextAlign.Center
    )
}



@Composable
fun SuggestionPercentageText(
    text: String,
    fontSize: TextUnit,
    textColor: Color
) {
    Text(
        text = text,
        fontWeight= FontWeight.SemiBold,
        color = textColor,
        fontSize = fontSize,
        textAlign = TextAlign.Center
    )
}

@Composable
fun FilterDisplayText(
    text: String,
) {
    Text(
        text = text,
        fontWeight= FontWeight.Normal,
        color = Color.Black,
        fontSize = 16.sp,
        textAlign = TextAlign.Center
    )
}


@Composable
fun TeamInformationText(
    text: String,
) {
    Text(
        text = text,
        color = MaterialTheme.colorScheme.onBackground,
        style = MaterialTheme.typography.bodyMedium,
        fontWeight = FontWeight.SemiBold
    )
}






