package com.sumanta.todo.di

import android.app.Application
import androidx.room.Room
import com.sumanta.todo.data.TodoDao
import com.sumanta.todo.data.TodoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun providesDatabase(application: Application): TodoDatabase =
        Room.databaseBuilder(
            application,
            TodoDatabase::class.java,
            "TodoDatabase"
        )
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideDao(db: TodoDatabase): TodoDao = db.getDao()

}