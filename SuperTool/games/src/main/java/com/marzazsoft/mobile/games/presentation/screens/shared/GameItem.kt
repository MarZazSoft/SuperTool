package com.marzazsoft.mobile.games.presentation.screens.shared

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.marzazsoft.mobile.games.R
import com.marzazsoft.mobile.games.models.Game
import com.marzazsoft.mobile.supertool.common.extensionfunctions.addTextWithLink
import com.marzazsoft.supertooldesign.utils.LARGE_IMAGE
import com.marzazsoft.supertooldesign.utils.NORMAL_BORDER
import com.marzazsoft.supertooldesign.utils.SIMPLE_BORDER
import com.marzazsoft.supertooldesign.utils.SIMPLE_PADDING
import com.marzazsoft.supertooldesign.utils.darkBlue
import com.marzazsoft.supertooldesign.utils.superLightBlue
import com.marzazsoft.supertooldesign.utils.yellow
import com.marzazsoft.supertooldesign.R as DesignR

@Suppress("ktlint:standard:function-naming")
@Composable
fun GameItem(
    game: Game,
    onClickAction: () -> Unit,
) {
    GameItemUi(
        title = game.title,
        author = game.author,
        posterIcon = game.posterUrl,
    ) {
        onClickAction()
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun GameItemUi(
    title: String,
    author: String,
    posterIcon: String,
    onClickAction: () -> Unit,
) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(SIMPLE_PADDING),
        border = BorderStroke(SIMPLE_BORDER, superLightBlue),
        onClick = { onClickAction() },
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().background(darkBlue).padding(SIMPLE_PADDING),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(
                contentAlignment = Alignment.Center,
            ) {
                AsyncImage(
                    model =
                        ImageRequest
                            .Builder(LocalContext.current)
                            .data(posterIcon)
                            .crossfade(true)
                            .placeholder(DesignR.drawable.super_tool_icon)
                            .error(DesignR.drawable.super_tool_icon_error)
                            .build(),
                    contentScale = ContentScale.Fit,
                    contentDescription = "",
                    modifier = Modifier.size(LARGE_IMAGE),
                )
            }
            Spacer(modifier = Modifier.height(NORMAL_BORDER))
            Text(
                text = title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = stringResource(R.string.from_label).addTextWithLink(" $author"),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(modifier = Modifier.height(NORMAL_BORDER))
            Button(
                onClick = {
                    onClickAction()
                },
                colors =
                    ButtonDefaults.buttonColors(
                        containerColor = yellow,
                    ),
                modifier = Modifier.fillMaxWidth(),
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Icon(
                        painter = painterResource(DesignR.drawable.ic_play),
                        tint = darkBlue,
                        contentDescription = stringResource(R.string.play_icon_description),
                    )
                    Text(text = stringResource(R.string.play_description))
                }
            }
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
@Preview(showBackground = true, showSystemUi = true)
fun GameItemPreview() {
    GameItem(
        game = Game(),
    ) {}
}
