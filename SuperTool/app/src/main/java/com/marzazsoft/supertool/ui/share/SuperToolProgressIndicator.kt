package com.marzazsoft.supertool.ui.share

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.marzazsoft.supertool.R
import com.marzazsoft.supertool.ui.theme.darkBlue
import com.marzazsoft.supertool.ui.theme.lightBlue
import com.marzazsoft.supertool.ui.theme.superLightBlue
import com.marzazsoft.supertool.ui.theme.yellow
import com.marzazsoft.supertool.utils.LARGE_INDICATOR
import com.marzazsoft.supertool.utils.LOADER_TIME_ANIMATION
import com.marzazsoft.supertool.utils.MEDIUM_IMAGE
import com.marzazsoft.supertool.utils.MEDIUM_INDICATOR
import com.marzazsoft.supertool.utils.SIMPLE_BORDER
import com.marzazsoft.supertool.utils.SMALL_INDICATOR
import com.marzazsoft.supertool.utils.START_INNER_INDICATOR
import com.marzazsoft.supertool.utils.START_OUTER_INDICATOR

@Suppress("ktlint:standard:function-naming")
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SuperToolProgressIndicator() {
    val infiniteTransition = rememberInfiniteTransition()
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec =
            infiniteRepeatable(
                animation = tween(durationMillis = LOADER_TIME_ANIMATION),
            ),
    )

    Box(
        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(darkBlue.copy(alpha = .9f)),
        )
        Box(
            contentAlignment = Alignment.Center,
        ) {
            CircularProgressIndicator(
                modifier =
                    Modifier
                        .size(
                            width = LARGE_INDICATOR,
                            height = LARGE_INDICATOR,
                        ).graphicsLayer {
                            rotationZ = rotation + START_OUTER_INDICATOR
                        },
                strokeWidth = SIMPLE_BORDER,
                color = lightBlue,
            )
            CircularProgressIndicator(
                modifier =
                    Modifier
                        .size(
                            width = MEDIUM_INDICATOR,
                            height = MEDIUM_INDICATOR,
                        ).graphicsLayer {
                            rotationZ = rotation
                        },
                strokeWidth = SIMPLE_BORDER,
                color = superLightBlue,
            )
            CircularProgressIndicator(
                modifier =
                    Modifier
                        .size(
                            width = SMALL_INDICATOR,
                            height = SMALL_INDICATOR,
                        ).graphicsLayer {
                            rotationZ = rotation + START_INNER_INDICATOR
                        },
                strokeWidth = SIMPLE_BORDER,
                color = yellow,
            )
            Image(
                painter = painterResource(R.drawable.super_tool_icon),
                contentDescription = stringResource(R.string.icon_super_tool_description),
                modifier = Modifier.clip(CircleShape).size(width = MEDIUM_IMAGE, height = MEDIUM_IMAGE),
            )
        }
    }
}
