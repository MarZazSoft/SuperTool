package com.marzazsoft.supertool.ui.screens

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.marzazsoft.supertool.R
import com.marzazsoft.supertool.navigation.NavigationScreens
import kotlinx.coroutines.delay

@Suppress("ktlint:standard:function-naming")
@Composable
fun SplashScreen(navController: NavController) {
    SplashUi()

    LaunchedEffect(key1 = true) {
        delay(2000)
        navController.popBackStack()
        navController.navigate(NavigationScreens.MainScreen.route)
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
@Preview(showBackground = true, showSystemUi = true)
fun SplashUi() {
    val infiniteTransition = rememberInfiniteTransition()
    val rotationAnimation =
        infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 360f,
            animationSpec = infiniteRepeatable(tween(1000, easing = LinearEasing)),
        )

    val brush =
        Brush.sweepGradient(
            colors = listOf(Color.Red, Color.Yellow, Color.Green, Color.Blue),
        )

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Box {
            Canvas(
                modifier =
                    Modifier.matchParentSize().drawBehind {
                        rotate(rotationAnimation.value) {
                            drawCircle(
                                brush = brush,
                                radius = size.minDimension / 2,
                                style = Stroke(width = 20f),
                            )
                        }
                    },
            ) {}
            Image(
                painter = painterResource(id = R.drawable.super_tool_icon),
                contentDescription = stringResource(id = R.string.icon_super_tool_description),
                modifier =
                    Modifier
                        .size(
                            dimensionResource(id = R.dimen.large_image),
                            dimensionResource(id = R.dimen.large_image),
                        ).clip(CircleShape),
            )
        }
        Text(
            text = stringResource(id = R.string.app_name),
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(all = 25.dp),
        )
    }
}
