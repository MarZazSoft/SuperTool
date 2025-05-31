package com.marzazsoft.mobile.supertool.common.extensionfunctions

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import com.marzazsoft.supertooldesign.utils.yellow
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

@OptIn(ExperimentalEncodingApi::class)
fun String.toB64(): String = Base64.Default.encode(this.encodeToByteArray())

@OptIn(ExperimentalEncodingApi::class)
fun String.fromB64toString(): String = Base64.Default.decode(this).decodeToString()

fun String.addTextWithLink(linkedText: String): AnnotatedString =
    buildAnnotatedString {
        append(this@addTextWithLink)
        withStyle(style = SpanStyle(color = yellow)) {
            append(linkedText)
        }
    }
