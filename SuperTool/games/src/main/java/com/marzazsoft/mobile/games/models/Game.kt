package com.marzazsoft.mobile.games.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Game(
    val title: String,
    val posterUrl: String,
    val gameUrl: String,
) : Parcelable
