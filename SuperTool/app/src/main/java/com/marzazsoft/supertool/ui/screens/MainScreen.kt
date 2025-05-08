package com.marzazsoft.supertool.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.credentials.CredentialManager
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.marzazsoft.supertool.R
import com.marzazsoft.supertool.navigation.NavigationScreens
import com.marzazsoft.supertool.ui.share.SuperToolProgressIndicator
import com.marzazsoft.supertool.ui.theme.darkBlue
import com.marzazsoft.supertool.ui.theme.superLightBlue
import com.marzazsoft.supertool.ui.theme.yellow
import com.marzazsoft.supertool.utils.ApiStatus
import com.marzazsoft.supertool.utils.LARGE_IMAGE
import com.marzazsoft.supertool.utils.MEDIUM_PADDING
import com.marzazsoft.supertool.utils.NORMAL_BORDER
import com.marzazsoft.supertool.utils.NORMAL_ROUNDED_CORNER
import com.marzazsoft.supertool.utils.SIMPLE_BORDER
import com.marzazsoft.supertool.utils.SIMPLE_HEIGHT_BUTTON
import com.marzazsoft.supertool.utils.SMALL_IMAGE
import com.marzazsoft.supertool.viewModels.MainViewModel
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel

@Suppress("ktlint:standard:function-naming")
@Composable
fun MainScreen(
    navController: NavHostController,
    modifier: Modifier,
    viewModel: MainViewModel = koinViewModel(),
) {
    val context = LocalContext.current
    val credentialManager = CredentialManager.create(context)
    var showProgressBar by rememberSaveable { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    val firebaseAuthState by viewModel.firebaseAuthState.collectAsState()

    LaunchedEffect(firebaseAuthState) {
        when (firebaseAuthState) {
            is ApiStatus.Loading -> showProgressBar = true
            is ApiStatus.Success -> {
                showProgressBar = false
                navController.popBackStack()
                navController.navigate("${NavigationScreens.HomeScreen.route}/${false}")
            }
            else -> showProgressBar = false
        }
    }

    MainScreenUi(
        modifier = modifier,
        navController = navController,
        googleSignInAction = {
            coroutineScope.launch {
                viewModel.goToGoogleSignIn(
                    credentialManager = credentialManager,
                    context = context,
                )
            }
        },
    )
    if (showProgressBar) SuperToolProgressIndicator()
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun MainScreenUi(
    modifier: Modifier,
    navController: NavHostController,
    googleSignInAction: () -> Unit,
) {
    ConstraintLayout(
        modifier =
            modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(darkBlue)
                .padding(all = MEDIUM_PADDING),
    ) {
        val (imageIconId, btnGuest, btnEmail, btnGoogleAuth) = createRefs()

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
                        bottom.linkTo(btnGuest.top, margin = MEDIUM_PADDING)
                    }.border(
                        width = NORMAL_BORDER,
                        color = yellow,
                        shape = CircleShape,
                    ),
        )

        OutlinedButton(
            modifier =
                Modifier
                    .constrainAs(btnGuest) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    }.fillMaxWidth()
                    .height(SIMPLE_HEIGHT_BUTTON),
            onClick = {
                navController.navigate("${NavigationScreens.HomeScreen.route}/${true}")
            },
            shape = RoundedCornerShape(NORMAL_ROUNDED_CORNER),
            border = BorderStroke(SIMPLE_BORDER, superLightBlue),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_guest),
                    contentDescription = stringResource(R.string.icon_email_description),
                    modifier = Modifier.weight(1.1f).size(SMALL_IMAGE),
                    tint = superLightBlue,
                )
                Text(
                    text = stringResource(R.string.guest_label),
                    modifier = Modifier.weight(2f),
                    textAlign = TextAlign.Start,
                    color = superLightBlue,
                )
            }
        }

        OutlinedButton(
            modifier =
                Modifier
                    .constrainAs(btnEmail) {
                        top.linkTo(btnGuest.bottom, margin = MEDIUM_PADDING)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }.height(SIMPLE_HEIGHT_BUTTON),
            onClick = {
                navController.navigate(NavigationScreens.LoginScreen.route)
            },
            shape = RoundedCornerShape(NORMAL_ROUNDED_CORNER),
            border = BorderStroke(SIMPLE_BORDER, superLightBlue),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_email),
                    contentDescription = stringResource(R.string.icon_email_description),
                    modifier = Modifier.weight(1.1f).size(SMALL_IMAGE),
                    tint = superLightBlue,
                )
                Text(
                    text = stringResource(R.string.email_label),
                    modifier = Modifier.weight(2f),
                    textAlign = TextAlign.Start,
                    color = superLightBlue,
                )
            }
        }

        OutlinedButton(
            modifier =
                Modifier
                    .constrainAs(btnGoogleAuth) {
                        top.linkTo(btnEmail.bottom, margin = MEDIUM_PADDING)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }.height(SIMPLE_HEIGHT_BUTTON),
            onClick = { googleSignInAction() },
            shape = RoundedCornerShape(NORMAL_ROUNDED_CORNER),
            border = BorderStroke(SIMPLE_BORDER, superLightBlue),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_google),
                    contentDescription = stringResource(R.string.icon_google_description),
                    modifier = Modifier.weight(1.1f).size(SMALL_IMAGE),
                )
                Text(
                    text = stringResource(R.string.google_label),
                    modifier = Modifier.weight(2f),
                    textAlign = TextAlign.Start,
                    color = superLightBlue,
                )
            }
        }
    }
}

@SuppressLint("ViewModelConstructorInComposable")
@Suppress("ktlint:standard:function-naming")
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainScreenPreview() {
    MainScreen(rememberNavController(), Modifier.fillMaxSize(), MainViewModel())
}
