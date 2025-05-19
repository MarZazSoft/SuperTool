package com.marzazsoft.mobile.games.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.marzazsoft.mobile.games.presentation.screens.GamesScreen

@Suppress("ktlint:standard:function-naming")
@Composable
fun GamesNavigation(
    appNavController: NavController,
    modifier: Modifier,
) {
    val gameController = rememberNavController()
    NavHost(
        navController = gameController,
        startDestination = NavigationScreens.MainScreen.route,
    ) {
        composable(NavigationScreens.MainScreen.route) {
            GamesScreen(modifier)
        }
    }
}
