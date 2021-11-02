package com.onban.data.datasource

import com.onban.data.model.NetworkResponse
import com.onban.data.model.StartEventRes

interface RemoteStarbucksDataSource {
    suspend fun getStartEvent(): NetworkResponse<StartEventRes, Error>
}