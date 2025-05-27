package com.marzazsoft.mobile.supertool.common.extensionfunctions

import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

@OptIn(ExperimentalEncodingApi::class)
fun String.toB64(): String = Base64.Default.encode(this.encodeToByteArray())

@OptIn(ExperimentalEncodingApi::class)
fun String.fromB64toString(): String = Base64.Default.decode(this).decodeToString()
