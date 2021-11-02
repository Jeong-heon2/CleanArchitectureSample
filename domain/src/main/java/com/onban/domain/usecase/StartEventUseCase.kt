package com.onban.domain.usecase

import com.onban.domain.model.StartEvent
import com.onban.domain.repository.StarbucksRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

class StartEventUseCase @Inject constructor(
    private val starbucksRepository: StarbucksRepository,
) {
    operator fun invoke(
        scope: CoroutineScope,
        onResult: (StartEvent) -> Unit = {}
    ) {
        scope.launch(Dispatchers.Main) {
            val deferred = async(Dispatchers.IO) { starbucksRepository.fetchStartEvent() }
            onResult(deferred.await())
        }
    }
}