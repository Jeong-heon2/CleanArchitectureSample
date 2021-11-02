package com.onban.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.onban.local.entity.StartEventEntity

@Dao
interface StartEventDao : BaseDao<StartEventEntity> {
    @Query("SELECT * FROM StartEvent")
    fun getStartEventList(): List<StartEventEntity>
}