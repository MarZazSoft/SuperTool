package com.marzazsoft.supertool.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.marzazsoft.supertool.navigation.HomeNavigation
import com.marzazsoft.supertool.ui.share.BottomMenu

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Suppress("ktlint:standard:function-naming")
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreen() {
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { BottomMenu(navController = navController, isGuest = false) },
    ) { innerPadding ->
        HomeNavigation(modifier = Modifier.padding(innerPadding), navController = navController)
    }
}
