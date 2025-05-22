package com.marzazsoft.mobile.games.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.marzazsoft.mobile.games.R
import com.marzazsoft.mobile.games.data.getGamesList
import com.marzazsoft.mobile.games.models.Game
import com.marzazsoft.mobile.games.navigation.NavigationScreens
import com.marzazsoft.mobile.games.presentation.screens.shared.GameItem
import com.marzazsoft.supertooldesign.utils.BOTTOM_HEIGHT
import com.marzazsoft.supertooldesign.utils.MEDIUM_PADDING
import com.marzazsoft.supertooldesign.utils.SIMPLE_PADDING
import com.marzazsoft.supertooldesign.utils.SMALL_HEIGHT
import com.marzazsoft.supertooldesign.utils.darkBlue
import com.marzazsoft.supertooldesign.utils.toB64
import com.marzazsoft.supertooldesign.utils.white
import com.marzazsoft.supertooldesign.R as DesignR

@Suppress("ktlint:standard:function-naming")
@Composable
fun GamesScreen(
    appNavController: NavController,
    gameNavController: NavController,
    modifier: Modifier,
) {
    var backActionFlag by rememberSaveable { mutableStateOf(true) }

    GamesScreenUi(
        modifier = modifier,
        backAction = {
            if (backActionFlag) {
                backActionFlag = false
                gameNavController.popBackStack()
                appNavController.popBackStack()
            }
        },
        gamesList = getGamesList(),
        goToGame = { url ->
            gameNavController.navigate("${NavigationScreens.GameViewScreen.route}/$url")
        },
    )
}

@Suppress("ktlint:standard:function-naming")
@SuppressLint("SetJavaScriptEnabled")
@Composable
fun GamesScreenUi(
    modifier: Modifier,
    backAction: () -> Unit,
    gamesList: List<Game>,
    goToGame: (url: String) -> Unit,
) {
    ConstraintLayout(
        modifier = modifier.background(darkBlue),
    ) {
        val (headerId, contentId) = createRefs()

        Box(
            Modifier
                .background(darkBlue)
                .fillMaxWidth()
                .height(SMALL_HEIGHT)
                .constrainAs(headerId) {
                    top.linkTo(parent.top, margin = MEDIUM_PADDING)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            contentAlignment = Alignment.Center,
        ) {
            Row(
                modifier = Modifier.fillMaxSize().padding(SIMPLE_PADDING),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    painter = painterResource(DesignR.drawable.ic_back),
                    tint = white,
                    contentDescription = stringResource(R.string.back_description),
                    modifier =
                        Modifier.weight(1f).clickable {
                            backAction()
                        },
                )
                Text(
                    text = stringResource(R.string.games_title),
                    color = white,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(7f),
                )
                Spacer(modifier = Modifier.weight(1f))
            }
        }
        Box(
            Modifier.background(darkBlue).padding(bottom = BOTTOM_HEIGHT).constrainAs(contentId) {
                top.linkTo(headerId.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.padding(SIMPLE_PADDING),
            ) {
                items(gamesList) { game ->
                    GameItem(
                        title = game.title,
                        posterIcon = game.posterUrl,
                    ) {
                        goToGame(game.gameUrl.toB64())
                    }
                }
            }
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GamesScreenPreview() {
    GamesScreen(
        appNavController = rememberNavController(),
        gameNavController = rememberNavController(),
        modifier = Modifier.fillMaxSize(),
    )
}
