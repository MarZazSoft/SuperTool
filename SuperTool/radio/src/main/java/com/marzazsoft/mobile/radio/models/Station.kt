package com.marzazsoft.mobile.radio.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Station(
    val name: String,
    val stationIcon: String,
    val stationUri: String,
) : Parcelable
