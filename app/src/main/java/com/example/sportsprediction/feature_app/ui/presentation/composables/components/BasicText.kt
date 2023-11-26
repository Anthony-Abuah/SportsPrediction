package com.example.sportsprediction.feature_app.ui.presentation.composables.components


import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.sportsprediction.feature_app.ui.theme.TeamEmblemColor

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
fun EventScoresInfoText(
    text: String,
    textColor: Color,
    fontWeight: FontWeight
) {
    Text(
        text = text,
        fontWeight= fontWeight,
        color = textColor,
        fontSize = 16.sp,
        textAlign = TextAlign.Start
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
        color = Color.Black,
        fontSize = 16.sp,
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
        color = Color.Black,
        fontSize = 16.sp,
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
        color = Color.Black,
        fontSize = 15.sp,
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
        color = Color.Gray,
        fontSize = 14.sp,
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
        color = Color.Black,
        fontSize = 14.sp,
        textAlign = TextAlign.Center
    )
}

@Composable
fun PlaceholderText(
    text: String,
) {
    Text(
        text = text,
        fontWeight= FontWeight.Normal,
        color = Color.Gray,
        fontSize = 16.sp,
        textAlign = TextAlign.Center
    )
}

@Composable
fun BasicSemiBoldText(
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
fun OddsNameText(
    text: String
) {
    Text(
        text = text,
        fontWeight= FontWeight.SemiBold,
        color = Color.LightGray,
        fontSize = 13.sp,
        textAlign = TextAlign.Center
    )
}

@Composable
fun OddsDisplayText(
    text: String
) {
    Text(
        text = text,
        fontWeight= FontWeight.SemiBold,
        color = Color.Black,
        fontSize = 15.sp,
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
        color = Color.Black,
        fontSize = 12.sp,
        textAlign = TextAlign.Center
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
        color = Color.Gray,
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
fun FormGuideText(
    text: String
) {
    Text(
        text = text,
        fontWeight= FontWeight.SemiBold,
        color = TeamEmblemColor,
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
        color = Color.Black,
        fontSize = 16.sp,
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
        color = Color.Gray,
        fontSize = 8.sp,
        textAlign = TextAlign.Center
    )
}

@Composable
fun DateMatchDurationText(
    text: String
) {
    Text(
        text = text,
        fontWeight= FontWeight.SemiBold,
        color = Color.Black,
        fontSize = 16.sp,
        textAlign = TextAlign.Center
    )
}

@Composable
fun FormGuideTitleText(
    text: String
) {
    Text(
        text = text,
        fontWeight= FontWeight.Bold,
        color = Color.Gray,
        fontSize = 13.sp,
        textAlign = TextAlign.Center
    )
}

@Composable
fun FormGuideRemarksText(
    text: String
) {
    Text(
        text = text,
        fontWeight= FontWeight.ExtraBold,
        color = Color.White,
        fontSize = 12.sp,
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
    text: String
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
fun UserPreferenceValueText(
    text: String
) {
    Text(
        text = text,
        fontWeight= FontWeight.Normal,
        color = Color.Gray,
        fontSize = 14.sp,
        textAlign = TextAlign.Center
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
fun FilterText(
    text: String,
) {
    Text(
        text = text,
        fontWeight= FontWeight.Normal,
        color = Color.Blue,
        fontSize = 16.sp,
        textAlign = TextAlign.Center,
        fontStyle = FontStyle.Italic,
        textDecoration = TextDecoration.Underline

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
fun FilterDisplayText1(
    text: String,
) {
    Text(
        text = text,
        fontWeight= FontWeight.Normal,
        color = Color.Gray,
        fontSize = 15.sp,
        textAlign = TextAlign.Center
    )
}






