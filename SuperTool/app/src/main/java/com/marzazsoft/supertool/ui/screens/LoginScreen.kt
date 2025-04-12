package com.marzazsoft.supertool.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.marzazsoft.supertool.R
import com.marzazsoft.supertool.navigation.NavigationScreens
import com.marzazsoft.supertool.utils.LARGE_IMAGE
import com.marzazsoft.supertool.utils.MEDIUM_PADDING
import com.marzazsoft.supertool.utils.NORMAL_ROUNDED_CORNER
import com.marzazsoft.supertool.utils.SIMPLE_HEIGHT_BUTTON
import com.marzazsoft.supertool.utils.SIMPLE_PADDING
import com.marzazsoft.supertool.utils.SMALL_IMAGE
import com.marzazsoft.supertool.viewModels.LoginViewModel
import org.koin.compose.viewmodel.koinViewModel

@Suppress("ktlint:standard:function-naming")
@Composable
fun LoginScreen(
    navController: NavHostController,
    modifier: Modifier,
    viewModel: LoginViewModel = koinViewModel()
) {
    val userValue = rememberSaveable { mutableStateOf("") }
    val passValue = rememberSaveable { mutableStateOf("") }
    val passHide = rememberSaveable { mutableStateOf(true) }

    ConstraintLayout(modifier = modifier.fillMaxWidth().fillMaxHeight().padding(all = MEDIUM_PADDING)) {
        val (imageIconId, inputUserId, inputPassId, btnLogin, btnGuest) = createRefs()

        Image(
            painter = painterResource(id = R.drawable.super_tool_icon),
            contentDescription = stringResource(id = R.string.icon_super_tool_description),
            modifier =
                Modifier
                    .size(
                        LARGE_IMAGE,
                        LARGE_IMAGE,
                    ).clip(CircleShape)
                    .constrainAs(imageIconId) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(inputUserId.top, margin = SIMPLE_PADDING)
                    },
        )

        OutlinedTextField(
            value = userValue.value,
            onValueChange = { userValue.value = it },
            label = { Text(text = stringResource(R.string.user_label)) },
            singleLine = true,
            modifier =
                Modifier
                    .constrainAs(inputUserId) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(inputPassId.top, margin = SIMPLE_PADDING)
                    }.fillMaxWidth(),
        )

        OutlinedTextField(
            value = passValue.value,
            onValueChange = { newText -> passValue.value = newText },
            label = { Text(text = stringResource(R.string.password_label)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if (passHide.value) PasswordVisualTransformation() else VisualTransformation.None,
            trailingIcon = {
                Image(
                    painter =
                        painterResource(
                            if (passHide.value) R.drawable.ic_eye_24 else R.drawable.ic_disabled_visible_24,
                        ),
                    contentDescription = stringResource(R.string.icon_password_description),
                    modifier =
                        Modifier.size(SMALL_IMAGE).clickable {
                            passHide.value = !passHide.value
                        },
                )
            },
            modifier =
                Modifier
                    .constrainAs(inputPassId) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }.fillMaxWidth(),
        )

        Button(
            modifier =
                Modifier
                    .constrainAs(btnLogin) {
                        top.linkTo(inputPassId.bottom, margin = MEDIUM_PADDING)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }.fillMaxWidth()
                    .height(SIMPLE_HEIGHT_BUTTON),
            onClick = {},
            shape = RoundedCornerShape(NORMAL_ROUNDED_CORNER),
        ) {
            Text(text = stringResource(R.string.enter_label))
        }

        OutlinedButton(
            modifier =
                Modifier
                    .constrainAs(btnGuest) {
                        top.linkTo(btnLogin.bottom, margin = SIMPLE_PADDING)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }.fillMaxWidth()
                    .height(SIMPLE_HEIGHT_BUTTON),
            onClick = {
                navController.navigate(NavigationScreens.MainScreen.route)
            },
            shape = RoundedCornerShape(NORMAL_ROUNDED_CORNER),
        ) {
            Text(text = stringResource(R.string.guest_label))
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(rememberNavController(), Modifier, LoginViewModel())
}
