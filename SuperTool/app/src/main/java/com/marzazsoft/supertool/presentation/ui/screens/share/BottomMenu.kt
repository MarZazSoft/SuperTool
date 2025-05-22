package com.marzazsoft.supertool.presentation.ui.screens.share

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.marzazsoft.supertool.models.ItemNavigation
import com.marzazsoft.supertool.utils.getHomeItems
import com.marzazsoft.supertooldesign.utils.darkBlue
import com.marzazsoft.supertooldesign.utils.lightBlue
import com.marzazsoft.supertooldesign.utils.superLightBlue
import com.marzazsoft.supertooldesign.utils.white

@Suppress("ktlint:standard:function-naming")
@Composable
fun BottomMenu(
    isGuest: Boolean,
    selectedItem: Int,
    onClickAction: (index: Int, itemNavigation: ItemNavigation) -> Unit,
) {
    NavigationBar(
        containerColor = lightBlue,
    ) {
        getHomeItems(isGuest, context = LocalContext.current).forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItem == index,
                onClick = { onClickAction(index, item) },
                icon = { Icon(imageVector = item.icon, contentDescription = item.title) },
                label = {
                    Text(
                        text = item.title,
                        color = if (index == selectedItem) white else superLightBlue,
                    )
                },
                colors =
                    NavigationBarItemDefaults.colors(
                        selectedIconColor = darkBlue,
                        indicatorColor = white,
                        unselectedIconColor = superLightBlue,
                    ),
            )
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
@Preview(showBackground = true, showSystemUi = true)
fun BottomMenuPreview() {
    BottomMenu(isGuest = false, selectedItem = 0) { _, _ -> }
}
