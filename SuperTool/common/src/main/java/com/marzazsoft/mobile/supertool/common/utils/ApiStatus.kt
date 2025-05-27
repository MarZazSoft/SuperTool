package com.marzazsoft.mobile.supertool.common.utils

sealed class ApiStatus<out T> {
    data object Empty : ApiStatus<Nothing>()

    data object Loading : ApiStatus<Nothing>()

    data class Success<out T>(
        val data: T,
    ) : ApiStatus<T>()

    data class Error(
        val message: String,
    ) : ApiStatus<Nothing>()
}
