package com.onban.local.entity

import androidx.room.Entity
import androidx.room.Index
import com.onban.domain.model.StartEvent


@Entity(
    tableName = "StartEvent",
    indices = [
        Index(value = ["name"])
    ],
    primaryKeys = ["name"]
)
data class StartEventEntity (
    override val name: String
) : StartEvent