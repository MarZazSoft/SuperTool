package com.marzazsoft.supertool.ui.screens

import android.annotation.SuppressLint
import android.webkit.WebView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.marzazsoft.supertool.ui.theme.darkBlue

@Suppress("ktlint:standard:function-naming")
@Composable
fun ToolsScreen(
    navController: NavController,
    modifier: Modifier,
) {
    ToolsScreenUi(modifier = modifier)
}

@SuppressLint("SetJavaScriptEnabled")
@Suppress("ktlint:standard:function-naming")
@Composable
fun ToolsScreenUi(modifier: Modifier) {
    Box(
        modifier = modifier.fillMaxSize().background(darkBlue),
        contentAlignment = Alignment.Center,
    ) {
        AndroidView(
            factory = { context ->
                WebView(context).apply {
                    settings.javaScriptEnabled = true
                    loadUrl("https://microstudio.io/gilles/indiebird/")
                }
            },
        )
    }
}

@Suppress("ktlint:standard:function-naming")
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ToolsScreenPreview() {
    ToolsScreen(rememberNavController(), modifier = Modifier.fillMaxSize())
}
