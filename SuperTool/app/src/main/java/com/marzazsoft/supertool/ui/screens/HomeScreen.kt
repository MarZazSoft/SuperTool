package com.marzazsoft.supertool.ui.screens

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.marzazsoft.supertool.R
import com.marzazsoft.supertool.models.ItemNavigation
import com.marzazsoft.supertool.navigation.HomeNavigation
import com.marzazsoft.supertool.navigation.NavigationScreens
import com.marzazsoft.supertool.ui.share.BottomMenu
import com.marzazsoft.supertool.ui.share.SimpleAlert

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Suppress("ktlint:standard:function-naming")
@Composable
fun HomeScreen(
    appNavController: NavHostController,
    isGuest: Boolean,
) {
    val homeNavController = rememberNavController()
    val activity = LocalActivity.current
    var selectedItem by rememberSaveable { mutableIntStateOf(0) }
    var showExitAlert by rememberSaveable { mutableStateOf(false) }

    BackHandler {
        if (homeNavController.currentBackStackEntry?.destination?.route
            != NavigationScreens.ToolsScreen.route
        ) {
            homeNavController.popBackStack()
            selectedItem = 0
            homeNavController.navigate(NavigationScreens.ToolsScreen.route)
        } else {
            showExitAlert = true
        }
    }

    HomeScreenUi(
        appNavController = appNavController,
        navController = homeNavController,
        selectedItem = selectedItem,
        isGuest = isGuest,
        showExistAlert = showExitAlert,
        cancelExitAlertAction = {
            showExitAlert = false
        },
        okExitAlertAction = {
            if (isGuest) {
                appNavController.popBackStack()
            } else {
                activity?.finish()
            }
        },
        bottomMenuAction = { index, item ->
            selectedItem = index
            homeNavController.popBackStack()
            homeNavController.navigate(item.route)
        },
    )
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun HomeScreenUi(
    appNavController: NavHostController,
    navController: NavHostController,
    selectedItem: Int,
    isGuest: Boolean,
    showExistAlert: Boolean,
    cancelExitAlertAction: () -> Unit,
    okExitAlertAction: () -> Unit,
    bottomMenuAction: (index: Int, itemNavigation: ItemNavigation) -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomMenu(
                isGuest = isGuest,
                selectedItem = selectedItem,
                onClickAction = bottomMenuAction,
            )
        },
    ) { innerPadding ->
        HomeNavigation(
            modifier = Modifier.padding(innerPadding),
            appNavController = appNavController,
            navController = navController,
        )
        if (showExistAlert) {
            SimpleAlert(
                titleText =
                    stringResource(
                        if (isGuest) R.string.title_exit_guest else R.string.title_exit,
                    ),
                cancelButtonText = stringResource(R.string.cancel_button_text),
                okButtonText = stringResource(R.string.exit_button_text),
                cancelButtonAction = { cancelExitAlertAction() },
                okButtonAction = { okExitAlertAction() },
            )
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(rememberNavController(), isGuest = false)
}
