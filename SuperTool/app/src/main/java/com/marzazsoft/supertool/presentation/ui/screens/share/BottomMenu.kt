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
import com.marzazsoft.supertool_design.utils.black
import com.marzazsoft.supertool_design.utils.gray
import com.marzazsoft.supertool_design.utils.lightBlue
import com.marzazsoft.supertool_design.utils.superLightBlue

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
                        color = if (index == selectedItem) superLightBlue else gray,
                    )
                },
                colors =
                    NavigationBarItemDefaults.colors(
                        selectedIconColor = black,
                        indicatorColor = superLightBlue,
                        unselectedIconColor = gray,
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
