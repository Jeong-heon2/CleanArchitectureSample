package com.onban.cleanarchitecture.di

import com.onban.remote.adapter.NetworkResponseAdapterFactory
import com.onban.remote.api.StartEventApi
import dagger.Module
import dagger.Provides
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RemoteModule {

    @Provides
    @Singleton
    fun provideStartEventApi(callAdapterFactory: NetworkResponseAdapterFactory, converterFactory: Converter.Factory): StartEventApi {
        return Retrofit.Builder()
            .baseUrl("https://public.codesquad.kr")
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(callAdapterFactory)
            .build()
            .create(StartEventApi::class.java)
    }

    @Provides
    @Singleton
    fun provideConverterFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideCallAdapterFactory(): CallAdapter.Factory {
        return NetworkResponseAdapterFactory()
    }
}