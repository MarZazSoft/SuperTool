package com.marzazsoft.supertool.models

data class User(
    val displayName: String?,
    val familyName: String?,
    val givenName: String?,
    val id: String,
    val idToken: String,
    val phoneNumber: String?,
    val profilePictureUri: String?,
    val signInWith: SignInMethod = SignInMethod.GUEST,
)
