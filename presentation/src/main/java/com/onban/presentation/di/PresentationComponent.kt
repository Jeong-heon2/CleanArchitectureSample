package com.onban.presentation.di

import com.onban.presentation.view.SplashActivity
import dagger.Subcomponent

@Subcomponent
interface PresentationComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): PresentationComponent
    }

    fun inject(activity: SplashActivity)
}