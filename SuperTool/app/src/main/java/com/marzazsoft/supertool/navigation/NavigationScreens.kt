package com.marzazsoft.supertool.navigation

sealed class NavigationScreens(
    val route: String,
) {
    data object SplashScreen : NavigationScreens("splash_screen")

    data object LoginScreen : NavigationScreens("login_screen")

    data object MainScreen : NavigationScreens("main_screen")
}
