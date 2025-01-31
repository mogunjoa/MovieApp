package com.mogun.remote.api

import retrofit2.Response
import javax.inject.Inject

class ApiHelper @Inject constructor() {
    suspend fun <T, R> safeApiCall(
        apiCall: suspend () -> Response<T>,
        mapToData: (T) -> List<R>
    ): List<R> {
        val response = apiCall()

        if (response.isSuccessful) {
            return response.body()?.let(mapToData) ?: emptyList()
        } else {
            val message = response.errorBody()?.string() ?: "unknown error"
            throw Exception(message)
        }
    }
}