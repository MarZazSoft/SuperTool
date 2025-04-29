package com.marzazsoft.supertool.ui.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.marzazsoft.supertool.models.ItemNavigation
import com.marzazsoft.supertool.navigation.HomeNavigation
import com.marzazsoft.supertool.navigation.NavigationScreens
import com.marzazsoft.supertool.ui.share.BottomMenu
import com.marzazsoft.supertool.utils.TAG_LOG

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Suppress("ktlint:standard:function-naming")
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreen() {
    val navController = rememberNavController()
    var selectedItem by rememberSaveable { mutableIntStateOf(0) }

    BackHandler {
        if (navController.currentBackStackEntry?.destination?.route
            != NavigationScreens.ToolsScreen.route
        ) {
            navController.popBackStack()
            selectedItem = 0
            navController.navigate(NavigationScreens.ToolsScreen.route)
        } else {
            Log.d(TAG_LOG, "Mostrar alerta de salir de app")
        }
    }

    HomeScreenUi(
        navController = navController,
        selectedItem = selectedItem,
    ) { index, item ->
        selectedItem = index
        navController.popBackStack()
        navController.navigate(item.route)
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun HomeScreenUi(
    navController: NavHostController,
    selectedItem: Int,
    bottomMenuAction: (index: Int, itemNavigation: ItemNavigation) -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomMenu(isGuest = false, selectedItem = selectedItem, onClickAction = bottomMenuAction)
        },
    ) { innerPadding ->
        HomeNavigation(modifier = Modifier.padding(innerPadding), navController = navController)
    }
}

@Suppress("ktlint:standard:function-naming")
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}
