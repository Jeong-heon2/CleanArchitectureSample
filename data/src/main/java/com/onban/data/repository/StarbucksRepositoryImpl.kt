package com.onban.data.repository

import com.onban.data.datasource.RemoteStarbucksDataSource
import com.onban.data.model.NetworkResponse
import com.onban.data.model.StartEventRes
import com.onban.domain.model.StartEvent
import com.onban.domain.repository.StarbucksRepository
import javax.inject.Inject

class StarbucksRepositoryImpl @Inject constructor(
    val remoteStarbucksDataSource: RemoteStarbucksDataSource
) : StarbucksRepository {
    override suspend fun fetchStartEvent(): StartEvent {
        val res = remoteStarbucksDataSource.getStartEvent()
        when (res) {
            is NetworkResponse.Success -> {
                return res.body
            }
        }
        //mapper

        return StartEventRes("임시 리턴")
    }
}