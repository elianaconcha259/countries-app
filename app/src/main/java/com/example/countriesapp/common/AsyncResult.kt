package com.example.countriesapp.util.common

enum class AsyncResultStatus { SUCCESS, ERROR }

data class AsyncResult<out T>(
    val status: AsyncResultStatus,
    val data: T? = null,
    val error: Errors? = null
) {

    companion object {
        fun <T> success(data: T?): AsyncResult<T> =
            AsyncResult(status = AsyncResultStatus.SUCCESS, data = data)

        fun <T> error(error: Errors, data: T? = null): AsyncResult<T> =
            AsyncResult(status = AsyncResultStatus.ERROR, error = error, data = data)

    }
}
