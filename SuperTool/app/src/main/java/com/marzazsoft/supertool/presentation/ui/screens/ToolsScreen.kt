package com.marzazsoft.supertool.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.marzazsoft.mobile.games.navigation.NavigationScreens
import com.marzazsoft.supertool.R
import com.marzazsoft.supertool.data.getGuestUserTools
import com.marzazsoft.supertool.data.getLoggedUserTools
import com.marzazsoft.supertool.models.Tool
import com.marzazsoft.supertool.presentation.ui.screens.share.MenuItem
import com.marzazsoft.supertool.presentation.viewModels.ToolsViewModel
import com.marzazsoft.supertooldesign.utils.MEDIUM_PADDING
import com.marzazsoft.supertooldesign.utils.SIMPLE_PADDING
import com.marzazsoft.supertooldesign.utils.SMALL_HEIGHT
import com.marzazsoft.supertooldesign.utils.darkBlue
import org.koin.androidx.compose.koinViewModel

@Suppress("ktlint:standard:function-naming")
@Composable
fun ToolsScreen(
    appNavController: NavController,
    modifier: Modifier,
    viewModel: ToolsViewModel = koinViewModel(),
) {
    val context = LocalContext.current
    var userName by rememberSaveable { mutableStateOf("") }
    var isLogged by rememberSaveable { mutableStateOf(false) }
    var toolsList by rememberSaveable { mutableStateOf(emptyList<Tool>()) }

    LaunchedEffect(Unit) {
        userName = viewModel.getUserName()
        isLogged = viewModel.getIfGuest()
        toolsList =
            isLogged.takeIf { it }?.let {
                getLoggedUserTools(context)
            } ?: run {
                getGuestUserTools(context)
            }
    }

    val welcomeText = "${stringResource(R.string.welcome_text)} $userName"

    ToolsScreenUi(
        toolsList = toolsList,
        modifier = modifier,
        welcomeText = welcomeText,
        goToAction = { id ->
            when (id) {
                0 -> appNavController.navigate(NavigationScreens.MainScreen.route)
                else -> Unit
            }
        },
    )
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun ToolsScreenUi(
    toolsList: List<Tool>,
    modifier: Modifier,
    welcomeText: String,
    goToAction: (id: Int) -> Unit,
) {
    ConstraintLayout(
        modifier = modifier.fillMaxSize().background(darkBlue),
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
            Text(text = welcomeText, fontWeight = FontWeight.Bold)
        }
        Box(
            Modifier.background(darkBlue).padding(MEDIUM_PADDING).constrainAs(contentId) {
                top.linkTo(headerId.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
        ) {
            LazyColumn {
                items(toolsList) { item ->
                    MenuItem(
                        icon = item.icon,
                        color = item.getColor(),
                        title = item.title,
                        resume = item.resume,
                    ) { goToAction(item.id) }
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
