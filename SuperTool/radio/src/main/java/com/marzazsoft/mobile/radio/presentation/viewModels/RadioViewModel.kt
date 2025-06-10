package com.marzazsoft.mobile.radio.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.media3.exoplayer.ExoPlayer

class RadioViewModel(
    val singleExoPlayer: ExoPlayer,
) : ViewModel() {
    fun getExoPlayer() = singleExoPlayer
}
