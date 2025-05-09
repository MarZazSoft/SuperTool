package com.marzazsoft.supertool.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.marzazsoft.supertool.ui.screens.ProfileScreen
import com.marzazsoft.supertool.ui.screens.SettingsScreen
import com.marzazsoft.supertool.ui.screens.ToolsScreen

@Suppress("ktlint:standard:function-naming")
@Composable
fun HomeNavigation(
    modifier: Modifier,
    appNavController: NavController,
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = NavigationScreens.ToolsScreen.route,
    ) {
        composable(NavigationScreens.ToolsScreen.route) {
            ToolsScreen(navController, modifier)
        }
        composable(NavigationScreens.ProfileScreen.route) {
            ProfileScreen(appNavController, navController, modifier)
        }
        composable(NavigationScreens.SettingsScreen.route) {
            SettingsScreen(navController, modifier)
        }
    }
}
