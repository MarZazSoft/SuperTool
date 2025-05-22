package com.marzazsoft.supertool.presentation.ui.screens.share

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.marzazsoft.supertool.R
import com.marzazsoft.supertooldesign.utils.MEDIUM_PADDING
import com.marzazsoft.supertooldesign.utils.SIMPLE_PADDING
import com.marzazsoft.supertooldesign.utils.SMALL_X_IMAGE
import com.marzazsoft.supertooldesign.utils.black
import com.marzazsoft.supertooldesign.utils.darkBlue
import com.marzazsoft.supertooldesign.utils.white
import com.marzazsoft.supertooldesign.R as DesignR

@Suppress("ktlint:standard:function-naming")
@Composable
fun SimpleAlert(
    titleText: String,
    cancelButtonText: String,
    okButtonText: String,
    cancelButtonAction: () -> Unit,
    okButtonAction: () -> Unit,
) {
    SimpleAlertUi(
        titleText = titleText,
        cancelButtonText = cancelButtonText,
        okButtonText = okButtonText,
        cancelButtonAction = cancelButtonAction,
        okButtonAction = okButtonAction,
    )
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun SimpleAlertUi(
    titleText: String,
    cancelButtonText: String,
    okButtonText: String,
    cancelButtonAction: () -> Unit,
    okButtonAction: () -> Unit,
) {
    Box(
        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(darkBlue.copy(alpha = .6f)),
        )
        Box(
            contentAlignment = Alignment.Center,
        ) {
            Card(
                colors = CardDefaults.cardColors(containerColor = white),
                elevation = CardDefaults.elevatedCardElevation(),
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(all = MEDIUM_PADDING),
                ) {
                    Image(
                        painter = painterResource(DesignR.drawable.super_tool_icon),
                        contentDescription = stringResource(R.string.image_super_tool_description),
                        modifier = Modifier.size(SMALL_X_IMAGE).clip(shape = CircleShape),
                    )
                    Spacer(modifier = Modifier.height(MEDIUM_PADDING))
                    Text(titleText, fontWeight = FontWeight.Bold, color = black)
                    Spacer(modifier = Modifier.height(SIMPLE_PADDING))
                    Row(horizontalArrangement = Arrangement.End) {
                        TextButton(onClick = { cancelButtonAction() }) {
                            Text(cancelButtonText, textAlign = TextAlign.End, color = darkBlue)
                        }
                        TextButton(onClick = { okButtonAction() }) {
                            Text(okButtonText, textAlign = TextAlign.End, color = darkBlue)
                        }
                    }
                }
            }
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SimpleAlertPreview() {
    with(LocalContext.current) {
        SimpleAlert(
            titleText = getString(R.string.title_exit),
            cancelButtonText = getString(R.string.cancel_button_text),
            okButtonText = getString(R.string.exit_button_text),
            cancelButtonAction = {},
            okButtonAction = {},
        )
    }
}
