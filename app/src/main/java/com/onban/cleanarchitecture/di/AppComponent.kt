package com.onban.cleanarchitecture.di

import android.content.Context
import com.onban.presentation.di.PresentationComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RemoteModule::class, DomainModule::class, SubComponentModule::class, DatabaseModule::class])
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun presentationComponent(): PresentationComponent.Factory
}