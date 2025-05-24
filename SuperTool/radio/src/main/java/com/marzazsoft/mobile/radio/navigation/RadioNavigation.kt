package com.marzazsoft.mobile.games.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.marzazsoft.mobile.radio.navigation.NavigationScreens
import com.marzazsoft.mobile.radio.presentation.screens.RadioScreen

@Suppress("ktlint:standard:function-naming")
@Composable
fun RadioNavigation(
    appNavController: NavController,
    modifier: Modifier,
) {
    val radioController = rememberNavController()
    NavHost(
        navController = radioController,
        startDestination = NavigationScreens.RadioScreen.route,
    ) {
        composable(NavigationScreens.RadioScreen.route) {
            RadioScreen(
                appNavController = appNavController,
                radioController = radioController,
                modifier = modifier,
            )
        }
    }
}
