package com.onban.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.onban.local.dao.StartEventDao
import com.onban.local.entity.StartEventEntity

@Database(entities = [StartEventEntity::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun getDao(): StartEventDao
}