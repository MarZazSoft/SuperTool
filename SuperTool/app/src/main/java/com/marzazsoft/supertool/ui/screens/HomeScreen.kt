package com.marzazsoft.supertool.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.marzazsoft.supertool.R
import com.marzazsoft.supertool.ui.theme.darkBlue
import com.marzazsoft.supertool.ui.theme.lightBlue
import com.marzazsoft.supertool.utils.SIMPLE_PADDING

@Suppress("ktlint:standard:function-naming")
@Composable
fun HomeScreen(
    navController: NavHostController,
    modifier: Modifier,
) {
    Column(
        modifier =
            modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(Brush.verticalGradient(listOf(lightBlue, darkBlue), startY = 0f, endY = 1500f))
                .padding(all = SIMPLE_PADDING),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().height(100.dp).background(Color.Red),
        ) {
            Image(
                painter = painterResource(R.drawable.ic_google),
                contentDescription = "",
                modifier = Modifier.weight(2f),
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.height(100.dp).weight(4f),
            ) {
                Text(text = "Título")
                Text(text = "Contenido")
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth().height(100.dp).background(Color.Yellow),
        ) {
            Image(
                painter = painterResource(R.drawable.ic_google),
                contentDescription = "",
                modifier = Modifier.weight(2f),
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.height(100.dp).weight(4f),
            ) {
                Text(text = "Título")
                Text(text = "Contenido")
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth().height(100.dp).background(Color.Green),
        ) {
            Image(
                painter = painterResource(R.drawable.ic_google),
                contentDescription = "",
                modifier = Modifier.weight(2f),
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.height(100.dp).weight(4f),
            ) {
                Text(text = "Título", fontWeight = FontWeight.ExtraBold)
                Text(text = "Contenido")
            }
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
@Preview(showBackground = true, showSystemUi = true)
fun HomeScreenPreview() {
    HomeScreen(rememberNavController(), Modifier.fillMaxSize())
}
