package com.onban.data.datasource

import com.onban.data.model.StartEventRes

interface StartEventCacheDataSource {
    suspend fun getStartEventList(): List<StartEventRes>
}