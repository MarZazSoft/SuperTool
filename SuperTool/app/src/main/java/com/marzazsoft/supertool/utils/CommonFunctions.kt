package com.marzazsoft.supertool.utils

import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Settings
import com.marzazsoft.supertool.R
import com.marzazsoft.supertool.models.ItemNavigation
import com.marzazsoft.supertool.navigation.NavigationScreens

fun getHomeItems(
    isGuest: Boolean,
    context: Context,
): List<ItemNavigation> =
    with(context) {
        if (isGuest) {
            listOf(
                ItemNavigation(
                    title = getString(R.string.tools_label),
                    icon = Icons.Default.Build,
                    route = NavigationScreens.ToolsScreen.route,
                ),
                ItemNavigation(
                    title = getString(R.string.settings_label),
                    icon = Icons.Default.Settings,
                    route = NavigationScreens.SettingsScreen.route,
                ),
            )
        } else {
            listOf(
                ItemNavigation(
                    title = getString(R.string.tools_label),
                    icon = Icons.Default.Build,
                    route = NavigationScreens.ToolsScreen.route,
                ),
                ItemNavigation(
                    title = getString(R.string.profile_label),
                    icon = Icons.Default.AccountCircle,
                    route = NavigationScreens.ProfileScreen.route,
                ),
                ItemNavigation(
                    title = getString(R.string.settings_label),
                    icon = Icons.Default.Settings,
                    route = NavigationScreens.SettingsScreen.route,
                ),
            )
        }
    }
