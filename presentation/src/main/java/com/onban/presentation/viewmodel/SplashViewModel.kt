package com.onban.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.onban.domain.usecase.StartEventUseCase
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val startEventUseCase: StartEventUseCase
): ViewModel() {

    fun fetchStartEvent() {
        viewModelScope.launch {
            startEventUseCase.invoke(this) {
                Log.d("Test", it.name)
            }
        }
    }
}