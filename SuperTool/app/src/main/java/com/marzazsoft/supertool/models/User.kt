package com.marzazsoft.supertool.models

import android.net.Uri

data class User(
    val displayName: String?,
    val familyName: String?,
    val givenName: String?,
    val id: String,
    val idToken: String,
    val phoneNumber: String?,
    val profilePictureUri: Uri?,
    val signInWith: SignInMethod = SignInMethod.GUEST
)
