package com.marzazsoft.supertool.presentation.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.marzazsoft.mobile.games.navigation.NavigationScreens
import com.marzazsoft.supertool.R
import com.marzazsoft.supertool.presentation.ui.theme.darkBlue
import com.marzazsoft.supertool.presentation.ui.theme.yellow
import com.marzazsoft.supertool.presentation.viewModels.ToolsViewModel
import com.marzazsoft.supertool.utils.MEDIUM_PADDING
import com.marzazsoft.supertool.utils.NORMAL_BORDER
import com.marzazsoft.supertool.utils.SIMPLE_PADDING
import com.marzazsoft.supertool.utils.SMALL_HEIGHT
import org.koin.androidx.compose.koinViewModel

@Suppress("ktlint:standard:function-naming")
@Composable
fun ToolsScreen(
    appNavController: NavController,
    modifier: Modifier,
    viewModel: ToolsViewModel = koinViewModel(),
) {
    var userName by rememberSaveable { mutableStateOf("") }

    LaunchedEffect(Unit) {
        userName = viewModel.getUserName()
    }

    val welcomeText = "${stringResource(R.string.welcome_text)} $userName"

    ToolsScreenUi(
        modifier = modifier,
        welcomeText = welcomeText,
        goToGamesModule = {
            appNavController.navigate(NavigationScreens.MainScreen.route) {
                popUpTo(appNavController.graph.startDestinationId) { inclusive = true }
            }
        },
    )
}

@SuppressLint("SetJavaScriptEnabled")
@Suppress("ktlint:standard:function-naming")
@Composable
fun ToolsScreenUi(
    modifier: Modifier,
    welcomeText: String,
    goToGamesModule: () -> Unit,
) {
    ConstraintLayout(
        modifier = modifier.fillMaxSize().background(darkBlue),
    ) {
        val (headerId, contentId) = createRefs()

        Box(
            Modifier
                .background(Color.Blue)
                .fillMaxWidth()
                .height(SMALL_HEIGHT)
                .constrainAs(headerId) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            contentAlignment = Alignment.Center,
        ) {
            Text(text = welcomeText)
        }
        Column(
            Modifier.background(darkBlue).padding(MEDIUM_PADDING).constrainAs(contentId) {
                top.linkTo(headerId.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                onClick = { goToGamesModule() },
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Box(
                        modifier = Modifier.background(yellow).weight(1f),
                        contentAlignment = Alignment.Center,
                    ) {
                        Image(
                            painter = painterResource(R.drawable.ic_games),
                            contentDescription = "",
                        )
                    }
                    Column(
                        modifier = Modifier.weight(3f).padding(SIMPLE_PADDING),
                    ) {
                        Text("Juegos")
                        Spacer(modifier = Modifier.height(NORMAL_BORDER))
                        Text("Disfruta de juegos online...", maxLines = 1)
                    }
                }
            }
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ToolsScreenPreview() {
    ToolsScreen(
        appNavController = rememberNavController(),
        modifier = Modifier.fillMaxSize(),
    )
}
