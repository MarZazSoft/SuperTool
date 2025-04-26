package com.marzazsoft.supertool.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Settings
import com.marzazsoft.supertool.models.ItemNavigation
import com.marzazsoft.supertool.navigation.NavigationScreens

fun getHomeItems(isGuest: Boolean): List<ItemNavigation> =
    if (isGuest) {
        listOf(
            ItemNavigation(
                title = "Tools",
                icon = Icons.Default.Build,
                route = NavigationScreens.ToolsScreen.route,
            ),
            ItemNavigation(
                title = "Settings",
                icon = Icons.Default.Settings,
                route = NavigationScreens.SettingsScreen.route,
            ),
        )
    } else {
        listOf(
            ItemNavigation(
                title = "Tools",
                icon = Icons.Default.Build,
                route = NavigationScreens.ToolsScreen.route,
            ),
            ItemNavigation(
                title = "Profile",
                icon = Icons.Default.AccountCircle,
                route = NavigationScreens.ProfileScreen.route,
            ),
            ItemNavigation(
                title = "Settings",
                icon = Icons.Default.Settings,
                route = NavigationScreens.SettingsScreen.route,
            ),
        )
    }
