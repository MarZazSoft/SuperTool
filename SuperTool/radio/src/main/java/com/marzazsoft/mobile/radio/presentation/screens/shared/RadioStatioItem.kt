package com.marzazsoft.mobile.radio.presentation.screens.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.marzazsoft.mobile.radio.models.RadioType
import com.marzazsoft.mobile.radio.models.Station
import com.marzazsoft.supertooldesign.utils.LARGE_IMAGE
import com.marzazsoft.supertooldesign.utils.MEDIUM_BORDER
import com.marzazsoft.supertooldesign.utils.MEDIUM_INDICATOR
import com.marzazsoft.supertooldesign.utils.black
import com.marzazsoft.supertooldesign.utils.superLightBlue
import com.marzazsoft.supertooldesign.R as DesignR

@Suppress("ktlint:standard:function-naming")
@Composable
fun RadioStationItem(
    radioStation: Station,
    onClickAction: () -> Unit,
) {
    RadioStationItemUi(
        iconUrl = radioStation.stationIcon,
        stationTitle = radioStation.name,
        onClickAction = onClickAction,
    )
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun RadioStationItemUi(
    iconUrl: String,
    stationTitle: String,
    onClickAction: () -> Unit,
) {
    Card(
        modifier =
            Modifier.size(
                width = MEDIUM_INDICATOR,
                height = LARGE_IMAGE,
            ),
        colors =
            CardColors(
                containerColor = superLightBlue,
                contentColor = superLightBlue,
                disabledContentColor = superLightBlue,
                disabledContainerColor = superLightBlue,
            ),
        shape = RoundedCornerShape(MEDIUM_BORDER),
        onClick = { onClickAction() },
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            AsyncImage(
                model =
                    ImageRequest
                        .Builder(LocalContext.current)
                        .data(iconUrl)
                        .crossfade(true)
                        .placeholder(DesignR.drawable.super_tool_icon)
                        .error(DesignR.drawable.super_tool_icon_error)
                        .build(),
                contentDescription = "",
                modifier = Modifier.weight(3f),
            )
            Text(
                text = stationTitle,
                color = black,
                modifier = Modifier.background(superLightBlue).weight(1f),
                maxLines = 1,
                overflow = TextOverflow.Clip,
            )
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RadioStationItemPreview() {
    RadioStationItem(
        radioStation = Station("", RadioType.FM, "", ""),
    ) {}
}
