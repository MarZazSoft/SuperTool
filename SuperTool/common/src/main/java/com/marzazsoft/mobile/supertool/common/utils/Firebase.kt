package com.marzazsoft.mobile.supertool.common.utils

import com.google.firebase.auth.FirebaseAuth

fun verifyLoggedUser(): Boolean = (FirebaseAuth.getInstance().currentUser != null)
