package com.marzazsoft.supertool.ui.share

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.marzazsoft.supertool.ui.theme.black
import com.marzazsoft.supertool.ui.theme.gray
import com.marzazsoft.supertool.ui.theme.lightBlue
import com.marzazsoft.supertool.ui.theme.superLightBlue
import com.marzazsoft.supertool.utils.getHomeItems

@Suppress("ktlint:standard:function-naming")
@Composable
fun BottomMenu(
    navController: NavController,
    isGuest: Boolean,
) {
    var selectedItem by rememberSaveable { mutableIntStateOf(0) }

    NavigationBar(
        containerColor = lightBlue,
    ) {
        getHomeItems(isGuest).forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    navController.navigate(item.route)
                },
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
    BottomMenu(rememberNavController(), isGuest = false)
}
