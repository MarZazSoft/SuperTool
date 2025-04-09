package com.marzazsoft.supertool.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Suppress("ktlint:standard:function-naming")
@Composable
fun MainScreen(
    navController: NavHostController,
    modifier: Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        Text(text = "Bienvenidos :D")
    }
}

@Suppress("ktlint:standard:function-naming")
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainScreenPreview() {
    MainScreen(rememberNavController(), Modifier)
}
