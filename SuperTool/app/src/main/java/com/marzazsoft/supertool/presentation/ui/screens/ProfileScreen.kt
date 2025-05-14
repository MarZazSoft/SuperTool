package com.marzazsoft.supertool.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.marzazsoft.supertool.R
import com.marzazsoft.supertool.navigation.NavigationScreens
import com.marzazsoft.supertool.presentation.ui.theme.darkBlue
import com.marzazsoft.supertool.presentation.ui.theme.white
import com.marzazsoft.supertool.presentation.viewModels.ProfileViewModel
import com.marzazsoft.supertool.utils.MEDIUM_PADDING
import com.marzazsoft.supertool.utils.MEDIUM_X_IMAGE
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Suppress("ktlint:standard:function-naming")
@Composable
fun ProfileScreen(
    appNavController: NavController,
    navController: NavController,
    modifier: Modifier,
    viewModel: ProfileViewModel = koinViewModel(),
) {
    val coroutineScope = rememberCoroutineScope()
    var name by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var profileImage by rememberSaveable { mutableStateOf("") }

    LaunchedEffect(Unit) {
        with(viewModel) {
            name = getUserName()
            email = getEmail()
            profileImage = getProfileImage()
        }
    }

    ProfileScreenUi(
        modifier = modifier,
        profileImage = profileImage,
        name = name,
        email = email,
        logOutAction = {
            coroutineScope.launch {
                with(viewModel) {
                    resetSignInPreference()
                    clearCredentialManager()
                }
            }
            appNavController.navigate(NavigationScreens.MainScreen.route) {
                popUpTo(appNavController.graph.startDestinationId) { inclusive = true }
            }
        },
    )
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun ProfileScreenUi(
    modifier: Modifier,
    profileImage: String,
    name: String,
    email: String,
    logOutAction: () -> Unit,
) {
    Column(modifier = modifier.fillMaxSize().background(darkBlue).padding(MEDIUM_PADDING)) {
        Box(
            modifier = Modifier.fillMaxWidth().padding(all = MEDIUM_PADDING),
            contentAlignment = Alignment.Center,
        ) {
            AsyncImage(
                model =
                    ImageRequest
                        .Builder(LocalContext.current)
                        .data(profileImage)
                        .crossfade(true)
                        .placeholder(R.drawable.super_tool_icon)
                        .error(R.drawable.super_tool_icon)
                        .build(),
                contentScale = ContentScale.Fit,
                contentDescription = stringResource(R.string.image_profile_description),
                modifier = Modifier.size(MEDIUM_X_IMAGE).clip(shape = CircleShape),
            )
        }
        Row {
            Text(text = "Nombre", modifier = Modifier.weight(1f))
            Text(text = name, modifier = Modifier.weight(2f))
        }
        Row {
            Text(text = "E-mail", modifier = Modifier.weight(1f))
            Text(text = email, modifier = Modifier.weight(2f))
        }
        Box(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(MEDIUM_PADDING),
            contentAlignment = Alignment.Center,
        ) {
            Button(
                colors = ButtonDefaults.buttonColors(containerColor = darkBlue),
                onClick = { logOutAction() },
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
    ProfileScreen(rememberNavController(), rememberNavController(), modifier = Modifier.fillMaxSize())
}
