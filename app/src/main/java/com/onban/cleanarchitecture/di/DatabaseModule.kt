package com.onban.cleanarchitecture.di

import android.content.Context
import androidx.room.Room
import com.onban.local.database.AppDataBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideAppDataBaseDatabase(context: Context) =
        Room.databaseBuilder(context, AppDataBase::class.java, "AppDatabase")
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideWhatsNewDao(db: AppDataBase) = db.getDao()
}