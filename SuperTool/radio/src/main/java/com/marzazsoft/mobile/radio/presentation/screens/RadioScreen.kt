package com.marzazsoft.mobile.radio.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.marzazsoft.supertooldesign.utils.lightBlue

@Suppress("ktlint:standard:function-naming")
@Composable
fun RadioScreen(
    appNavController: NavController,
    radioController: NavController,
    modifier: Modifier,
) {
    RadioScreenUi(
        appNavController = appNavController,
        modifier = modifier,
    )
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun RadioScreenUi(
    appNavController: NavController,
    modifier: Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize().background(lightBlue),
        contentAlignment = Alignment.Center,
    ) {
        AndroidView(
            factory = { context ->
                PlayerView(context).apply {
                    player =
                        ExoPlayer.Builder(context).build().apply {
                            setMediaItem(
                                MediaItem.fromUri(
                                    "https://19313.live.streamtheworld.com/XEQR_FMAAC.aac?dist=grc-web&key=grc-web&tdsdk=js-2.9&swm=false&pname=TDSdk&pversion=2.9&banners=none&burst-time=15&sbmid=b2e5e9f8-ceb2-4923-8c52-30025333b1e5",
                                ),
                            )
                            prepare()
                            play()
                        }
                }
            },
            modifier = Modifier.fillMaxSize(),
        )
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
@Preview(showBackground = true, showSystemUi = true)
fun RadioScreenPreview() {
    RadioScreen(
        appNavController = rememberNavController(),
        radioController = rememberNavController(),
        modifier = Modifier.fillMaxSize(),
    )
}
