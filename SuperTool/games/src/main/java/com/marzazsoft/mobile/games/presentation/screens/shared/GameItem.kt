package com.marzazsoft.mobile.games.presentation.screens.shared

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.marzazsoft.supertooldesign.R
import com.marzazsoft.supertooldesign.utils.LARGE_IMAGE
import com.marzazsoft.supertooldesign.utils.NORMAL_BORDER
import com.marzazsoft.supertooldesign.utils.SIMPLE_BORDER
import com.marzazsoft.supertooldesign.utils.SIMPLE_PADDING
import com.marzazsoft.supertooldesign.utils.white

@Suppress("ktlint:standard:function-naming")
@Composable
fun GameItem(
    title: String,
    posterIcon: String,
    onClickAction: () -> Unit,
) {
    GameItemUi(
        title = title,
        posterIcon = posterIcon,
    ) {
        onClickAction()
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun GameItemUi(
    title: String,
    posterIcon: String,
    onClickAction: () -> Unit,
) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(SIMPLE_PADDING),
        border = BorderStroke(SIMPLE_BORDER, white),
        onClick = { onClickAction() },
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(SIMPLE_PADDING),
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
                            .placeholder(R.drawable.super_tool_icon)
                            .error(R.drawable.super_tool_icon)
                            .build(),
                    contentScale = ContentScale.Fit,
                    contentDescription = "",
                    modifier = Modifier.size(LARGE_IMAGE),
                )
            }
            Text(title)
            Spacer(modifier = Modifier.height(NORMAL_BORDER))
            Button(
                onClick = {
                    onClickAction()
                },
            ) {
                Text(text = "Jugar")
            }
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
@Preview(showBackground = true, showSystemUi = true)
fun GameItemPreview() {
    GameItem(
        title = "Juego Dummy",
        posterIcon = "https://dummygame.com",
    ) {}
}
