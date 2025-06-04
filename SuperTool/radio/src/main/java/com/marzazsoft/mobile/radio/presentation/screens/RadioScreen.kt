package com.marzazsoft.mobile.radio.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.marzazsoft.mobile.radio.R
import com.marzazsoft.supertooldesign.utils.EXTRA_LARGE_IMAGE
import com.marzazsoft.supertooldesign.utils.LARGE_PADDING
import com.marzazsoft.supertooldesign.utils.MEDIUM_PADDING
import com.marzazsoft.supertooldesign.utils.SIMPLE_PADDING
import com.marzazsoft.supertooldesign.utils.SMALL_HEIGHT
import com.marzazsoft.supertooldesign.utils.SMALL_X_IMAGE
import com.marzazsoft.supertooldesign.utils.darkBlue
import com.marzazsoft.supertooldesign.utils.white
import com.marzazsoft.supertooldesign.R as DesignR

@Suppress("ktlint:standard:function-naming")
@Composable
fun RadioScreen(
    appNavController: NavController,
    radioController: NavController,
    modifier: Modifier,
) {
    var backActionFlag by rememberSaveable { mutableStateOf(true) }

    val context = LocalContext.current
    val exoPlayer = remember { ExoPlayer.Builder(context).build() }
    var isPlaying by rememberSaveable { mutableStateOf(false) }

    DisposableEffect(Unit) {
        onDispose { exoPlayer.release() }
    }

    RadioScreenUi(
        modifier = modifier,
        backAction = {
            if (backActionFlag) {
                backActionFlag = false
                radioController.popBackStack()
                appNavController.popBackStack()
            }
        },
        playAction = {
            if (isPlaying) {
                exoPlayer.pause()
            } else {
                exoPlayer.setMediaItem(
                    MediaItem.fromUri(
                        "https://19313.live.streamtheworld.com/XEQR_FMAAC.aac?dist=grc-web&key=grc-web&tdsdk=js-2.9&swm=false&pname=TDSdk&pversion=2.9&banners=none&burst-time=15&sbmid=b2e5e9f8-ceb2-4923-8c52-30025333b1e5",
                    ),
                )
                exoPlayer.prepare()
                exoPlayer.play()
            }
            isPlaying = !isPlaying
        },
    )
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun RadioScreenUi(
    modifier: Modifier,
    backAction: () -> Unit,
    playAction: () -> Unit,
) {
    ConstraintLayout(
        modifier = modifier.fillMaxSize().background(darkBlue),
    ) {
        val (appBarId, headerId, contentId, toolsId) = createRefs()

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
                    text = stringResource(R.string.module_name),
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
                Modifier
                    .constrainAs(headerId) {
                        top.linkTo(appBarId.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }.padding(MEDIUM_PADDING),
        ) {
            Row {
                Text(text = "La Z XD")
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
            AsyncImage(
                model =
                    ImageRequest
                        .Builder(LocalContext.current)
                        .data(
                            "https://imgsvr.radiocut.site/get/crop/center/200/200/radio_logos/96/b2/96b243f3-b93b-4964-823f-d23f5e1a28ea.jpg",
                        ).crossfade(true)
                        .placeholder(DesignR.drawable.super_tool_icon)
                        .error(DesignR.drawable.super_tool_icon_error)
                        .build(),
                contentDescription = "",
                modifier = Modifier.size(EXTRA_LARGE_IMAGE),
            )
        }
        Row(
            modifier =
                Modifier
                    .constrainAs(toolsId) {
                        top.linkTo(contentId.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }.padding(LARGE_PADDING),
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_next_left),
                contentDescription = "",
                modifier = Modifier.weight(1f).size(SMALL_X_IMAGE),
            )
            Icon(
                painter = painterResource(DesignR.drawable.ic_play),
                contentDescription = "",
                modifier =
                    Modifier.weight(1f).size(SMALL_X_IMAGE).clickable {
                        playAction()
                    },
            )
            Icon(
                painter = painterResource(R.drawable.ic_next_right),
                contentDescription = "",
                modifier = Modifier.weight(1f).size(SMALL_X_IMAGE),
            )
        }
        /*AndroidView(
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
        )*/
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
