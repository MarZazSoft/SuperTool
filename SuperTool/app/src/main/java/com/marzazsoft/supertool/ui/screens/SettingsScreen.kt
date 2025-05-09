package com.marzazsoft.supertool.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.marzazsoft.supertool.R
import com.marzazsoft.supertool.ui.theme.darkBlue
import com.marzazsoft.supertool.ui.theme.white
import com.marzazsoft.supertool.utils.EXTRA_LARGE_IMAGE

@Suppress("ktlint:standard:function-naming")
@Composable
fun SettingsScreen(
    navController: NavController,
    modifier: Modifier,
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.workinganim))
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever,
    )
    SettingsScreenUi(
        modifier = modifier,
        lottieProgress = progress,
        lottieComposition = composition,
    )
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun SettingsScreenUi(
    modifier: Modifier,
    lottieProgress: Float,
    lottieComposition: LottieComposition?,
) {
    Box(
        modifier = modifier.fillMaxSize().background(darkBlue),
        contentAlignment = Alignment.Center,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            LottieAnimation(
                composition = lottieComposition,
                progress = { lottieProgress },
                modifier = Modifier.height(EXTRA_LARGE_IMAGE).width(EXTRA_LARGE_IMAGE),
            )
            Text(text = stringResource(R.string.working_on_it_text), color = white)
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SettingsScreenPreview() {
    SettingsScreen(rememberNavController(), modifier = Modifier.fillMaxSize())
}
