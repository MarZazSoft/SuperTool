package com.marzazsoft.mobile.games.navigation

sealed class NavigationScreens(
    val route: String,
) {
    data object MainScreen : NavigationScreens("games_main_screen")

    data object GameScreen : NavigationScreens("game_screen")
}
