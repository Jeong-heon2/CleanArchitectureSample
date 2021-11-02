package com.onban.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEntity(vararg entity: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEntityList(entity: List<T>)

    @Delete
    fun deleteEntity(vararg entity: T)
}