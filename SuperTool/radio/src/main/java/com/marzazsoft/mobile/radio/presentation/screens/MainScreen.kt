package com.marzazsoft.mobile.radio.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.marzazsoft.mobile.games.navigation.RadioNavigation

@Suppress("ktlint:standard:function-naming")
@Composable
fun MainScreen(appNavController: NavController) {
    MainScreenUi(
        appNavController = appNavController,
    )
}

@Suppress("ktlint:standard:function-naming")
@SuppressLint("SetJavaScriptEnabled")
@Composable
fun MainScreenUi(appNavController: NavController) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { innerPadding ->
        RadioNavigation(
            appNavController = appNavController,
            modifier = Modifier.padding(innerPadding),
        )
    }
}

@Suppress("ktlint:standard:function-naming")
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainScreenPreview() {
    MainScreen(
        appNavController = rememberNavController(),
    )
}
