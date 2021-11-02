package com.onban.cleanarchitecture

import android.app.Application
import com.onban.cleanarchitecture.di.AppComponent
import com.onban.cleanarchitecture.di.DaggerAppComponent
import com.onban.presentation.di.PresentationComponent
import com.onban.presentation.di.PresentationComponentProvider

/*
https://medium.com/@kimtaesoo188/android-clean-architecture-2e789d6cefc6
 */
class App : Application(), PresentationComponentProvider {
    val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    private fun initializeComponent(): AppComponent {
        return DaggerAppComponent.factory().create(applicationContext)
    }

    override fun providePresentationComponent(): PresentationComponent {
        return appComponent.presentationComponent().create()
    }
}