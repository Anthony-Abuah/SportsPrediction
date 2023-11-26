package com.example.sportsprediction.feature_app.ui.presentation.composables.build_a_bet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.example.sportsprediction.feature_app.ui.presentation.composables.components.BasicText
import com.example.sportsprediction.feature_app.ui.theme.LocalSpacing
import com.example.sportsprediction.feature_app.ui.theme.MainBackgroundColor
import com.example.sportsprediction.feature_app.ui.theme.PrimaryTextColor
import com.example.sportsprediction.feature_app.ui.theme.PrimaryThemeColor

@Composable
fun StatsBar(
    homeValue: Number,
    awayValue: Number
) {
    val totalValue = try { homeValue.toDouble().plus(awayValue.toDouble()) } catch (e: IllegalArgumentException) {1.0}
    val homeFraction = try { homeValue.toDouble().div(totalValue).toFloat()} catch (e: ArithmeticException){1.0.toFloat()}
    val awayFraction = try { awayValue.toDouble().div(totalValue).toFloat()} catch (e: ArithmeticException){1.0.toFloat()}

    val homeColor = if (homeFraction >= awayFraction) PrimaryThemeColor else Color.Red
    val awayColor = if (awayFraction >= homeFraction) PrimaryThemeColor else Color.Red

    Row(modifier = Modifier.fillMaxWidth(),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.Center) {

        //Home Value Bar
        Row(
            modifier = Modifier
                .padding(LocalSpacing.current.extraSmall)
                .weight(1f)
                .height(LocalSpacing.current.small)
                .background(Color.White, RoundedCornerShape(LocalSpacing.current.small)),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(modifier = Modifier
                .fillMaxWidth(homeFraction)
                .height(LocalSpacing.current.small)
                .background(homeColor, RoundedCornerShape(LocalSpacing.current.small))
            ) {
                BasicText(text = "A", fontSize = 23.sp, textColor = Color.Transparent)
            }
        }


        // Away Value Bar
        Row(
            modifier = Modifier
                .padding(LocalSpacing.current.extraSmall)
                .weight(1f)
                .height(LocalSpacing.current.small)
                .background(Color.White, RoundedCornerShape(LocalSpacing.current.small)),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(modifier = Modifier
                .fillMaxWidth(awayFraction)
                .height(LocalSpacing.current.small)
                .background(awayColor, RoundedCornerShape(LocalSpacing.current.small))
            ) {
                BasicText(text = "A", fontSize = 23.sp, textColor = Color.Transparent)
            }
        }


    }

}


