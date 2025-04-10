package com.marzazsoft.supertool.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.marzazsoft.supertool.R

@Suppress("ktlint:standard:function-naming")
@Composable
fun LoginScreen(
    navController: NavHostController,
    modifier: Modifier,
) {
    ConstraintLayout(modifier = modifier.fillMaxSize()) {
        val (imageIconId, inputUserId, inputPassId) = createRefs()

        Image(
            painter = painterResource(id = R.drawable.super_tool_icon),
            contentDescription = stringResource(id = R.string.icon_super_tool_description),
            modifier =
                Modifier
                    .size(
                        dimensionResource(id = R.dimen.large_image),
                        dimensionResource(id = R.dimen.large_image),
                    ).clip(CircleShape)
                    .constrainAs(imageIconId) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom, margin = 100.dp)
                    },
        )
        OutlinedTextField(
            value = "",
            onValueChange = { },
            label = { Text(text = "Usuario") },
            modifier =
                Modifier.constrainAs(inputUserId) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(imageIconId.bottom)
                },
        )
        OutlinedTextField(
            value = "",
            onValueChange = { },
            label = { Text(text = "Contraseña") },
            modifier =
                Modifier.constrainAs(inputPassId) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(inputUserId.bottom)
                },
        )
    }
}

@Suppress("ktlint:standard:function-naming")
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(rememberNavController(), Modifier)
}
