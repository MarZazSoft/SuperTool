package com.marzazsoft.mobile.games.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import com.marzazsoft.supertooldesign.utils.MEDIUM_PADDING
import com.marzazsoft.supertooldesign.utils.SMALL_HEIGHT
import com.marzazsoft.supertooldesign.utils.black
import com.marzazsoft.supertooldesign.utils.darkBlue
import com.marzazsoft.supertooldesign.utils.yellow

@Suppress("ktlint:standard:function-naming")
@Composable
fun GamesScreen(modifier: Modifier) {
    GamesScreenUi(
        modifier = modifier,
    )
}

@Suppress("ktlint:standard:function-naming")
@SuppressLint("SetJavaScriptEnabled")
@Composable
fun GamesScreenUi(modifier: Modifier) {
    ConstraintLayout(
        modifier = modifier.fillMaxSize().background(darkBlue),
    ) {
        val (headerId, contentId) = createRefs()

        Box(
            Modifier
                .background(yellow)
                .fillMaxWidth()
                .height(SMALL_HEIGHT)
                .constrainAs(headerId) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            contentAlignment = Alignment.Center,
        ) {
            Text(text = "Videojuegos", color = black)
        }
        Box(
            Modifier.background(darkBlue).padding(MEDIUM_PADDING).constrainAs(contentId) {
                top.linkTo(headerId.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
        ) {
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GamesScreenPreview() {
    GamesScreen(modifier = Modifier.fillMaxSize())
}
