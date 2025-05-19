package com.marzazsoft.supertool.presentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import com.marzazsoft.supertooldesign.utils.LARGE_IMAGE
import com.marzazsoft.supertooldesign.utils.MEDIUM_PADDING
import com.marzazsoft.supertooldesign.utils.NORMAL_BORDER
import com.marzazsoft.supertooldesign.utils.NORMAL_ROUNDED_CORNER
import com.marzazsoft.supertooldesign.utils.SIMPLE_HEIGHT_BUTTON
import com.marzazsoft.supertooldesign.utils.SIMPLE_PADDING
import com.marzazsoft.supertooldesign.utils.SMALL_IMAGE
import com.marzazsoft.supertooldesign.utils.darkBlue
import com.marzazsoft.supertooldesign.utils.superLightBlue
import com.marzazsoft.supertooldesign.utils.yellow

@Suppress("ktlint:standard:function-naming")
@Composable
fun LoginScreen(
    navController: NavHostController,
    modifier: Modifier,
) {
    var userValue by rememberSaveable { mutableStateOf("") }
    var passValue by rememberSaveable { mutableStateOf("") }
    var passHide by rememberSaveable { mutableStateOf(true) }

    LoginScreenUi(
        userValueState = userValue,
        userValueAction = { userValue = it },
        passValueState = passValue,
        passValueAction = { passValue = it },
        passHideAction = { passHide = !passHide },
        passHideIcon = getPassHideIcon(passHide),
        passVisualTransformation = getVisualTransformationForPass(passHide),
        modifier = modifier,
    )
}

fun getPassHideIcon(passHideState: Boolean): Int = if (passHideState) R.drawable.ic_eye_24 else R.drawable.ic_disabled_visible_24

fun getVisualTransformationForPass(passHideState: Boolean): VisualTransformation =
    if (passHideState) PasswordVisualTransformation() else VisualTransformation.None

@Suppress("ktlint:standard:function-naming")
@Composable
fun LoginScreenUi(
    userValueState: String,
    userValueAction: (String) -> Unit = {},
    passValueState: String,
    passValueAction: (String) -> Unit = {},
    passHideAction: () -> Unit,
    passHideIcon: Int,
    passVisualTransformation: VisualTransformation,
    modifier: Modifier,
) {
    ConstraintLayout(
        modifier =
            modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(darkBlue)
                .padding(all = MEDIUM_PADDING),
    ) {
        val (imageIconId, inputUserId, inputPassId, btnLogin) = createRefs()

        Image(
            painter = painterResource(id = R.drawable.super_tool_icon),
            contentDescription = stringResource(id = R.string.image_super_tool_description),
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
                    }.border(
                        width = NORMAL_BORDER,
                        color = yellow,
                        shape = CircleShape,
                    ),
        )

        OutlinedTextField(
            value = userValueState,
            onValueChange = { userValueAction(it) },
            label = { Text(text = stringResource(R.string.user_label)) },
            singleLine = true,
            modifier =
                Modifier
                    .constrainAs(inputUserId) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                        top.linkTo(parent.top)
                    }.fillMaxWidth(),
        )

        OutlinedTextField(
            value = passValueState,
            onValueChange = { passValueAction(it) },
            label = { Text(text = stringResource(R.string.password_label)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = passVisualTransformation,
            trailingIcon = {
                Icon(
                    painter = painterResource(passHideIcon),
                    contentDescription = stringResource(R.string.icon_password_description),
                    modifier = Modifier.size(SMALL_IMAGE).clickable { passHideAction() },
                    tint = superLightBlue,
                )
            },
            modifier =
                Modifier
                    .constrainAs(inputPassId) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(inputUserId.bottom, margin = SIMPLE_PADDING)
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
    }
}

@Suppress("ktlint:standard:function-naming")
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(rememberNavController(), Modifier)
}
