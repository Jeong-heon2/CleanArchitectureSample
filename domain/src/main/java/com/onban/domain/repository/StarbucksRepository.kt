package com.onban.domain.repository

import com.onban.domain.model.StartEvent


interface StarbucksRepository {
    suspend fun fetchStartEvent(): StartEvent
}