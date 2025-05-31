package com.marzazsoft.mobile.games.navigation

sealed class NavigationScreens(
    val route: String,
) {
    data object MainScreen : NavigationScreens("games_main_screen")

    data object GamesScreen : NavigationScreens("games_screen")

    data object GameViewScreen : NavigationScreens("game_view_screen")
}
