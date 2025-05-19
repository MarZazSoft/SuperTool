package com.marzazsoft.supertool.models

import android.os.Parcelable
import androidx.compose.ui.graphics.Color
import kotlinx.parcelize.Parcelize

@Parcelize
data class Tool(
    val id: Int,
    val color: Int,
    val icon: Int,
    val title: String,
    val resume: String,
) : Parcelable {
    fun getColor(): Color = Color(color = color)
}
