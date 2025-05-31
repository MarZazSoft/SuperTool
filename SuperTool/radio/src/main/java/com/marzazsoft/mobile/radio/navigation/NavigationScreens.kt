package com.marzazsoft.mobile.radio.navigation

sealed class NavigationScreens(
    val route: String,
) {
    data object MainScreen : NavigationScreens("radio_main_screen")

    data object RadioScreen : NavigationScreens("radio_screen")
}
