package com.onban.local.datasource

import com.onban.data.datasource.StartEventCacheDataSource
import com.onban.data.model.StartEventRes
import com.onban.local.dao.StartEventDao

class LocalStarbucksDataSourceImpl(
    private val startEventDao: StartEventDao
): StartEventCacheDataSource {
    override suspend fun getStartEventList(): List<StartEventRes> {
        //mapper 추가해야 함 , 비동기
        val res = startEventDao.getStartEventList()
        return res.map { StartEventRes(it.name) }
    }
}