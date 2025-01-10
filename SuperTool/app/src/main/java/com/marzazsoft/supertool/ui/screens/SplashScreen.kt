package com.marzazsoft.supertool.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.marzazsoft.supertool.R
import com.marzazsoft.supertool.navigation.NavigationScreens
import kotlinx.coroutines.delay

@Suppress("ktlint:standard:function-naming")
@Composable
fun SplashScreen(navController: NavController) {
    Splash()

    LaunchedEffect(key1 = true) {
        delay(3000)
        navController.popBackStack()
        navController.navigate(NavigationScreens.MainScreen.route)
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun Splash() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            painter = painterResource(id = R.drawable.super_tool_icon),
            contentDescription = stringResource(id = R.string.icon_super_tool_description),
            Modifier.size(
                dimensionResource(id = R.dimen.large_image),
                dimensionResource(id = R.dimen.large_image),
            ),
        )
        Text(
            text = stringResource(id = R.string.app_name),
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}
