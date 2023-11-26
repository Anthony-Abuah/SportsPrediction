package com.example.sportsprediction.feature_app.ui.presentation.composables.bottom_nav

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.example.sportsprediction.feature_app.ui.presentation.composables.components.BasicText
import com.example.sportsprediction.feature_app.ui.theme.*


@Composable
fun RowScope.AddItem(
    screen: BottomNavScreens,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    val isSelected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
    val backgroundColor = if (isSelected) PrimaryThemeColor else Color.Transparent
    val contentColor = if (isSelected) Color.White else SecondaryBackgroundColor

    Box(modifier = Modifier
        .fillMaxHeight()
        .weight(1f)
        .clickable {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        },
        contentAlignment = Alignment.BottomCenter
    ){
        Row(modifier = Modifier,
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.spacedBy(LocalSpacing.current.semiLarge)
        ){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                Icon(
                    painter = painterResource(id = if (isSelected) screen.focused_icon else screen.unfocused_icon ) ,
                    contentDescription = "",
                    tint = contentColor
                )

                BasicText(text = screen.name, fontSize = 14.sp, textColor = contentColor)

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .background(if (isSelected) Color.White else Color.Transparent)
                        .height(LocalSpacing.current.small)
                )


            }

            /*AnimatedVisibility(visible = isSelected) {
                BasicText(text = screen.name, fontSize = 14.sp, textColor = contentColor)
            }*/

        }


    }


}
