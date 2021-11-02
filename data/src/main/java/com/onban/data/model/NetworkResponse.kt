package com.onban.data.model

import java.io.IOException

sealed class NetworkResponse <out T : Any, out E : Any> {
    data class Success<T : Any>(val body: T): NetworkResponse<T, Nothing>()
    data class ApiError<E : Any>(val body: E, val code: Int): NetworkResponse<Nothing, E>()
    data class NetworkError(val error: IOException): NetworkResponse<Nothing, Nothing>()
    data class UnknownError(val error: Throwable?): NetworkResponse<Nothing, Nothing>()
}