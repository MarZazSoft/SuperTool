package com.marzazsoft.mobile.radio.models

import androidx.compose.ui.Modifier

data class RadioScreenAttributes(
    val modifier: Modifier,
    val backAction: () -> Unit,
    val playAction: () -> Unit,
    val playIcon: Int,
    val titleRadioStation: String,
    val uriIconStation: String,
    val stationsList: List<Station>,
    val actionStationSelected: (radioStation: Station) -> Unit,
)
