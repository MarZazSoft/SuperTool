package com.marzazsoft.supertool.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.marzazsoft.supertool.ui.screens.MainScreen
import com.marzazsoft.supertool.ui.screens.SplashScreen

@Suppress("ktlint:standard:function-naming")
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavigationScreens.SplashScreen.route,
    ) {
        composable(NavigationScreens.SplashScreen.route) {
            SplashScreen(navController)
        }
        composable(NavigationScreens.MainScreen.route) {
            MainScreen()
        }
    }
}
