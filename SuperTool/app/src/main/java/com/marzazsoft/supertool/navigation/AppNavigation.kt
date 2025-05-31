package com.marzazsoft.supertool.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.marzazsoft.supertool.presentation.ui.screens.HomeScreen
import com.marzazsoft.supertool.presentation.ui.screens.LoginScreen
import com.marzazsoft.supertool.presentation.ui.screens.MainScreen
import com.marzazsoft.supertool.presentation.ui.screens.SplashScreen
import com.marzazsoft.mobile.games.navigation.NavigationScreens as GamesNavigationScreens
import com.marzazsoft.mobile.games.presentation.screens.MainScreen as MainGamesScreen
import com.marzazsoft.mobile.radio.navigation.NavigationScreens as RadioNavigationScreens
import com.marzazsoft.mobile.radio.presentation.screens.MainScreen as MainRadioScreen

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
        composable(
            route = "${NavigationScreens.HomeScreen.route}/{isGuest}",
            arguments =
                listOf(
                    navArgument("isGuest") {
                        type = NavType.BoolType
                    },
                ),
        ) { argument ->
            HomeScreen(navController, isGuest = argument.arguments?.getBoolean("isGuest") != false)
        }
        composable(GamesNavigationScreens.MainScreen.route) {
            MainGamesScreen(appNavController = navController)
        }
        composable(RadioNavigationScreens.MainScreen.route) {
            MainRadioScreen(appNavController = navController)
        }
    }
}
