package com.marzazsoft.supertool.navigation

sealed class NavigationScreens(
    val route: String,
) {
    data object SplashScreen : NavigationScreens("splash_screen")

    data object MainScreen : NavigationScreens("main_screen")

    data object LoginScreen : NavigationScreens("login_screen")

    data object HomeScreen : NavigationScreens("home_screen")

    data object ToolsScreen : NavigationScreens("tools_screen")

    data object ProfileScreen : NavigationScreens("profile_screen")

    data object SettingsScreen : NavigationScreens("settings_screen")
}
