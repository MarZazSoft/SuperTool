package com.marzazsoft.supertool.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.marzazsoft.supertool.ui.screens.HomeScreen
import com.marzazsoft.supertool.ui.screens.HomeScreenGuest
import com.marzazsoft.supertool.ui.screens.LoginScreen
import com.marzazsoft.supertool.ui.screens.MainScreen
import com.marzazsoft.supertool.ui.screens.SplashScreen

@Suppress("ktlint:standard:function-naming")
@Composable
fun AppNavigation(modifier: Modifier) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavigationScreens.SplashScreen.route,
    ) {
        composable(NavigationScreens.SplashScreen.route) {
            SplashScreen(navController)
        }
        composable(NavigationScreens.MainScreen.route) {
            MainScreen(navController, modifier)
        }
        composable(NavigationScreens.LoginScreen.route) {
            LoginScreen(navController, modifier)
        }
        composable(NavigationScreens.HomeScreenGuest.route) {
            HomeScreenGuest(navController, modifier)
        }
        composable(NavigationScreens.HomeScreen.route) {
            HomeScreen(navController, modifier)
        }
    }
}
