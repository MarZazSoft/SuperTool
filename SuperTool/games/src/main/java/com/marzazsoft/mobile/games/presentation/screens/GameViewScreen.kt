package com.marzazsoft.mobile.games.presentation.screens

import android.annotation.SuppressLint
import android.webkit.WebView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView

@Suppress("ktlint:standard:function-naming")
@Composable
fun GameViewScreen(
    modifier: Modifier,
    gameUrl: String,
) {
    GameViewScreenUi(
        modifier = modifier,
        gameUrl = gameUrl,
    )
}

@Suppress("ktlint:standard:function-naming")
@SuppressLint("SetJavaScriptEnabled")
@Composable
fun GameViewScreenUi(
    modifier: Modifier,
    gameUrl: String,
) {
    Box(
        modifier = Modifier.fillMaxSize().background(Color.Blue),
    ) {
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { context ->
                WebView(context).apply {
                    settings.javaScriptEnabled = true
                    loadUrl("https://microstudio.io/gilles/$gameUrl/")
                }
            },
        )
    }
}

@Suppress("ktlint:standard:function-naming")
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GameViewScreenPreview() {
    GameViewScreen(modifier = Modifier.fillMaxSize(), gameUrl = "")
}
