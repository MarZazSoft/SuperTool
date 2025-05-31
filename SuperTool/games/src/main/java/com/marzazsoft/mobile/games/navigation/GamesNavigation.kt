package com.marzazsoft.mobile.games.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.marzazsoft.mobile.games.presentation.screens.GameViewScreen
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
        startDestination = NavigationScreens.GamesScreen.route,
    ) {
        composable(NavigationScreens.GamesScreen.route) {
            GamesScreen(
                appNavController = appNavController,
                gameNavController = gameController,
                modifier = modifier,
            )
        }
        composable(
            route = "${NavigationScreens.GameViewScreen.route}/{gameUrl}",
            arguments =
                listOf(
                    navArgument("gameUrl") {
                        type = NavType.StringType
                    },
                ),
        ) { argument ->
            GameViewScreen(modifier = modifier, gameUrl = argument.arguments?.getString("gameUrl").orEmpty())
        }
    }
}
