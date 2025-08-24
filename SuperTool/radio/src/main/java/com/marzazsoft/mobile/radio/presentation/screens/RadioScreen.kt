package com.marzazsoft.mobile.radio.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.marzazsoft.mobile.radio.R
import com.marzazsoft.mobile.radio.data.getRadioStationsList
import com.marzazsoft.mobile.radio.models.RadioScreenAttributes
import com.marzazsoft.mobile.radio.presentation.screens.shared.RadioStationItem
import com.marzazsoft.mobile.radio.presentation.viewModels.RadioViewModel
import com.marzazsoft.supertooldesign.utils.EXTRA_LARGE_IMAGE
import com.marzazsoft.supertooldesign.utils.LARGE_PADDING
import com.marzazsoft.supertooldesign.utils.MEDIUM_PADDING
import com.marzazsoft.supertooldesign.utils.SIMPLE_PADDING
import com.marzazsoft.supertooldesign.utils.SMALL_HEIGHT
import com.marzazsoft.supertooldesign.utils.SMALL_X_IMAGE
import com.marzazsoft.supertooldesign.utils.darkBlue
import com.marzazsoft.supertooldesign.utils.superLightBlue
import com.marzazsoft.supertooldesign.utils.white
import org.koin.androidx.compose.koinViewModel
import com.marzazsoft.supertooldesign.R as DesignR

@Suppress("ktlint:standard:function-naming")
@Composable
fun RadioScreen(
    appNavController: NavController,
    radioController: NavController,
    modifier: Modifier,
    viewModel: RadioViewModel = koinViewModel(),
) {
    var backActionFlag by rememberSaveable { mutableStateOf(true) }
    var uriStation by rememberSaveable { mutableStateOf("") }
    var uriIconStation by rememberSaveable { mutableStateOf("") }
    var titleStation by rememberSaveable { mutableStateOf("") }
    var exoPlayer = remember { viewModel.getExoPlayer() }
    var isPlaying by rememberSaveable {
        mutableStateOf(
            exoPlayer.playWhenReady && exoPlayer.playbackState == Player.STATE_READY,
        )
    }

    val stationsList = getRadioStationsList()

    if (uriStation.isEmpty() && !isPlaying) {
        uriStation = stationsList[0].stationUri
        titleStation = stationsList[0].name
        uriIconStation = stationsList[0].stationIcon

        exoPlayer.setMediaItem(
            MediaItem.fromUri(
                uriStation,
            ),
        )
        exoPlayer.prepare()
    }

    val attributes =
        RadioScreenAttributes(
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
                    exoPlayer.play()
                }
                isPlaying = !isPlaying
            },
            playIcon = if (isPlaying) R.drawable.ic_stop else DesignR.drawable.ic_play,
            titleRadioStation = titleStation,
            uriIconStation = uriIconStation,
            stationsList = stationsList,
            actionStationSelected = { station ->
                uriStation = station.stationUri
                uriIconStation = station.stationIcon
                titleStation = station.name
                if (isPlaying) {
                    exoPlayer.pause()
                    isPlaying = false
                }
                exoPlayer.setMediaItem(
                    MediaItem.fromUri(
                        uriStation,
                    ),
                )
                exoPlayer.prepare()
            },
        )

    RadioScreenUi(attributes)
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun RadioScreenUi(attributes: RadioScreenAttributes) =
    with(attributes) {
        ConstraintLayout(
            modifier = modifier.fillMaxSize().background(darkBlue),
        ) {
            val (appBarId, headerId, contentId, toolsId, listId) = createRefs()

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
                    Text(text = titleRadioStation)
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
                            .data(uriIconStation)
                            .crossfade(true)
                            .placeholder(DesignR.drawable.super_tool_icon)
                            .error(DesignR.drawable.super_tool_icon_error)
                            .build(),
                    contentDescription = "",
                    modifier = Modifier.size(EXTRA_LARGE_IMAGE).clip(CircleShape),
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
                    tint = superLightBlue,
                )
                Icon(
                    painter = painterResource(playIcon),
                    contentDescription = "",
                    modifier =
                        Modifier.weight(1f).size(SMALL_X_IMAGE).clickable {
                            playAction()
                        },
                    tint = superLightBlue,
                )
                Icon(
                    painter = painterResource(R.drawable.ic_next_right),
                    contentDescription = "",
                    modifier = Modifier.weight(1f).size(SMALL_X_IMAGE),
                    tint = superLightBlue,
                )
            }
            LazyRow(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(
                            start = MEDIUM_PADDING,
                            end = MEDIUM_PADDING,
                            bottom = MEDIUM_PADDING,
                        ).constrainAs(listId) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            bottom.linkTo(parent.bottom)
                        },
                horizontalArrangement = Arrangement.spacedBy(MEDIUM_PADDING),
            ) {
                items(stationsList) { radioStation ->
                    RadioStationItem(
                        radioStation = radioStation,
                    ) {
                        actionStationSelected(radioStation)
                    }
                }
            }
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
