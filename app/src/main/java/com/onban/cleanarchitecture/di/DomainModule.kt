package com.onban.cleanarchitecture.di

import com.onban.data.datasource.RemoteStarbucksDataSource
import com.onban.data.repository.StarbucksRepositoryImpl
import com.onban.domain.repository.StarbucksRepository
import com.onban.remote.api.StartEventApi
import com.onban.remote.datasource.RemoteStarbucksDataSourceImpl
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun providesStarbucksRepository(remoteStarbucksDataSource: RemoteStarbucksDataSource): StarbucksRepository {
        return StarbucksRepositoryImpl(remoteStarbucksDataSource)
    }

    @Provides
    fun providesStarbucksDataSource(startEventApi: StartEventApi): RemoteStarbucksDataSource {
        return RemoteStarbucksDataSourceImpl(startEventApi)
    }
}