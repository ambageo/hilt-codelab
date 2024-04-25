package com.example.android.hilt.di

import android.content.Context
import androidx.room.Room
import com.example.android.hilt.data.AppDatabase
import com.example.android.hilt.data.LogDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/*
With the @InstallIn annotation we're indicating that the dependencies provided by this module
(AppDatabase and LogDao), should be available throughout the entire application
 and should have a singleton scope.
 */
@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    /*
     @Provides is used for types that cannot be constructor injected
     (abstract classes, interfaces, classes that are not owned by the app - so third party classes
     like Room in this case)
     */
    @Provides
    fun provideLogDao(database: AppDatabase): LogDao {
        return database.logDao()
    }

    @Singleton // Since we want Hilt to always provide the same database instance
    @Provides
    fun provideAppDataBase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "logging.db"
        ).build()
    }
}