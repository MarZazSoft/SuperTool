package com.marzazsoft.supertool.ui.screens

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.marzazsoft.supertool.R
import com.marzazsoft.supertool.navigation.NavigationScreens
import com.marzazsoft.supertool.ui.theme.darkBlue
import com.marzazsoft.supertool.ui.theme.white
import com.marzazsoft.supertool.utils.DELAY_TIME_SPLASH_SCREEN
import com.marzazsoft.supertool.utils.LARGE_FONT_SIZE
import com.marzazsoft.supertool.utils.LARGE_IMAGE
import com.marzazsoft.supertool.utils.MEDIUM_PADDING
import com.marzazsoft.supertool.utils.NORMAL_STROKE_WIDTH
import com.marzazsoft.supertool.utils.SIMPLE_TIME_ANIMATION
import com.marzazsoft.supertool.viewModels.SplashViewModel
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel
import androidx.compose.runtime.getValue

@Suppress("ktlint:standard:function-naming")
@Composable
fun SplashScreen(
    navController: NavController,
    viewModel: SplashViewModel = koinViewModel()
) {
    val isLogged by viewModel.isLogged().collectAsState(initial = false)
    val infiniteTransition = rememberInfiniteTransition()
    val rotationAnimation =
        infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 360f,
            animationSpec = infiniteRepeatable(tween(SIMPLE_TIME_ANIMATION, easing = LinearEasing)),
        )

    val brush =
        Brush.sweepGradient(
            colors = listOf(Color.Red, Color.Yellow, Color.Green, Color.Blue),
        )

    SplashScreenUi(
        rotationAnimation = rotationAnimation,
        brush = brush,
    )

    LaunchedEffect(key1 = true) {
        delay(DELAY_TIME_SPLASH_SCREEN)

        isLogged.takeIf { it }?.let {
            navController.popBackStack()
            navController.navigate("${NavigationScreens.HomeScreen.route}/${false}")
        } ?: run {
            navController.popBackStack()
            navController.navigate(NavigationScreens.MainScreen.route)
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun SplashScreenUi(
    rotationAnimation: State<Float>,
    brush: Brush,
) {
    Column(
        modifier = Modifier.fillMaxSize().background(darkBlue),
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
                                style = Stroke(width = NORMAL_STROKE_WIDTH),
                            )
                        }
                    },
            ) {}
            Image(
                painter = painterResource(id = R.drawable.super_tool_icon),
                contentDescription = stringResource(id = R.string.image_super_tool_description),
                modifier =
                    Modifier
                        .size(
                            width = LARGE_IMAGE,
                            height = LARGE_IMAGE,
                        ).clip(CircleShape),
            )
        }
        Text(
            text = stringResource(id = R.string.app_name),
            fontSize = LARGE_FONT_SIZE,
            fontWeight = FontWeight.Bold,
            color = white,
            modifier = Modifier.padding(all = MEDIUM_PADDING),
        )
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
@Preview(showBackground = true, showSystemUi = true)
fun SplashScreenPreview() {
    SplashScreen(navController = rememberNavController())
}
