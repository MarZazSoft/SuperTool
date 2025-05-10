package com.marzazsoft.supertool.ui.screens

import android.annotation.SuppressLint
import android.webkit.WebView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.marzazsoft.supertool.R
import com.marzazsoft.supertool.ui.theme.darkBlue
import com.marzazsoft.supertool.utils.SMALL_HEIGHT
import com.marzazsoft.supertool.viewModels.ToolsViewModel
import org.koin.androidx.compose.koinViewModel

@Suppress("ktlint:standard:function-naming")
@Composable
fun ToolsScreen(
    navController: NavController,
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
    )
}

@SuppressLint("SetJavaScriptEnabled")
@Suppress("ktlint:standard:function-naming")
@Composable
fun ToolsScreenUi(
    modifier: Modifier,
    welcomeText: String,
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
        Box(
            Modifier.background(darkBlue).constrainAs(contentId) {
                top.linkTo(headerId.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
        ) {
            AndroidView(
                modifier = Modifier.fillMaxSize(),
                factory = { context ->
                    WebView(context).apply {
                        settings.javaScriptEnabled = true
                        loadUrl("https://microstudio.io/gilles/indiebird/")
                    }
                },
            )
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ToolsScreenPreview() {
    ToolsScreen(rememberNavController(), modifier = Modifier.fillMaxSize())
}
