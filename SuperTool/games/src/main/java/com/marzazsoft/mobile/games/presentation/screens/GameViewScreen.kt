package com.marzazsoft.mobile.games.presentation.screens

import android.annotation.SuppressLint
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import kotlin.apply

@Suppress("ktlint:standard:function-naming")
@SuppressLint("SetJavaScriptEnabled")
@Composable
fun GameViewScreen(
    modifier: Modifier,
    gameUrl: String,
) {
    val context = LocalContext.current
    var webView =
        WebView(context).apply {
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
            loadUrl("https://microstudio.io/gilles/$gameUrl/")
        }

    DisposableEffect(Unit) {
        onDispose {
            webView.clearCache(true)
            webView.clearHistory()
            webView.destroy()
        }
    }

    GameViewScreenUi(
        webView = webView,
        modifier = modifier,
    )
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun GameViewScreenUi(
    webView: WebView,
    modifier: Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize().background(Color.Blue),
    ) {
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { webView },
        )
    }
}

@Suppress("ktlint:standard:function-naming")
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GameViewScreenPreview() {
    GameViewScreen(modifier = Modifier.fillMaxSize(), gameUrl = "")
}
