package com.marzazsoft.supertool.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.marzazsoft.supertool.R
import com.marzazsoft.supertool.ui.theme.darkBlue
import com.marzazsoft.supertool.ui.theme.white
import com.marzazsoft.supertool.utils.MEDIUM_PADDING
import com.marzazsoft.supertool.utils.MEDIUM_X_IMAGE

@Suppress("ktlint:standard:function-naming")
@Composable
fun ProfileScreen(
    navController: NavController,
    modifier: Modifier,
) {
    ProfileScreenUi(modifier = modifier)
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun ProfileScreenUi(modifier: Modifier) {
    Column(modifier = modifier.fillMaxSize().background(darkBlue)) {
        Box(
            modifier = Modifier.fillMaxWidth().padding(all = MEDIUM_PADDING),
            contentAlignment = Alignment.Center,
        ) {
            Image(
                painter = painterResource(R.drawable.super_tool_icon),
                contentDescription = stringResource(R.string.image_profile_description),
                modifier = Modifier.size(MEDIUM_X_IMAGE).clip(shape = CircleShape),
            )
        }
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Button(
                colors = ButtonDefaults.buttonColors(containerColor = darkBlue),
                onClick = {},
            ) {
                Text(
                    text = stringResource(R.string.log_out_text),
                    textDecoration = TextDecoration.Underline,
                    fontWeight = FontWeight.Bold,
                    color = white,
                )
            }
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(rememberNavController(), modifier = Modifier.fillMaxSize())
}
