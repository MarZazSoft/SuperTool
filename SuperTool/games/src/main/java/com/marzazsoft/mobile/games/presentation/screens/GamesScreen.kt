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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
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
import com.marzazsoft.mobile.games.models.Game
import com.marzazsoft.mobile.games.navigation.NavigationScreens
import com.marzazsoft.mobile.games.presentation.screens.shared.GameItem
import com.marzazsoft.mobile.games.presentation.viewModels.GamesScreenViewModel
import com.marzazsoft.mobile.games.utils.MICRO_STUDIO
import com.marzazsoft.mobile.supertool.common.extensionfunctions.addTextWithLink
import com.marzazsoft.mobile.supertool.common.utils.ApiStatus
import com.marzazsoft.supertooldesign.presentation.screens.SuperToolProgressIndicator
import com.marzazsoft.supertooldesign.utils.BOTTOM_HEIGHT
import com.marzazsoft.supertooldesign.utils.SIMPLE_PADDING
import com.marzazsoft.supertooldesign.utils.SMALL_HEIGHT
import com.marzazsoft.supertooldesign.utils.darkBlue
import com.marzazsoft.supertooldesign.utils.toB64
import com.marzazsoft.supertooldesign.utils.white
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel
import com.marzazsoft.supertooldesign.R as DesignR

@Suppress("ktlint:standard:function-naming")
@Composable
fun GamesScreen(
    appNavController: NavController,
    gameNavController: NavController,
    modifier: Modifier,
    viewModel: GamesScreenViewModel = koinViewModel(),
) {
    var backActionFlag by rememberSaveable { mutableStateOf(true) }
    var showProgressBar by rememberSaveable { mutableStateOf(false) }
    var gamesList by rememberSaveable { mutableStateOf(emptyList<Game>()) }

    val gameState by viewModel.gamesStatus.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(gameState) {
        when (gameState) {
            is ApiStatus.Loading -> showProgressBar = true
            is ApiStatus.Success -> {
                showProgressBar = false
                gamesList = (gameState as ApiStatus.Success<List<Game>>).data
            }
            else -> showProgressBar = false
        }
    }

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            viewModel.getGamesList()
        }
    }

    GamesScreenUi(
        modifier = modifier,
        backAction = {
            if (backActionFlag) {
                backActionFlag = false
                gameNavController.popBackStack()
                appNavController.popBackStack()
            }
        },
        gamesList = gamesList,
        goToGame = { url ->
            gameNavController.navigate("${NavigationScreens.GameViewScreen.route}/$url")
        },
    )
    if (showProgressBar) SuperToolProgressIndicator()
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
        modifier = modifier.background(darkBlue).fillMaxSize(),
    ) {
        val (appBarId, headerId, contentId) = createRefs()

        Box(
            Modifier
                .background(darkBlue)
                .fillMaxWidth()
                .height(SMALL_HEIGHT)
                .constrainAs(appBarId) {
                    top.linkTo(parent.top, margin = SIMPLE_PADDING)
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
                    contentDescription = stringResource(DesignR.string.back_description),
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
            modifier =
                Modifier.constrainAs(headerId) {
                    top.linkTo(appBarId.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
        ) {
            Row {
                Text(text = stringResource(R.string.from_label).addTextWithLink(MICRO_STUDIO))
            }
        }
        Box(
            modifier =
                Modifier.background(darkBlue).constrainAs(contentId) {
                    top.linkTo(headerId.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier =
                    Modifier
                        .fillMaxSize()
                        .padding(
                            top = SIMPLE_PADDING,
                            end = SIMPLE_PADDING,
                            start = SIMPLE_PADDING,
                            bottom = BOTTOM_HEIGHT,
                        ),
            ) {
                items(gamesList) { game ->
                    GameItem(
                        game = game,
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
