package com.marzazsoft.supertool.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.marzazsoft.supertool.ui.theme.darkBlue
import com.marzazsoft.supertool.ui.theme.white

@Suppress("ktlint:standard:function-naming")
@Composable
fun SettingsScreen(
    navController: NavController,
    modifier: Modifier,
) {
    SettingsScreenUi(modifier = modifier)
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun SettingsScreenUi(modifier: Modifier) {
    Box(
        modifier = modifier.fillMaxSize().background(darkBlue),
        contentAlignment = Alignment.Center,
    ) {
        Text("Settings Screen", color = white)
    }
}

@Suppress("ktlint:standard:function-naming")
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SettingsScreenPreview() {
    SettingsScreen(rememberNavController(), modifier = Modifier.fillMaxSize())
}
