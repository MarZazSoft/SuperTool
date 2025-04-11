package com.marzazsoft.supertool.ui.screens

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Suppress("ktlint:standard:function-naming")
@Composable
fun MainScreen(
    navController: NavHostController,
    modifier: Modifier,
) {
    ConstraintLayout(modifier = modifier.fillMaxWidth().fillMaxHeight()) {
        val textExample = createRef()

        Text(
            "INICIO SUPER TOOL - WORKING ON IT... :D",
            modifier =
                Modifier.constrainAs(textExample) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
        )
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
@Preview(showBackground = true, showSystemUi = true)
fun MainScreenPreview() {
    MainScreen(rememberNavController(), Modifier.fillMaxSize())
}
