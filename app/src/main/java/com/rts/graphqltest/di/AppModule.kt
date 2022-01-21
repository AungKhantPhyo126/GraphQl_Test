package com.rts.graphqltest.di

import android.content.Context
import com.rts.graphqltest.localdatabase.AppDatabase
import com.rts.graphqltest.localdatabase.dao.LaunchesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Singleton
    @Provides
    fun provideRoomDatabase(@ApplicationContext context: Context)  : AppDatabase {
        return AppDatabase.create(context)
    }

    @Provides
    fun providelaunchesDao(appDatabase: AppDatabase) : LaunchesDao {
        return appDatabase.launchesDao()
    }
}