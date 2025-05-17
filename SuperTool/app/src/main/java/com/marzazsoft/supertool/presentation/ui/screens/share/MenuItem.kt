package com.marzazsoft.supertool.presentation.ui.screens.share

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.marzazsoft.supertool.R
import com.marzazsoft.supertool.presentation.ui.theme.darkBlue
import com.marzazsoft.supertool.presentation.ui.theme.white
import com.marzazsoft.supertool.presentation.ui.theme.yellow
import com.marzazsoft.supertool.utils.MEDIUM_HEIGHT
import com.marzazsoft.supertool.utils.NORMAL_BORDER
import com.marzazsoft.supertool.utils.SIMPLE_BORDER
import com.marzazsoft.supertool.utils.SIMPLE_PADDING

@Suppress("ktlint:standard:function-naming")
@Composable
fun MenuItem(
    icon: Int,
    color: Color,
    title: String,
    resume: String,
    onClickAction: () -> Unit,
) {
    MenuItemUi(
        icon = icon,
        color = color,
        title = title,
        resume = resume,
    ) {
        onClickAction()
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun MenuItemUi(
    icon: Int,
    color: Color,
    title: String,
    resume: String,
    onClickAction: () -> Unit,
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        border = BorderStroke(SIMPLE_BORDER, white),
        onClick = { onClickAction() },
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().height(MEDIUM_HEIGHT),
        ) {
            Box(
                modifier = Modifier.background(color).weight(1f),
                contentAlignment = Alignment.Center,
            ) {
                Image(
                    painter = painterResource(icon),
                    contentDescription = stringResource(R.string.list_icon_description),
                    modifier = Modifier.fillMaxSize().padding(SIMPLE_PADDING),
                )
            }
            Column(
                modifier =
                    Modifier
                        .weight(3f)
                        .background(darkBlue)
                        .padding(SIMPLE_PADDING)
                        .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
            ) {
                Text(title)
                Spacer(modifier = Modifier.height(NORMAL_BORDER))
                Text(resume, maxLines = 2, overflow = TextOverflow.Ellipsis)
            }
        }
    }
    Spacer(modifier = Modifier.height(SIMPLE_PADDING))
}

@Suppress("ktlint:standard:function-naming")
@Composable
@Preview(showBackground = true, showSystemUi = true)
fun MenuItemPreview() {
    MenuItem(
        icon = R.drawable.ic_games,
        color = yellow,
        title = stringResource(R.string.online_games_title),
        resume = stringResource(R.string.online_games_resume),
    ) { Unit }
}
