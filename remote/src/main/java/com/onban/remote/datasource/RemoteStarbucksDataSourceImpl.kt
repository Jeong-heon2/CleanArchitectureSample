package com.onban.remote.datasource

import com.onban.data.datasource.RemoteStarbucksDataSource
import com.onban.remote.api.StartEventApi
import javax.inject.Inject

class RemoteStarbucksDataSourceImpl
    @Inject constructor(
    private val startEventApi: StartEventApi,
) : RemoteStarbucksDataSource {
    override suspend fun getStartEvent() = startEventApi.getStartEvent()
}