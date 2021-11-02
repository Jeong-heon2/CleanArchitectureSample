package com.onban.remote.api

import com.onban.data.model.NetworkResponse
import com.onban.data.model.StartEventRes
import retrofit2.http.GET

interface StartEventApi {
    @GET("jk/boostcamp/starbuckst-loading.json")
    suspend fun getStartEvent(): NetworkResponse<StartEventRes, Error>
}
